package ar.edu.uade.ia.ejbs;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import ar.edu.uade.ia.common.dtos.PackageAuthorizeRequestDTO;
import ar.edu.uade.ia.common.dtos.PackageOfferRequestDTO;
import ar.edu.uade.ia.entities.PortalUser;
import ar.edu.uade.ia.entities.business.PackageOffer;
import ar.edu.uade.ia.entities.business.PackageReservation;

/**
 * Session Bean implementation class PackageOfferEJB
 */
@Stateless
@LocalBean
public class PackageOfferEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public PackageOfferEJB() {}
    
	@SuppressWarnings("unchecked")
	public List<PackageOffer> search(PackageOfferRequestDTO request) throws Exception {
		
		Integer destinationId = request.getDestination() != null ? request.getDestination().getId() : null;
		Date dateFrom = request.getFromDate();
		Date dateTo = request.getToDate();
		Integer quantityPeople = request.getQuantityPeople();
		Float minPrice = request.getMinPrice();
		Float maxPrice = request.getMaxPrice();
		
		StringBuffer queryBuilder = new StringBuffer("select pkOf");
		queryBuilder.append(" from PackageOffer as pkOf");
		queryBuilder.append(" inner join pkOf.destination as dest");
		queryBuilder.append(" where 1 = 1");
		if (minPrice != null) {
			queryBuilder.append(" and pkOf.price >= :minPrice");
		}
		if (maxPrice != null) {
			queryBuilder.append(" and pkOf.price <= :maxPrice");
		}
		if (destinationId != null)
			queryBuilder.append(" and dest.id = :destinationId");
		if (dateFrom != null && dateTo != null)
			queryBuilder.append(" and pkOf.offerStart >= :dateFrom and pkOf.offerEnd <= :dateTo");
		if (dateFrom != null && dateTo == null)
			queryBuilder.append(" and pkOf.offerStart >= :dateFrom");
		if (dateFrom == null && dateTo != null)
			queryBuilder.append(" and pkOf.offerEnd <= :dateTo");
		if (quantityPeople != null)
			queryBuilder.append(" and pkOf.availableQuota >= :quantityPeople");
		
		Query query = this.em.createQuery(queryBuilder.toString());
		if (minPrice != null)
			query.setParameter("minPrice", minPrice);
		if (maxPrice != null)
			query.setParameter("maxPrice", maxPrice);
		if (destinationId != null)
			query.setParameter("destinationId", destinationId);
		if (quantityPeople != null)
			query.setParameter("quantityPeople", quantityPeople);
		if (dateFrom != null && dateTo != null) {
			query.setParameter("dateFrom", dateFrom, TemporalType.DATE);
			query.setParameter("dateTo", dateTo, TemporalType.DATE);
		} else if (dateFrom != null && dateTo == null) {
			query.setParameter("dateFrom", dateFrom, TemporalType.DATE);
		} else if (dateFrom == null && dateTo != null) {
			query.setParameter("dateTo", dateTo, TemporalType.DATE);
		}
		
		return query.getResultList();
	}
	
	public PackageOffer getDetail(Integer id) throws Exception {
		return this.em.find(PackageOffer.class, id);
	}

	public Boolean hasQuota(Integer packageId, PackageAuthorizeRequestDTO req) {

		Integer quantityPeople = req.getQuantityPeople();
		int value = 0;
		
		StringBuffer queryBuilder = new StringBuffer("select pkOf");
		queryBuilder.append(" from PackageOffer as pkOf");
		queryBuilder.append(" where 1 = 1");
		queryBuilder.append(" and pkOf.id = :packageId");
		queryBuilder.append(" and pkOf.availableQuota >= :quantityPeople");
		
		Query query = this.em.createQuery(queryBuilder.toString());
		query.setParameter("quantityPeople", quantityPeople);
		query.setParameter("packageId", packageId);
		
		value = query.getResultList().size();

		return (value ==1);
	}
	
	@SuppressWarnings("unchecked")
	public List<PackageOffer> searchOtherPackages(Integer packageId, PackageOfferRequestDTO request) {
		Integer destinationId = request.getDestination().getId();
		
		Date dateFrom = request.getFromDate();
		Date dateTo = request.getToDate();
		
		StringBuffer queryBuilder = new StringBuffer("select pkOf");
		queryBuilder.append(" from PackageOffer as pkOf");
		queryBuilder.append(" inner join pkOf.destination as dest");
		queryBuilder.append(" where 1 = 1");
		queryBuilder.append(" and dest.id = :destinationId");
		queryBuilder.append(" and pkOf.id <> :packageId");
		
		if (dateFrom != null && dateTo != null)
			queryBuilder.append(" and pkOf.offerStart >= :dateFrom and pkOf.offerEnd <= :dateTo");
		if (dateFrom != null && dateTo == null)
			queryBuilder.append(" and pkOf.offerStart >= :dateFrom");
		if (dateFrom == null && dateTo != null)
			queryBuilder.append(" and pkOf.offerEnd <= :dateTo");
		
		
		Query query = this.em.createQuery(queryBuilder.toString());
		query.setParameter("destinationId", destinationId);
		query.setParameter("packageId", packageId);
		if (dateFrom != null && dateTo != null) {
			query.setParameter("dateFrom", dateFrom, TemporalType.DATE);
			query.setParameter("dateTo", dateTo, TemporalType.DATE);
		} else if (dateFrom != null && dateTo == null) {
			query.setParameter("dateFrom", dateFrom, TemporalType.DATE);
		} else if (dateFrom == null && dateTo != null) {
			query.setParameter("dateTo", dateTo, TemporalType.DATE);
		}
		
		return query.getResultList();
	}
	
    public void add(PackageOffer packageOffer) throws Exception {
    	this.em.persist(packageOffer);
    }

	public void update(PackageOffer po) throws Exception {
		this.em.merge(po);
	}
	
	public void reserve(Integer packageId, PackageAuthorizeRequestDTO req, PortalUser user) throws Exception {
		
		PackageOffer po = this.em.find(PackageOffer.class, packageId);
		
		po.setAvailableQuota(po.getAvailableQuota() - req.getQuantityPeople());
		this.em.merge(po);
		
		PackageReservation pr = new PackageReservation();
		pr.setPckage(po);
		pr.setPortalUser(user);
		pr.setTotalPrice(req.getTotalPrice());
		pr.setReservationDate(new Date());
		pr.setQuotaQuantity(req.getQuantityPeople());
		
		this.em.persist(pr);
	}
	
}

package ar.edu.uade.ia.ejbs;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

import ar.edu.uade.ia.common.dtos.PackageOfferRequestDTO;
import ar.edu.uade.ia.entities.business.PackageOffer;

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
		
		Integer idDestination = request.getDestination() != null ? request.getDestination().getId() : null;
		Date dateFrom = request.getFromDate();
		Date dateTo = request.getToDate();
		Integer quantityPeople = request.getQuantityPeople();
		
		StringBuffer queryBuilder = new StringBuffer("select pkOf");
		queryBuilder.append(" from PackageOffer as pkOf");
		queryBuilder.append(" inner join pkOf.destination as dest");
		queryBuilder.append(" where 1 = 1");
		
		if (idDestination != null)
			queryBuilder.append(" and dest.id = :idDestination");
		if (dateFrom != null && dateTo != null)
			queryBuilder.append(" and pkOf.offerStart >= :dateFrom and pkOf.offerEnd <= :dateTo");
		if (dateFrom != null && dateTo == null)
			queryBuilder.append(" and pkOf.offerStart >= :dateFrom");
		if (dateFrom == null && dateTo != null)
			queryBuilder.append(" and pkOf.offerEnd <= :dateTo");
		if (quantityPeople != null)
			queryBuilder.append(" and pkOf.availableQuota >= :quantityPeople");
		
		
		Query query = this.em.createQuery(queryBuilder.toString());
		if (idDestination != null)
			query.setParameter("idDestination", idDestination);
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
}

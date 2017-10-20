package ar.edu.uade.ia.ejbs;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ar.edu.uade.ia.entities.FavoriteHotelOffer;
import ar.edu.uade.ia.entities.PortalUser;
import ar.edu.uade.ia.entities.business.HotelOffer;

/**
 * Session Bean implementation class FavoriteHotelOfferEJB
 */
@Stateless
@LocalBean
public class FavoriteHotelOfferEJB {

	@PersistenceContext(unitName = "mu")
	protected EntityManager em;
	
    /**
     * Default constructor. 
     */
    public FavoriteHotelOfferEJB() {
    }

    public FavoriteHotelOffer getFavoriteHotelOffer(HotelOffer ho, PortalUser user) {
		try {
			Query query = this.em.createQuery("FROM FavoriteHotelOffer WHERE hotelOffer.id = :hoId AND portalUser.id = :userId");
			query.setParameter("hoId", ho.getId());
			query.setParameter("userId", user.getId());
			return (FavoriteHotelOffer) query.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		}
	}

	public void removeFavorite(FavoriteHotelOffer fho) {
		this.em.remove(fho);
	}

	public void markFavorite(HotelOffer ho, PortalUser user) {
		FavoriteHotelOffer fho = new FavoriteHotelOffer();
		fho.setHotelOffer(ho);
		fho.setPortalUser(user);
		this.em.persist(fho);
	}
}

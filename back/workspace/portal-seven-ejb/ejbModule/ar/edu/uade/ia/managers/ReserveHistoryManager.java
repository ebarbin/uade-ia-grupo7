package ar.edu.uade.ia.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ar.edu.uade.ia.common.dtos.ImageDTO;
import ar.edu.uade.ia.common.dtos.ReservationDTO;
import ar.edu.uade.ia.common.enums.Constant;
import ar.edu.uade.ia.ejbs.PackageReservationEJB;
import ar.edu.uade.ia.ejbs.QuotaReservationEJB;
import ar.edu.uade.ia.entities.business.Hotel;
import ar.edu.uade.ia.entities.business.PackageReservation;
import ar.edu.uade.ia.entities.business.QuotaReservation;

/**
 * Session Bean implementation class ReserveHistoryManager
 */
@Stateless
@LocalBean
public class ReserveHistoryManager {

	@EJB
	private QuotaReservationEJB quotaReservationEJB;
	
	@EJB
	private PackageReservationEJB packageReservationEJB;
	
    /**
     * Default constructor. 
     */
    public ReserveHistoryManager() {}

	public List<ReservationDTO> getReservations(Integer portalUserId) throws Exception {
		List<QuotaReservation>hotelReservations = this.quotaReservationEJB.getReservations(portalUserId);
		List<PackageReservation>packageReservations = this.packageReservationEJB.getReservations(portalUserId);
		
		List<ReservationDTO>results = new ArrayList<ReservationDTO>();
		ReservationDTO dto;
		Hotel h;
		for (QuotaReservation hr : hotelReservations) {
			dto = new ReservationDTO();
			h = hr.getQuotas().get(0).getOffer().getHotel();
			dto.setStart(hr.getReservationStart());
			dto.setEnd(hr.getReservationEnd());
			dto.setName(h.getName());
			if (h.getImages() != null && !h.getImages().isEmpty()) {
				dto.setImage(new ImageDTO());
				dto.getImage().setId(h.getImages().get(0).getId());
			}
			if (h.getPoints() != null && !h.getVotes().equals(0)) {
				dto.setValoration(h.getPoints() / h.getVotes());
			} else {
				dto.setValoration(0);
			}
			dto.setType(Constant.HOTEL.name());
			results.add(dto);
		}
		
		/*for(PackageReservation pr : packageReservations) {
			dto = new ReservationDTO();
			h = hr.getQuotas().get(0).getOffer().getHotel();
			dto.setStart(pr.get );
			dto.setEnd(hr.getReservationEnd());
			dto.setName(h.getName());
			if (h.getImages() != null && !h.getImages().isEmpty()) {
				dto.setImage(new ImageDTO());
				dto.getImage().setId(h.getImages().get(0).getId());
			}
			if (h.getPoints() != null && !h.getVotes().equals(0)) {
				dto.setValoration(h.getPoints() / h.getVotes());
			} else {
				dto.setValoration(0);
			}
			dto.setType(Constant.HOTEL.name());
			results.add(dto);
		}*/
		return results;
	}

}

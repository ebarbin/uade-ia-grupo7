package ar.edu.uade.ia.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ar.edu.uade.ia.commons.dtos.HotelAutocompleteDTO;
import ar.edu.uade.ia.ejbs.HotelAutocompleteEJB;
import ar.edu.uade.ia.ejbs.entities.bussiness.Hotel;
import ar.edu.uade.ia.managers.interfaces.HotelAutocompleteManagerLocal;
import ar.edu.uade.ia.managers.interfaces.HotelAutocompleteManagerRemote;

/**
 * Session Bean implementation class HotelAutocompleteManager
 */
@Stateless
@LocalBean
public class HotelAutocompleteManager implements HotelAutocompleteManagerRemote, HotelAutocompleteManagerLocal {

	@EJB
	private HotelAutocompleteEJB hotelAutocompleteEJB;
	
    /**
     * Default constructor. 
     */
    public HotelAutocompleteManager() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<HotelAutocompleteDTO> query(String value, Integer limit) throws Exception {
		List<HotelAutocompleteDTO> results = new ArrayList<HotelAutocompleteDTO>();
		List<Hotel> hotels = this.hotelAutocompleteEJB.query(value, limit);
		for (Hotel hotel : hotels) {
			results.add(new HotelAutocompleteDTO(hotel.getId(), hotel.getName()));
		}
		return results;
	}

}

package ar.edu.uade.ia.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ar.edu.uade.ia.commons.dtos.HotelAutocompleteDTO;
import ar.edu.uade.ia.ejbs.HotelEJB;
import ar.edu.uade.ia.ejbs.entities.bussiness.Hotel;
import ar.edu.uade.ia.managers.interfaces.AutocompleteManagerLocal;
import ar.edu.uade.ia.managers.interfaces.AutocompleteManagerRemote;

/**
 * Session Bean implementation class HotelAutocompleteManager
 */
@Stateless
@LocalBean
public class AutocompleteManager implements AutocompleteManagerRemote, AutocompleteManagerLocal {

	@EJB
	private HotelEJB hotelEJB;
	
    /**
     * Default constructor. 
     */
    public AutocompleteManager() {}

	@Override
	public List<HotelAutocompleteDTO> queryHotels(String value, Integer limit) throws Exception {
		List<HotelAutocompleteDTO> results = new ArrayList<HotelAutocompleteDTO>();
		List<Hotel> hotels = this.hotelEJB.autocompleteQuery(value, limit);
		for (Hotel hotel : hotels) {
			results.add(new HotelAutocompleteDTO(hotel.getId(), hotel.getName()));
		}
		return results;
	}

}

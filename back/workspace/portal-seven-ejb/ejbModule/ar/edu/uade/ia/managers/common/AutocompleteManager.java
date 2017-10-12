package ar.edu.uade.ia.managers.common;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ar.edu.uade.ia.common.dtos.SimpleNamedDTO;
import ar.edu.uade.ia.ejbs.common.AutocompleteEJB;
import ar.edu.uade.ia.entities.business.Destination;
import ar.edu.uade.ia.entities.business.Hotel;

/**
 * Session Bean implementation class HotelAutocompleteManager
 */
@Stateless
@LocalBean
public class AutocompleteManager {

	@EJB
	private AutocompleteEJB autocompleteEJB;

	/**
	 * Default constructor.
	 */
	public AutocompleteManager() {
	}

	public List<SimpleNamedDTO> queryHotels(String value, Integer limit) throws Exception {
		List<SimpleNamedDTO> results = new ArrayList<SimpleNamedDTO>();
		List<Hotel> hotels = this.autocompleteEJB.hotelQuery(value, limit);
		SimpleNamedDTO simpleNamedDTO;
		for (Hotel hotel : hotels) {
			simpleNamedDTO = new SimpleNamedDTO();
			simpleNamedDTO.setId(hotel.getId());
			simpleNamedDTO.setName(hotel.getName() + " - (" + hotel.getCode() + ")");
			results.add(simpleNamedDTO);
		}
		return results;
	}

	public List<SimpleNamedDTO> queryDestinations(String value, Integer limit) throws Exception {
		List<SimpleNamedDTO> results = new ArrayList<SimpleNamedDTO>();
		List<Destination> destinations = this.autocompleteEJB.destinationQuery(value, limit);
		SimpleNamedDTO simpleNamedDTO;
		for (Destination destination : destinations) {
			simpleNamedDTO = new SimpleNamedDTO();
			simpleNamedDTO.setId(destination.getId());
			simpleNamedDTO.setName(destination.getName());
			results.add(simpleNamedDTO);
		}
		return results;
	}

}

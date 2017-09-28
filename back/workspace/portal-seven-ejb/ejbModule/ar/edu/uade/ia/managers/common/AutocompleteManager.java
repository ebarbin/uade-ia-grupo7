package ar.edu.uade.ia.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ar.edu.uade.ia.commons.dtos.SimpleNamedDTO;
import ar.edu.uade.ia.ejbs.AutocompleteEJB;
import ar.edu.uade.ia.ejbs.entities.bussiness.Destination;
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
	private AutocompleteEJB autocompleteEJB;

    /**
     * Default constructor. 
     */
    public AutocompleteManager() {}

	@Override
	public List<SimpleNamedDTO> queryHotels(String value, Integer limit) throws Exception {
		List<SimpleNamedDTO> results = new ArrayList<SimpleNamedDTO>();
		List<Hotel> hotels = this.autocompleteEJB.hotelQuery(value, limit);
		SimpleNamedDTO simpleNamedDTO;
		for (Hotel hotel : hotels) {
			simpleNamedDTO = new SimpleNamedDTO();
			simpleNamedDTO.setId(hotel.getId());
			simpleNamedDTO.setName(hotel.getName());
			results.add(simpleNamedDTO);
		}
		return results;
	}

	@Override
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

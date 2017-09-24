package ar.edu.uade.ia.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import ar.edu.uade.ia.commons.dtos.AutocompleteResourceDTO;
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
	public List<AutocompleteResourceDTO> queryHotels(String value, Integer limit) throws Exception {
		List<AutocompleteResourceDTO> results = new ArrayList<AutocompleteResourceDTO>();
		List<Hotel> hotels = this.autocompleteEJB.hotelQuery(value, limit);
		for (Hotel hotel : hotels) {
			results.add(new AutocompleteResourceDTO(hotel.getId(), hotel.getName()));
		}
		return results;
	}

	@Override
	public List<AutocompleteResourceDTO> queryDestinations(String value, Integer limit) throws Exception {
		List<AutocompleteResourceDTO> results = new ArrayList<AutocompleteResourceDTO>();
		List<Destination> destinations = this.autocompleteEJB.destinationQuery(value, limit);
		for (Destination destination : destinations) {
			results.add(new AutocompleteResourceDTO(destination.getId(), destination.getName()));
		}
		return results;
	}

}

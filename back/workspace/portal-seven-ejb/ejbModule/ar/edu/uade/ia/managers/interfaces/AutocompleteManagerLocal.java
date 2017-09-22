package ar.edu.uade.ia.managers.interfaces;

import java.util.List;

import javax.ejb.Local;

import ar.edu.uade.ia.commons.dtos.HotelAutocompleteDTO;

@Local
public interface AutocompleteManagerLocal {

	List<HotelAutocompleteDTO> queryHotels(String value, Integer limit) throws Exception;
}

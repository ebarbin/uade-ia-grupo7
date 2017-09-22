package ar.edu.uade.ia.managers.interfaces;

import java.util.List;

import javax.ejb.Remote;

import ar.edu.uade.ia.commons.dtos.HotelAutocompleteDTO;

@Remote
public interface AutocompleteManagerRemote {
	
	List<HotelAutocompleteDTO> queryHotels(String value, Integer limit) throws Exception;
}

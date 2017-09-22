package ar.edu.uade.ia.managers.interfaces;

import java.util.List;

import javax.ejb.Local;

import ar.edu.uade.ia.commons.dtos.AutocompleteResourceDTO;

@Local
public interface AutocompleteManagerLocal {

	List<AutocompleteResourceDTO> queryHotels(String value, Integer limit) throws Exception;
	
	List<AutocompleteResourceDTO> queryDestinations(String value, Integer limit) throws Exception;
}

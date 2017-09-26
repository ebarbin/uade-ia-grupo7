package ar.edu.uade.ia.managers.interfaces;

import java.util.List;

import javax.ejb.Local;

import ar.edu.uade.ia.commons.dtos.SimpleNamedDTO;

@Local
public interface AutocompleteManagerLocal {

	List<SimpleNamedDTO> queryHotels(String value, Integer limit) throws Exception;
	
	List<SimpleNamedDTO> queryDestinations(String value, Integer limit) throws Exception;
}

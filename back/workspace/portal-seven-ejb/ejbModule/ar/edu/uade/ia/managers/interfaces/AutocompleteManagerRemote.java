package ar.edu.uade.ia.managers.interfaces;

import java.util.List;

import javax.ejb.Remote;

import ar.edu.uade.ia.commons.dtos.AutocompleteResourceDTO;

@Remote
public interface AutocompleteManagerRemote {
	
	List<AutocompleteResourceDTO> queryHotels(String value, Integer limit) throws Exception;

	List<AutocompleteResourceDTO> queryDestinations(String value, Integer limit) throws Exception;
}

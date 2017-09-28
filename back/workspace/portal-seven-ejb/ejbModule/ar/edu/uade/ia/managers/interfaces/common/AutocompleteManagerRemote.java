package ar.edu.uade.ia.managers.interfaces.common;

import java.util.List;

import javax.ejb.Remote;

import ar.edu.uade.ia.dtos.SimpleNamedDTO;

@Remote
public interface AutocompleteManagerRemote {
	
	List<SimpleNamedDTO> queryHotels(String value, Integer limit) throws Exception;

	List<SimpleNamedDTO> queryDestinations(String value, Integer limit) throws Exception;
}

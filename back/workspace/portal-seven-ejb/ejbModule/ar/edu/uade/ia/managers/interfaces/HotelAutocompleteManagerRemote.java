package ar.edu.uade.ia.managers.interfaces;

import java.util.List;

import javax.ejb.Remote;

import ar.edu.uade.ia.commons.dtos.HotelAutocompleteDTO;

@Remote
public interface HotelAutocompleteManagerRemote {
	
	List<HotelAutocompleteDTO> query(String value, Integer limit) throws Exception;
}

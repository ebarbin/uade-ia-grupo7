package ar.edu.uade.ia.managers.interfaces;

import java.util.List;

import javax.ejb.Local;

import ar.edu.uade.ia.dtos.PackageOfferDTO;
import ar.edu.uade.ia.dtos.PackageOfferHeaderDTO;
import ar.edu.uade.ia.dtos.PackageOfferRequestDTO;

@Local
public interface PackageOfferManagerLocal {

	List<PackageOfferHeaderDTO>search(PackageOfferRequestDTO packageOfferRequestDTO) throws Exception;
	
	PackageOfferDTO getDetail(Integer id) throws Exception;
}

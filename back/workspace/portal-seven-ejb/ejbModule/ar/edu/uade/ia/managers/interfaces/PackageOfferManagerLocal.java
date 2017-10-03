package ar.edu.uade.ia.managers.interfaces;

import java.util.List;

import javax.ejb.Local;

import ar.edu.uade.ia.common.dtos.PackageOfferDTO;
import ar.edu.uade.ia.common.dtos.PackageOfferHeaderDTO;
import ar.edu.uade.ia.common.dtos.PackageOfferRequestDTO;

@Local
public interface PackageOfferManagerLocal {

	List<PackageOfferHeaderDTO>search(PackageOfferRequestDTO packageOfferRequestDTO) throws Exception;
	
	PackageOfferDTO getDetail(Integer id) throws Exception;
}

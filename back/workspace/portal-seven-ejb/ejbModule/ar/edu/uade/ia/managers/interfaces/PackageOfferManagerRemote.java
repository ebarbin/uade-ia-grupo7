package ar.edu.uade.ia.managers.interfaces;

import java.util.List;

import javax.ejb.Remote;

import ar.edu.uade.ia.dtos.PackageOfferDTO;
import ar.edu.uade.ia.dtos.PackageOfferHeaderDTO;
import ar.edu.uade.ia.dtos.PackageOfferRequestDTO;

@Remote
public interface PackageOfferManagerRemote {

	List<PackageOfferHeaderDTO>search(PackageOfferRequestDTO packageOfferRequestDTO) throws Exception;
	
	PackageOfferDTO getDetail(Integer id) throws Exception;
}

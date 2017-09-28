package ar.edu.uade.ia.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;

import ar.edu.uade.ia.dtos.PackageOfferDTO;
import ar.edu.uade.ia.dtos.PackageOfferHeaderDTO;
import ar.edu.uade.ia.dtos.PackageOfferRequestDTO;
import ar.edu.uade.ia.ejbs.PackageOfferEJB;
import ar.edu.uade.ia.ejbs.entities.bussiness.PackageOffer;
import ar.edu.uade.ia.managers.interfaces.PackageOfferManagerLocal;
import ar.edu.uade.ia.managers.interfaces.PackageOfferManagerRemote;

/**
 * Session Bean implementation class PackageOfferManager
 */
@Stateless
@LocalBean
public class PackageOfferManager implements PackageOfferManagerRemote, PackageOfferManagerLocal {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	@EJB
	private PackageOfferEJB packageOfferEJB;
	
    /**
     * Default constructor. 
     */
    public PackageOfferManager() {}

	@Override
	public List<PackageOfferHeaderDTO> search(PackageOfferRequestDTO packageOfferRequestDTO) throws Exception {
		List<PackageOffer> packageOffers = this.packageOfferEJB.search(packageOfferRequestDTO);
		return this.convertToListOfPackageOfferHeaderDTO(packageOffers);
	}

	@Override
	public PackageOfferDTO getDetail(Integer id) throws Exception {
		PackageOffer po = this.packageOfferEJB.getDetail(id);
		return PackageOfferManager.mapper.map(po, PackageOfferDTO.class);
	}

	private List<PackageOfferHeaderDTO> convertToListOfPackageOfferHeaderDTO(List<PackageOffer> packageOffers) {
		return new ArrayList<PackageOfferHeaderDTO>();
	}
}

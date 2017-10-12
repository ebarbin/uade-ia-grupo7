package ar.edu.uade.ia.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;

import ar.edu.uade.ia.common.dtos.ImageDTO;
import ar.edu.uade.ia.common.dtos.PackageOfferDTO;
import ar.edu.uade.ia.common.dtos.PackageOfferHeaderDTO;
import ar.edu.uade.ia.common.dtos.PackageOfferRequestDTO;
import ar.edu.uade.ia.common.dtos.SimpleNamedDTO;
import ar.edu.uade.ia.ejbs.PackageOfferEJB;
import ar.edu.uade.ia.entities.business.Image;
import ar.edu.uade.ia.entities.business.PackageOffer;
import ar.edu.uade.ia.entities.business.ServicePackage;

/**
 * Session Bean implementation class PackageOfferManager
 */
@Stateless
@LocalBean
public class PackageOfferManager {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	@EJB
	private PackageOfferEJB packageOfferEJB;

	/**
	 * Default constructor.
	 */
	public PackageOfferManager() {}

	public List<PackageOfferHeaderDTO> search(PackageOfferRequestDTO packageOfferRequestDTO) throws Exception {
		List<PackageOffer> packageOffers = this.packageOfferEJB.search(packageOfferRequestDTO);
		return this.convertToListOfPackageOfferHeaderDTO(packageOffers);
	}

	public PackageOfferDTO getDetail(Integer id) throws Exception {
		PackageOffer po = this.packageOfferEJB.getDetail(id);
		return PackageOfferManager.mapper.map(po, PackageOfferDTO.class);
	}

	public List<PackageOfferHeaderDTO> searchOtherPackages(Integer packageId, PackageOfferRequestDTO request) {
		List<PackageOffer> packageOffers = this.packageOfferEJB.searchOtherPackages(packageId, request);
		return this.convertToListOfPackageOfferHeaderDTO(packageOffers);
	}

	private List<PackageOfferHeaderDTO> convertToListOfPackageOfferHeaderDTO(List<PackageOffer> packageOffers) {
		List<PackageOfferHeaderDTO> results = new ArrayList<PackageOfferHeaderDTO>();

		PackageOfferHeaderDTO headerDTO;
		SimpleNamedDTO namedDTO;
		ImageDTO imageDTO;

		for (PackageOffer packageOffer : packageOffers) {
			headerDTO = new PackageOfferHeaderDTO();

			headerDTO.setId(packageOffer.getId());
			headerDTO.setDescription(packageOffer.getDescription());
			headerDTO.setServices(new ArrayList<SimpleNamedDTO>());

			for (ServicePackage service : packageOffer.getServices()) {
				namedDTO = new SimpleNamedDTO();
				namedDTO.setId(service.getId());
				namedDTO.setName(service.getName());
				headerDTO.getServices().add(namedDTO);
			}
			headerDTO.setPrice(packageOffer.getPrice());
			headerDTO.setOfferStart(packageOffer.getOfferStart());
			headerDTO.setOfferEnd(packageOffer.getOfferEnd());

			headerDTO.setImages(new ArrayList<ImageDTO>());
			for (Image img : packageOffer.getImages()) {
				imageDTO = new ImageDTO();
				imageDTO.setId(img.getId());
				headerDTO.getImages().add(imageDTO);
			}
			results.add(headerDTO);
		}

		return results;
	}
}

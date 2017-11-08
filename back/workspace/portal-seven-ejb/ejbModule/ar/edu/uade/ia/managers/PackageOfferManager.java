package ar.edu.uade.ia.managers;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import org.dozer.DozerBeanMapperBuilder;
import org.dozer.Mapper;

import ar.edu.uade.ia.common.dtos.AuthorizeStatusDTO;
import ar.edu.uade.ia.common.dtos.ImageDTO;
import ar.edu.uade.ia.common.dtos.PackageAuthorizeRequestDTO;
import ar.edu.uade.ia.common.dtos.PackageOfferDTO;
import ar.edu.uade.ia.common.dtos.PackageOfferHeaderDTO;
import ar.edu.uade.ia.common.dtos.PackageOfferRequestDTO;
import ar.edu.uade.ia.common.dtos.SimpleNamedDTO;
import ar.edu.uade.ia.common.enums.LoggingAction;
import ar.edu.uade.ia.ejbs.FavouriteOfferEJB;
import ar.edu.uade.ia.ejbs.PackageOfferEJB;
import ar.edu.uade.ia.ejbs.common.PortalUserEJB;
import ar.edu.uade.ia.entities.PortalUser;
import ar.edu.uade.ia.entities.business.Image;
import ar.edu.uade.ia.entities.business.PackageOffer;
import ar.edu.uade.ia.entities.business.ServicePackage;
import ar.edu.uade.ia.integrations.backOffice.logging.LoggingJMS;

/**
 * Session Bean implementation class PackageOfferManager
 */
@Stateless
@LocalBean
public class PackageOfferManager {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

	@EJB
	private LoggingJMS loggingService;
	
	@EJB
	private PackageOfferEJB packageOfferEJB;

	@EJB
	private FavouriteOfferEJB favouriteOfferEJB;
	
	@EJB
	private PortalUserEJB portalUserEJB;
	
	/**
	 * Default constructor.
	 */
	public PackageOfferManager() {}

	public AuthorizeStatusDTO autorize(Integer offerId, PackageAuthorizeRequestDTO req) throws Exception {

		AuthorizeStatusDTO dto = new AuthorizeStatusDTO();
		if (this.packageOfferEJB.hasQuota(offerId, req)){
			// TODO MANDAR A AUTORIZAR AL WEBSERVICE SOAP
			
			PortalUser user = this.portalUserEJB.getById(req.getPortalUser().getId());
			this.packageOfferEJB.reserve(offerId, req, user);	
			
			dto.setStatus(Boolean.TRUE);
			this.loggingService.info(LoggingAction.HOTEL_RESERVATION);
			
		} else {
			dto.setStatus(Boolean.FALSE);
			dto.setDescription("No hay paquetes disponibles.");
		}

		return dto;
	}
	
	public List<PackageOfferHeaderDTO> search(Integer portalUserId, PackageOfferRequestDTO packageOfferRequestDTO) throws Exception {
		List<PackageOffer> packageOffers = this.packageOfferEJB.search(packageOfferRequestDTO);
		return this.convertToListOfPackageOfferHeaderDTO(packageOffers, portalUserId);
	}

	public PackageOfferDTO getDetail(Integer id) throws Exception {
		PackageOffer po = this.packageOfferEJB.getDetail(id);
		return PackageOfferManager.mapper.map(po, PackageOfferDTO.class);
	}

	public List<PackageOfferHeaderDTO> searchOtherPackages(Integer packageId, PackageOfferRequestDTO request) throws Exception {
		List<PackageOffer> packageOffers = this.packageOfferEJB.searchOtherPackages(packageId, request);
		return this.convertToListOfPackageOfferHeaderDTO(packageOffers, null);
	}

	private List<PackageOfferHeaderDTO> convertToListOfPackageOfferHeaderDTO(List<PackageOffer> packageOffers, Integer portalUserId) throws Exception {
		List<PackageOfferHeaderDTO> results = new ArrayList<PackageOfferHeaderDTO>();

		PackageOfferHeaderDTO headerDTO;
		SimpleNamedDTO namedDTO;
		ImageDTO imageDTO;

		for (PackageOffer packageOffer : packageOffers) {
			headerDTO = new PackageOfferHeaderDTO();

			headerDTO.setId(packageOffer.getId());
			headerDTO.setDescription(packageOffer.getDescription());
			headerDTO.setQuantityPeople(packageOffer.getAvailableQuota());
			
			if(packageOffer.getAgency().getPoints() != null && !packageOffer.getAgency().getPoints().equals(0)) {
				headerDTO.setValoration(packageOffer.getAgency().getPoints() / packageOffer.getAgency().getVotes());
			} else {
				headerDTO.setValoration(0);
			}
			
			headerDTO.setServices(new ArrayList<SimpleNamedDTO>());
			if (portalUserId != null)
				headerDTO.setFavourite(this.favouriteOfferEJB.isFavouritePackage(packageOffer.getId(), portalUserId));
			
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

	public Integer valoration(Integer id, Integer vote) throws Exception {
		PackageOffer po = this.packageOfferEJB.getDetail(id);
		if (po.getAgency().getVotes() == null) po.getAgency().setVotes(0);
		po.getAgency().setVotes(po.getAgency().getVotes()+1);
		if (po.getAgency().getPoints() == null) po.getAgency().setPoints(0);
		po.getAgency().setPoints(po.getAgency().getPoints()+vote);
		this.packageOfferEJB.update(po);
		return po.getAgency().getPoints() / po.getAgency().getVotes();
	}
}

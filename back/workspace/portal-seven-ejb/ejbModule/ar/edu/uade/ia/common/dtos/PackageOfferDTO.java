package ar.edu.uade.ia.common.dtos;

import java.io.Serializable;
import java.util.List;

public class PackageOfferDTO implements Serializable {

	private static final long serialVersionUID = 8609017154811359650L;
	
	private Integer id;

	private String description;
	
	private List<ImageDTO> images;
	
	private Float price;
	
	private String cancellationPolicy;
	
	private List<SimpleNamedDTO> services;
	
	private List<SimpleNamedDTO> paymentMethods;
	
	private DestinationDTO destination;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ImageDTO> getImages() {
		return images;
	}

	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getCancellationPolicy() {
		return cancellationPolicy;
	}

	public void setCancellationPolicy(String cancellationPolicy) {
		this.cancellationPolicy = cancellationPolicy;
	}

	public List<SimpleNamedDTO> getServices() {
		return services;
	}

	public void setServices(List<SimpleNamedDTO> services) {
		this.services = services;
	}

	public DestinationDTO getDestination() {
		return destination;
	}

	public void setDestination(DestinationDTO destination) {
		this.destination = destination;
	}

	public List<SimpleNamedDTO> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(List<SimpleNamedDTO> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}
}

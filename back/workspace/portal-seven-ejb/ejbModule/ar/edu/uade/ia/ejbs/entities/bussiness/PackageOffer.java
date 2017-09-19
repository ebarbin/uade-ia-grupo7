package ar.edu.uade.ia.ejbs.entities.bussiness;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class PackageOffer extends Offer {

	private static final long serialVersionUID = 6647993123948723384L;

	private List<Image>images;
	
	private String description;
	
	private List<Service>services;
	
	private Destination destination;
	
	private Integer availableQuota;
	
	private List<PaymentMethod>paymentMethods;
	
	private Agency agency;

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	public Integer getAvailableQuota() {
		return availableQuota;
	}

	public void setAvailableQuota(Integer availableQuota) {
		this.availableQuota = availableQuota;
	}

	public List<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}
}

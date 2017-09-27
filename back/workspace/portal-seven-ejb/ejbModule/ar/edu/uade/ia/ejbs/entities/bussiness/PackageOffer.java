package ar.edu.uade.ia.ejbs.entities.bussiness;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "package_offer")
public class PackageOffer extends PersistentObject {

	private static final long serialVersionUID = 6647993123948723384L;

	private Date start;
	
	@Column(name="offer_end")
	private Date end;
	
	private Float price;
	
	private String cancellationPolicy;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "package_offer_image", joinColumns = {
			@JoinColumn(name = "package_offer_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "image_id", nullable = false, updatable = false) })
	private List<Image> images;

	private String description;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "package_offer_service", joinColumns = {
			@JoinColumn(name = "package_offer_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "service_id", nullable = false, updatable = false) })
	private List<Service> services;

	@ManyToOne
	private Destination destination;

	private Integer availableQuota;

	@ManyToMany
	@JoinTable(name = "package_offer_payment_method", joinColumns = {
			@JoinColumn(name = "package_offer_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "payment_method_id", nullable = false, updatable = false) })
	private List<PaymentMethod> paymentMethods;

	@ManyToOne
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

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
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
}

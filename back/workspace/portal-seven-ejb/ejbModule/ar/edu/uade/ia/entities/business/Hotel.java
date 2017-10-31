package ar.edu.uade.ia.entities.business;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "hotel")
public class Hotel extends PersistentObject {

	private static final long serialVersionUID = 564408788585026377L;

	private String name;

	private String description;
	
	private Integer points;
	
	private Integer votes;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "hotel_image", joinColumns = {
			@JoinColumn(name = "hotel_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "image_id", nullable = false, updatable = false) })
	private List<Image> images;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "hotel_service_hotel", joinColumns = {
			@JoinColumn(name = "hotel_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "service_hotel_id", nullable = false, updatable = false) })
	private List<ServiceHotel> services;

	@ManyToOne(cascade = CascadeType.ALL)
	private Address address;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "hotel_payment_method", joinColumns = {
			@JoinColumn(name = "hotel_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "payment_method_id", nullable = false, updatable = false) })
	private List<PaymentMethod> paymentMethods;

	private Boolean state;

	private String code;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<ServiceHotel> getServices() {
		return services;
	}

	public void setServices(List<ServiceHotel> services) {
		this.services = services;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<PaymentMethod> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}
}

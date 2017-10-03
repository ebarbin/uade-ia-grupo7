package ar.edu.uade.ia.entities.business;

import java.util.List;

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
	
	@ManyToOne
	private Destination destination;
	
	private String description;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "hotel_image", joinColumns = {
			@JoinColumn(name = "hotel_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "image_id", nullable = false, updatable = false) })
	private List<Image> images;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "hotel_service", joinColumns = {
			@JoinColumn(name = "hotel_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "service_id", nullable = false, updatable = false) })
	private List<Service>services;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "hotel_room", joinColumns = {
			@JoinColumn(name = "hotel_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "room_id", nullable = false, updatable = false) })
	private List<Room>rooms;
	
	@ManyToOne
	private Address address;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "hotel_payment_method", joinColumns = {
			@JoinColumn(name = "hotel_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "payment_method_id", nullable = false, updatable = false) })
	private List<PaymentMethod>paymentMethods;

	private Boolean state;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
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

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
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
}

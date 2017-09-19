package ar.edu.uade.ia.ejbs.entities.bussiness;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Hotel extends PersistentObject {

	private static final long serialVersionUID = 564408788585026377L;

	private String name;
	
	private Destination destination;
	
	private String descripcion;
	
	private List<Image> images;
	
	private List<Service>services;
	
	private List<Room>rooms;
	
	private Address address;
	
	private List<PaymentMethod>paymentMethods;

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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

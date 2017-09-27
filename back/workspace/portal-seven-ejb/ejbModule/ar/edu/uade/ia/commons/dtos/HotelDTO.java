package ar.edu.uade.ia.commons.dtos;

import java.io.Serializable;
import java.util.List;

public class HotelDTO implements Serializable {

	private static final long serialVersionUID = 125066535146409060L;

	private Integer id;
	
	private String name;
	
	private String description;
	
	private List<ImageDTO>images;
	
	private AddressDTO address;
	
	private List<RoomDTO>rooms;
	
	private List<SimpleNamedDTO>services;
	
	private List<SimpleNamedDTO>paymentMethods;
	
	private Boolean state;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<ImageDTO> getImages() {
		return images;
	}

	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<RoomDTO> getRooms() {
		return rooms;
	}

	public void setRooms(List<RoomDTO> rooms) {
		this.rooms = rooms;
	}

	public List<SimpleNamedDTO> getServices() {
		return services;
	}

	public void setServices(List<SimpleNamedDTO> services) {
		this.services = services;
	}

	public List<SimpleNamedDTO> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(List<SimpleNamedDTO> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}
}

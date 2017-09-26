package ar.edu.uade.ia.commons.dtos;

import java.io.Serializable;
import java.util.List;

public class HotelDTO implements Serializable {

	private static final long serialVersionUID = 125066535146409060L;

	private Integer id;
	
	private String name;
	
	private List<ImageDTO>images;
	
	private AddressDTO address;

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
}

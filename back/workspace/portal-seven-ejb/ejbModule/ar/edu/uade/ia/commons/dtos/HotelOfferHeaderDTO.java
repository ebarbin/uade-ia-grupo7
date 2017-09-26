package ar.edu.uade.ia.commons.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ar.edu.uade.ia.ejbs.entities.bussiness.HotelOffer;
import ar.edu.uade.ia.ejbs.entities.bussiness.Image;
import ar.edu.uade.ia.ejbs.entities.bussiness.Service;

public class HotelOfferHeaderDTO implements Serializable {

	private static final long serialVersionUID = -5056225701663864983L;
	
	private Integer id;
	
	private List<ImageDTO>images;
	
	private String name;
	
	private String description;
	
	private List<SimpleNamedDTO>services;
	
	private Float price;

	public HotelOfferHeaderDTO(HotelOffer ho) {
		this.id = ho.getId();
		this.name = ho.getHotel().getName();
		this.description = ho.getHotel().getDescription();
		this.services = new ArrayList<SimpleNamedDTO>();
		for(Service service : ho.getHotel().getServices()) {
			this.services.add(new SimpleNamedDTO(service));
		}
		this.price = ho.getPrice();
		this.images = new ArrayList<ImageDTO>();
		for(Image img : ho.getHotel().getImages()) {
			this.images.add(new ImageDTO(img.getId()));
		}
	}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SimpleNamedDTO> getServices() {
		return services;
	}

	public void setServices(List<SimpleNamedDTO> services) {
		this.services = services;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public List<ImageDTO> getImages() {
		return images;
	}

	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}
}

package ar.edu.uade.ia.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class HotelOfferHeaderDTO implements Serializable {

	private static final long serialVersionUID = -5056225701663864983L;
	
	private Integer id;
	
	private List<ImageDTO>images;
	
	private String name;
	
	private String description;
	
	private List<SimpleNamedDTO>services;
	
	private Float price;
	
	private Integer roomCapacity;
	
	private Date offerStart;
	
	private Date offerEnd;
	
	public HotelOfferHeaderDTO() {}
	
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

	public Integer getRoomCapacity() {
		return roomCapacity;
	}

	public void setRoomCapacity(Integer roomCapacity) {
		this.roomCapacity = roomCapacity;
	}

	public Date getOfferStart() {
		return offerStart;
	}

	public void setOfferStart(Date offerStart) {
		this.offerStart = offerStart;
	}

	public Date getOfferEnd() {
		return offerEnd;
	}

	public void setOfferEnd(Date offerEnd) {
		this.offerEnd = offerEnd;
	}
}

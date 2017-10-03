package ar.edu.uade.ia.common.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PackageOfferHeaderDTO implements Serializable {
	
	private static final long serialVersionUID = 2287388135848248683L;
	
	private Integer id;

	private String description;
	
	private List<SimpleNamedDTO>services;
	
	private List<ImageDTO>images;
	
	private Float price;
	
	private Date offerStart;
	
	private Date offerEnd;
	
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

	public List<SimpleNamedDTO> getServices() {
		return services;
	}

	public void setServices(List<SimpleNamedDTO> services) {
		this.services = services;
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
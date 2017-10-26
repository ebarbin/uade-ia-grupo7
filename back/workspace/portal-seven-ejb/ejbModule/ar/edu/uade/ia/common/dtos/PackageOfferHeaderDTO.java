package ar.edu.uade.ia.common.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import ar.edu.uade.ia.common.enums.Constant;

public class PackageOfferHeaderDTO implements Serializable {
	
	private static final long serialVersionUID = 2287388135848248683L;
	
	private Integer id;

	private String description;
	
	private List<SimpleNamedDTO>services;
	
	private List<ImageDTO>images;
	
	private Float price;
	
	private Date offerStart;
	
	private Date offerEnd;
	
	private Integer quantityPeople;
	
	private Boolean favourite;
	
	private String type = Constant.PACKAGE.name();
	
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

	public Boolean getFavourite() {
		return favourite;
	}

	public void setFavourite(Boolean favourite) {
		this.favourite = favourite;
	}

	public Integer getQuantityPeople() {
		return quantityPeople;
	}

	public void setQuantityPeople(Integer quantityPeople) {
		this.quantityPeople = quantityPeople;
	}

	public String getType() {
		return type;
	}

	@SuppressWarnings("unused")
	private void setType(String type) {
		this.type = type;
	}
}

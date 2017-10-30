package ar.edu.uade.ia.common.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class FavoriteOfferDTO implements Serializable {

	private static final long serialVersionUID = -2332942010626328189L;

	private Integer id;
	private Integer quantityCapacity;
	private Date offerStart;
	private Date offerEnd;
	private String type;
	private String description;
	private List<SimpleNamedDTO> services;
	private List<SimpleNamedDTO> paymentMethods;
	private String name;
	private Integer roomType;
	private List<ImageDTO> images;
	private Boolean favourite = Boolean.TRUE;
	private Float price;
	private Integer valoration;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getQuantityCapacity() {
		return quantityCapacity;
	}

	public void setQuantityCapacity(Integer quantityCapacity) {
		this.quantityCapacity = quantityCapacity;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getRoomType() {
		return roomType;
	}

	public void setRoomType(Integer roomType) {
		this.roomType = roomType;
	}

	public List<ImageDTO> getImages() {
		return images;
	}

	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}

	public List<SimpleNamedDTO> getPaymentMethods() {
		return paymentMethods;
	}

	public void setPaymentMethods(List<SimpleNamedDTO> paymentMethods) {
		this.paymentMethods = paymentMethods;
	}

	public Boolean getFavourite() {
		return favourite;
	}

	public void setFavourite(Boolean favourite) {
		this.favourite = favourite;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getValoration() {
		return valoration;
	}

	public void setValoration(Integer valoration) {
		this.valoration = valoration;
	}
}

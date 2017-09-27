package ar.edu.uade.ia.ejbs.entities.bussiness;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "room")
public class Room extends PersistentObject {

	private static final long serialVersionUID = 5243270004093839420L;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "room_image", joinColumns = {
			@JoinColumn(name = "room_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "image_id", nullable = false, updatable = false) })
	private List<Image>images;
	
	@Column(length = 500)
	private String description;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "room_service", joinColumns = {
			@JoinColumn(name = "room_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "service_id", nullable = false, updatable = false) })
	private List<Service>services;
	
	private Integer capacity;
	
	private String type;

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Service> getServices() {
		return services;
	}

	public void setServices(List<Service> services) {
		this.services = services;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}

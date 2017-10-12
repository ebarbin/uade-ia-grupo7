package ar.edu.uade.ia.entities.business;

import java.util.List;

import javax.persistence.CascadeType;
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

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "room_image", joinColumns = {
			@JoinColumn(name = "room_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "image_id", nullable = false, updatable = false) })
	private List<Image> images;

	private String description;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "room_service_room", joinColumns = {
			@JoinColumn(name = "room_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "service_room_id", nullable = false, updatable = false) })
	private List<ServiceRoom> services;

	private Integer capacity;

	private Integer type;

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

	public List<ServiceRoom> getServices() {
		return services;
	}

	public void setServices(List<ServiceRoom> services) {
		this.services = services;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}

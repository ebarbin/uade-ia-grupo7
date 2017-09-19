package ar.edu.uade.ia.ejbs.entities.bussiness;

import java.util.List;

public class Room extends PersistentObject {

	private static final long serialVersionUID = 5243270004093839420L;

	private List<Image>images;
	
	private String descripcion;
	
	private List<Service>services;
	
	private Integer capacity;
	
	private String type;

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
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

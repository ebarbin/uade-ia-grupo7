package ar.edu.uade.ia.common.dtos;

import java.io.Serializable;
import java.util.List;

public class RoomDTO implements Serializable {

	private static final long serialVersionUID = -8668114247735410136L;

	private Integer id;
	
	private List<ImageDTO>images;
	
	private List<SimpleNamedDTO>services;
	
	private String description;
	
	private Integer capacity;
	
	private String type;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<ImageDTO> getImages() {
		return images;
	}

	public void setImages(List<ImageDTO> images) {
		this.images = images;
	}

	public List<SimpleNamedDTO> getServices() {
		return services;
	}

	public void setServices(List<SimpleNamedDTO> services) {
		this.services = services;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

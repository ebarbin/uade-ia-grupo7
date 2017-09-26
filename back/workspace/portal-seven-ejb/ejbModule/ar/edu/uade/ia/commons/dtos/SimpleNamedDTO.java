package ar.edu.uade.ia.commons.dtos;

import java.io.Serializable;

import ar.edu.uade.ia.ejbs.entities.bussiness.Service;

public class SimpleNamedDTO implements Serializable {

	private static final long serialVersionUID = 3176115022360295095L;

	private Integer id;
	
	private String name;

	public SimpleNamedDTO(Service service) {
		this.id = service.getId();
		this.name = service.getName();
	}
	
	public SimpleNamedDTO(Integer id, String name) {
		this.id = id;
		this.name = name;
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
}

package ar.edu.uade.ia.commons.dtos;

import java.io.Serializable;

public class AutocompleteResourceDTO implements Serializable {

	private static final long serialVersionUID = 3176115022360295095L;

	private Integer id;
	
	private String name;

	public AutocompleteResourceDTO(Integer id, String name) {
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

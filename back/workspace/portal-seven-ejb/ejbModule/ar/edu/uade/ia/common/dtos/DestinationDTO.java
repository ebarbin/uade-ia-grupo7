package ar.edu.uade.ia.common.dtos;

import java.io.Serializable;

public class DestinationDTO implements Serializable {

	private static final long serialVersionUID = 3594007392449142903L;

	private Integer id;
	
	private String name;
	
	private AddressDTO address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}

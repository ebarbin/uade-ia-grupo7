package ar.edu.uade.ia.common.dtos;

import java.io.Serializable;

public class PackageOfferDTO implements Serializable {

	private static final long serialVersionUID = 8609017154811359650L;
	
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
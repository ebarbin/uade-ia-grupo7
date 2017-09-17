package ar.edu.uade.ia.commons.dtos;

import java.io.Serializable;

public class ImageDTO implements Serializable { 

	private static final long serialVersionUID = -4801306275268602785L;

	private Integer id;
	
	private byte[] data;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}

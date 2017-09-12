package ar.edu.uade.ia.commons.dtos;

import java.io.Serializable;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = -1894418050880651314L;

	private Integer id;
	
	private String userName;
	
	private String firtName;
	
	private String sureName;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirtName() {
		return firtName;
	}

	public void setFirtName(String firtName) {
		this.firtName = firtName;
	}

	public String getSureName() {
		return sureName;
	}

	public void setSureName(String sureName) {
		this.sureName = sureName;
	}
}

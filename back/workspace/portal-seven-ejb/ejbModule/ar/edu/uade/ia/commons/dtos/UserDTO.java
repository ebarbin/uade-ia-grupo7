package ar.edu.uade.ia.commons.dtos;

import java.io.Serializable;

public class UserDTO implements Serializable {

	private static final long serialVersionUID = -1894418050880651314L;

	private Integer id;
	
	private String username;
	
	private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}

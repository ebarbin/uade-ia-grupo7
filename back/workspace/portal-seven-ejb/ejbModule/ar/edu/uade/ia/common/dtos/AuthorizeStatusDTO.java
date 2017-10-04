package ar.edu.uade.ia.common.dtos;

import java.io.Serializable;

public class AuthorizeStatusDTO implements Serializable {

	private static final long serialVersionUID = 3643476671980999001L;
	
	private Boolean status;

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
}

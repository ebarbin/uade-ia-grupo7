package ar.edu.uade.ia.common.dtos;

import java.io.Serializable;

public class ConfigurationDTO implements Serializable {

	private static final long serialVersionUID = -5436073796910295850L;
	
	private String backOffice;

	public String getBackOffice() {
		return backOffice;
	}

	public void setBackOffice(String backOffice) {
		this.backOffice = backOffice;
	}
}

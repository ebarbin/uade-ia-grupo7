package ar.edu.uade.ia.common.dtos;

import java.io.Serializable;

public class ConfigurationDTO implements Serializable {

	private static final long serialVersionUID = -5436073796910295850L;
	
	private String authorizeSource;
	private String loggingSource;
	
	public String getAuthorizeSource() {
		return authorizeSource;
	}
	public void setAuthorizeSource(String authorizeSource) {
		this.authorizeSource = authorizeSource;
	}
	public String getLoggingSource() {
		return loggingSource;
	}
	public void setLoggingSource(String loggingSource) {
		this.loggingSource = loggingSource;
	}
}

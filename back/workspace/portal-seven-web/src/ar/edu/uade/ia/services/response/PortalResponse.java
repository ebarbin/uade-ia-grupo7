package ar.edu.uade.ia.services.response;

public class PortalResponse {
	
	private boolean success = true;

	private Object data;
	
	private String errorMessage;

	public PortalResponse() {
	}

	public PortalResponse(String errorMessage) {
		this.success = false;
		this.errorMessage = errorMessage;
	}

	public PortalResponse(Object data) {
		this.data = data;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}

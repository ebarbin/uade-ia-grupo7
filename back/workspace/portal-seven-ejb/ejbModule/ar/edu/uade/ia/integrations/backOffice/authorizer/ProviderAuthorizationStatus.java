package ar.edu.uade.ia.integrations.backOffice.authorizer;

public enum ProviderAuthorizationStatus {

	APPROVED("Aprobado"), REJECTED("Rechazado"), PENDING("Pendiente");
	
	private String code;
	
	private ProviderAuthorizationStatus(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}
}

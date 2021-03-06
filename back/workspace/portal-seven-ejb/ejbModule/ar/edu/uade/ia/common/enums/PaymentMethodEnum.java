package ar.edu.uade.ia.common.enums;

public enum PaymentMethodEnum {

	CREDIT_CARD(1, "Tarjeta"), CHECK(2, "Cheque"), PAYMENT_AT_DESTINATION(3, "Pago en Destino"), MERCADO_PAGO(4,
			"Mercado Pago"), PAY_PAL(5, "Pay Pal");

	private Integer id;

	private String description;

	private PaymentMethodEnum(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
	
	public static PaymentMethodEnum getById(Integer id) {
		for(PaymentMethodEnum pm :PaymentMethodEnum.values()) {
			if (pm.getId().equals(id)) return pm;
		}
		return null;
	}
}

package ar.edu.uade.ia.common.enums;

public enum LogginAction {

	HOTEL_RESERVATION(1, "Reserva Hotel"),
	PACKAGE_RESERVATION(2, "Reserva Paquete"),
	HOTEL_OFFER_REGISTRATION(3, "Registro Oferta Hotelera"),
	PACKAGE_OFFER_REGISTRATION(4, "Registro Oferta Paquete"),
	HOTEL_OFFER_SEARCH(5, "Buscar Oferta Hotelera"),
	PACKAGE_OFFER_SEARCH(6, "Buscar Oferta Paquete"),
	HOTEL_OFFER_DETAIL(7, "Ver Detalle Oferta Hotelera"),
	PACKAGE_OFFER_DETAIL(8, "Ver Detalle Oferta Paquete"),
	OTHER(99, "Otro"),
	ERROR(100, "Error");
	
	private Integer id;
	
	private String description;
	
	private LogginAction(Integer id, String description) {
		this.id = id;
		this.description = description;
	}

	public Integer getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
}

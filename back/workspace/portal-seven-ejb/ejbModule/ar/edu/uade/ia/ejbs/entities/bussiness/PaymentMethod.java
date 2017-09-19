package ar.edu.uade.ia.ejbs.entities.bussiness;

import javax.persistence.Entity;

@Entity
public class PaymentMethod extends PersistentObject {

	private static final long serialVersionUID = 4857775724267046382L;
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

package ar.edu.uade.ia.entities.business;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "payment_method")
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

package ar.edu.uade.ia.entities.business;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "destination")
public class Destination extends PersistentObject {

	private static final long serialVersionUID = 7201684177830379591L;

	private String name;
	
	@ManyToOne
	private Address address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}

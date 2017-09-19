package ar.edu.uade.ia.ejbs.entities.bussiness;

import javax.persistence.Entity;

@Entity
public class Destination extends PersistentObject {

	private static final long serialVersionUID = 7201684177830379591L;

	private String name;
	
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

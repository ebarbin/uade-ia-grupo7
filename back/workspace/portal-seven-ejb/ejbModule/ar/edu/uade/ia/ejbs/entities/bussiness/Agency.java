package ar.edu.uade.ia.ejbs.entities.bussiness;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class Agency extends PersistentObject {

	private static final long serialVersionUID = 954537632748254118L;

	private String name;
	
	@OneToOne
	private Address address;
	
	private String email;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

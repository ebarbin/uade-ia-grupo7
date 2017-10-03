package ar.edu.uade.ia.entities.business;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "agency")
public class Agency extends PersistentObject {

	private static final long serialVersionUID = 954537632748254118L;

	private String name;
	
	@ManyToOne
	private Address address;
	
	private String email;
	
	private Boolean state;

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

	public Boolean getState() {
		return state;
	}

	public void setState(Boolean state) {
		this.state = state;
	}
}

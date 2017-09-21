package ar.edu.uade.ia.ejbs.entities.bussiness;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "service")
public class Service extends PersistentObject {

	private static final long serialVersionUID = -1978696476715748487L;

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

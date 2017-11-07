package ar.edu.uade.ia.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

import ar.edu.uade.ia.entities.business.PersistentObject;

@Entity
@Table(name = "configuration")
public class Configuration extends PersistentObject {

	private static final long serialVersionUID = 4978177979592976951L;

	private String key;
	
	private String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

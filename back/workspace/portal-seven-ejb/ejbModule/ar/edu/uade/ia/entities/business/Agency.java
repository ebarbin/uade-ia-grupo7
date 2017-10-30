package ar.edu.uade.ia.entities.business;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "agency")
public class Agency extends PersistentObject {

	private static final long serialVersionUID = 954537632748254118L;

	private String name;
	
	private String email;
	
	private Boolean state;

	private String code;
	
	private Integer points;
	
	private Integer votes;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}
}

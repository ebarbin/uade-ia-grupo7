package ar.edu.uade.ia.common.dtos;

import java.io.Serializable;

public class ValorationDTO implements Serializable {
	
	private static final long serialVersionUID = 6518816911341682994L;
	
	private Integer vote;

	public Integer getVote() {
		return vote;
	}

	public void setVote(Integer vote) {
		this.vote = vote;
	}
}

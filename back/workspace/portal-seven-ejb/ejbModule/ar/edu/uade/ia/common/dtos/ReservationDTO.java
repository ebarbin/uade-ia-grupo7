package ar.edu.uade.ia.common.dtos;

import java.io.Serializable;
import java.util.Date;

public class ReservationDTO implements Serializable {

	private static final long serialVersionUID = -5922392830001250768L;
	
	private String name;
	private ImageDTO image;
	private Date start;
	private Date end;
	private String type;
	private Integer valoration;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImageDTO getImage() {
		return image;
	}

	public void setImage(ImageDTO image) {
		this.image = image;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getValoration() {
		return valoration;
	}

	public void setValoration(Integer valoration) {
		this.valoration = valoration;
	}
}

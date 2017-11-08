package ar.edu.uade.ia.entities.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="image")
public class Image extends PersistentObject {
	
	private static final long serialVersionUID = -2382376870718857038L;
	
	@Lob
    private byte[] data;

	private String description;
	
	@Column(length = 500)
	private String url;
	
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

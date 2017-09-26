package ar.edu.uade.ia.ejbs.entities.bussiness;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="Imagen")
public class Image extends PersistentObject {
	
	private static final long serialVersionUID = -2382376870718857038L;
	
	@Lob
    @Column(columnDefinition="image")
    private byte[] data;

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}

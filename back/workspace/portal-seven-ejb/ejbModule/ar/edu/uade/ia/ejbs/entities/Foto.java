package ar.edu.uade.ia.ejbs.entities;

public class Foto extends ObjetoPersistente {

	private static final long serialVersionUID = 6973416984676285472L;
	
	private byte[] data;

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}

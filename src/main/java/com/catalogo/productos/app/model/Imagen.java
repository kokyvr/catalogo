package com.catalogo.productos.app.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "imagenes")
public class Imagen {

	@Id
	private String id;
	
	private byte[] foto;

	public Imagen() {
	}

	public Imagen(byte[] foto) {
		this.foto = foto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	
}

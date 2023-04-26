package com.catalogo.productos.app.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Roles implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	
	private String role;

	public Roles() {
	}

	public Roles(String role) {
		this.role = role;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Roles [role=").append(role).append("]");
		return builder.toString();
	}
	
	
}

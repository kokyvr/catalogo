package com.catalogo.productos.app.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "usuarios")
public class Usuario {

	@Id
	private String id;
	
	private String username;
	
	private String password;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaNacimiento;
	
	private Set<Roles> roles;
	
	protected String correo;
	
	protected String numeroTelefono;
	
	private boolean enabled;

	
	
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Usuario() {
		this.roles = new HashSet<>();
	}
	public void agregarRoles(Roles role) {
		this.roles.add(role);
	}

	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}




	public Usuario(String username, String password, Date fechaNacimiento, Set<Roles> roles, String correo,
			String numeroTelefono) {
		this.username = username;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
		this.roles = roles;
		this.correo = correo;
		this.numeroTelefono = numeroTelefono;
	}
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}






	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Set<Roles> getRoles() {
		return roles;
	}



	public void setRoles(Set<Roles> roles) {
		this.roles = roles;
	}
	
}

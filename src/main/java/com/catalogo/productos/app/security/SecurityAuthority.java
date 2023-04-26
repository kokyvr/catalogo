package com.catalogo.productos.app.security;

import org.springframework.security.core.GrantedAuthority;

import com.catalogo.productos.app.model.Roles;

public class SecurityAuthority implements GrantedAuthority{

	
	private static final long serialVersionUID = 1L;
	private final Roles role;
	
	public Roles getRole() {
		return role;
	}

	public SecurityAuthority(Roles role) {
		this.role = role;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return role.getRole().toString();
	}

}

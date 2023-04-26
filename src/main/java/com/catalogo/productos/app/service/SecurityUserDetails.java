package com.catalogo.productos.app.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.catalogo.productos.app.dao.UsuarioDao;
import com.catalogo.productos.app.model.Usuario;
import com.catalogo.productos.app.security.SecurityUser;

@Service
public class SecurityUserDetails implements UserDetailsService{

	@Autowired
	private UsuarioDao dao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Usuario>	usuarioOptional =	dao.findByUsername(username);
		
		if(usuarioOptional.isPresent()) {
			return new SecurityUser(usuarioOptional.get());
		}
		 throw new UsernameNotFoundException("Usuario no encontrado : " + username);
	}

	
	
}

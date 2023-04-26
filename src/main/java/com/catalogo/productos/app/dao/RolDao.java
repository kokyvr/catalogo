package com.catalogo.productos.app.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.catalogo.productos.app.model.Roles;

public interface RolDao extends MongoRepository<Roles, String>{
	
	Optional<Roles> findByRole(String role);
}

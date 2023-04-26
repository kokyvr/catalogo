package com.catalogo.productos.app.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.catalogo.productos.app.model.Usuario;

public interface UsuarioDao extends MongoRepository<Usuario, String>{

	Optional<Usuario> findByUsername(String username);
}

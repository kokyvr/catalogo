package com.catalogo.productos.app.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.catalogo.productos.app.model.Categoria;

public interface CategoriaDao extends MongoRepository<Categoria, String>{

	public Optional<Categoria> findByNombre(String nombre);
}

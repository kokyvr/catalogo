package com.catalogo.productos.app.dao;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.catalogo.productos.app.model.Producto;

public interface ProductoDao extends MongoRepository<Producto, String>{

	public Optional<Producto> findByNombre(String nombre);
}

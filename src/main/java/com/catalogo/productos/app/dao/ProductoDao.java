package com.catalogo.productos.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.catalogo.productos.app.model.Producto;

public interface ProductoDao extends MongoRepository<Producto, String>{

	@Query("field= {image:0}")
	public List<Producto> findAllWithOutImage();
	
	public Optional<Producto> findByNombre(String nombre);
}

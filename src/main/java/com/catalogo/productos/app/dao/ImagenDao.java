package com.catalogo.productos.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.catalogo.productos.app.model.Imagen;

public interface ImagenDao extends MongoRepository<Imagen, String> {

}

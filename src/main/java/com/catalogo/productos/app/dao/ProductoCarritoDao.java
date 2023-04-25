package com.catalogo.productos.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.catalogo.productos.app.model.ProductoCarrito;

public interface ProductoCarritoDao extends MongoRepository<ProductoCarrito, String>{

}

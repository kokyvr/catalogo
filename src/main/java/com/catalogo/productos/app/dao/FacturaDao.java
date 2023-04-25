package com.catalogo.productos.app.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.catalogo.productos.app.model.Factura;

public interface FacturaDao extends MongoRepository<Factura, String>{

}

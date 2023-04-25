package com.catalogo.productos.app.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.productos.app.inter.crud.ICrud;
import com.catalogo.productos.app.model.Producto;

@CrossOrigin("*")
@RequestMapping("/productos")
@RestController
public class ProductoController {

	@Qualifier("productoService")
	@Autowired
	private ICrud<Producto> productoService;
	
	@PostMapping
	public ResponseEntity<Producto> save(@RequestBody Producto producto){
		try {
			productoService.save(producto);
			return  ResponseEntity.created(URI.create("/productos/"+producto.getId())).body(producto);
			
		} catch (Exception e) {
		
			return ResponseEntity.badRequest().build();
		}
		
	
		
	}
}

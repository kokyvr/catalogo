package com.catalogo.productos.app.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.productos.app.inter.crud.ICrud;
import com.catalogo.productos.app.model.Categoria;

@CrossOrigin("*")
@RequestMapping("/categorias")
@RestController
public class CategoriaController {

	
	@Qualifier("categoriaService")
	@Autowired
	private ICrud<Categoria> categoriaService;
	
	public ResponseEntity<Categoria> save(@RequestBody Categoria categoria){
		try {
			categoriaService.save(categoria);
			
			return ResponseEntity.created(URI.create("/categorias"+ categoria.getId())).body(categoria);
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		
	}
}

package com.catalogo.productos.app.controller;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.productos.app.inter.crud.ICrud;
import com.catalogo.productos.app.model.Categoria;

@CrossOrigin("*")
@RequestMapping("/categorias")
@RestController
public class CategoriaController {

	private static final Logger log =LoggerFactory.getLogger(CategoriaController.class);

	
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
	
	@GetMapping
	public ResponseEntity<List<Categoria>> findAll(){
		try {
			List<Categoria> categoriAll = categoriaService.findAll();
			return ResponseEntity.ok(categoriAll);
			
		} catch (Exception e) {
		return ResponseEntity.noContent().build();
		}
	}
}

package com.catalogo.productos.app.controller;

import java.net.URI;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> update(@RequestBody Categoria categoria,@PathVariable String id){
		Map<String, Object> rpta = new HashMap<>();
		Categoria cat = categoriaService.update(categoria, id);
		if(Objects.nonNull(cat)) {
			
			rpta.put("categoria", cat);
			return ResponseEntity.created(URI.create("/categorias/" + cat.getId())).body(rpta);
		}
		rpta.put("message", "ID " + id + " No ENCONTRADO");
		rpta.put("timestamp", new Date());
		rpta.put("error-code", HttpStatus.NOT_FOUND);
		return new ResponseEntity<Map<String, Object>>(rpta,HttpStatus.NOT_FOUND);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id){
		try {
			categoriaService.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("/{nombre}")
	public ResponseEntity<Categoria> findByNombre(@PathVariable String nombre){
		Categoria cat = categoriaService.getByNombre(nombre);
		if(Objects.nonNull(cat)) 
			return ResponseEntity.ok(cat);
		
		
		return ResponseEntity.notFound().build();
	}
}

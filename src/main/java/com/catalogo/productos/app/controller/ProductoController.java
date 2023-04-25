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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	private static final Logger log = LoggerFactory.getLogger(ProductoController.class);

	@PostMapping
	public ResponseEntity<Producto> save(@RequestBody Producto producto) {
		try {
			productoService.save(producto);
			return ResponseEntity.created(URI.create("/productos/" + producto.getId())).body(producto);

		} catch (Exception e) {

			return ResponseEntity.badRequest().build();
		}

	}

	@GetMapping
	public ResponseEntity<List<Producto>> findAll() {
		try {
			List<Producto> productoAll = productoService.findAll();
			return ResponseEntity.ok(productoAll);

		} catch (Exception e) {
			return ResponseEntity.noContent().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Map<String, Object>> update(@RequestBody Producto producto, @PathVariable String id) {
		Map<String, Object> rpta = new HashMap<>();
		Producto p = productoService.update(producto, id);
		if (Objects.nonNull(p)) {

			rpta.put("producto", p);
			return ResponseEntity.created(URI.create("/productos/" + producto.getId())).body(rpta);
		}

		rpta.put("message", "ID " + id + " No ENCONTRADO");
		rpta.put("timestamp", new Date());
		rpta.put("error-code", HttpStatus.NOT_FOUND);
		return new ResponseEntity<Map<String, Object>>(rpta, HttpStatus.NOT_FOUND);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable String id) {
		try {
			productoService.deleteById(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}

	}

	@GetMapping("/{nombre}")
	public ResponseEntity<Producto> findByNombre(@PathVariable String nombre) {
		Producto p = productoService.getByNombre(nombre);
		if (Objects.nonNull(p)) {
			return ResponseEntity.ok(p);
		}
		return ResponseEntity.notFound().build();
	}

}

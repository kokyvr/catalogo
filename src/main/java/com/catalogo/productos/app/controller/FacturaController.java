package com.catalogo.productos.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.productos.app.inter.crud.ICrud;
import com.catalogo.productos.app.model.Factura;

@RequestMapping("/factura")
@RestController
public class FacturaController {

	@Qualifier("facturaService")
	@Autowired
	private ICrud<Factura> facturaService;

	private static final Logger log = LoggerFactory.getLogger(FacturaController.class);

	@PostMapping
	public ResponseEntity<Map<String, Object>> ventaSave(@RequestBody Factura factura) {
		Factura facturaBD = facturaService.save(factura);
		Map<String, Object> rpta = new HashMap<>();
		if (factura.getId() != null) {

			rpta.put("factura", facturaBD);
			return ResponseEntity.ok(rpta);
		}
		rpta.put("error", "Hubo un error al comprar");
		rpta.put("status", HttpStatus.BAD_REQUEST);
		rpta.put("tiemstamp", new Date());
		return ResponseEntity.badRequest().body(rpta);
		

	}

}

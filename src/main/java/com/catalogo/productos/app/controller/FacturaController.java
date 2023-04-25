package com.catalogo.productos.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

import com.catalogo.productos.app.inter.crud.ICarritoCrudAll;
import com.catalogo.productos.app.inter.crud.ICrud;
import com.catalogo.productos.app.inter.crud.ICrudProductoWithImage;
import com.catalogo.productos.app.model.Factura;
import com.catalogo.productos.app.model.Producto;
import com.catalogo.productos.app.model.ProductoCarrito;

@RequestMapping("/factura")
@RestController
public class FacturaController {

	@Qualifier("facturaService")
	@Autowired
	private ICrud<Factura> facturaService;

	@Autowired
	private ICrudProductoWithImage productoService;
	@Qualifier("productoCarritoService")
	@Autowired
	private ICarritoCrudAll<ProductoCarrito> productoCarritoService;

	private static final Logger log = LoggerFactory.getLogger(FacturaController.class);
	
	@PostMapping
	public ResponseEntity<Map<String, Object>> ventaSave(@RequestBody Factura factura) {
		Iterable<Producto> productos = productoService
				.findAllById(factura.getProductos().stream().map(ids -> ids.getId()).collect(Collectors.toSet()));
		factura.getProductos().stream().map(fa -> {
			productos.forEach(p -> {
				if (p.getId().equalsIgnoreCase(fa.getId())){
					fa.setPrecio(p.getPrecio());
					fa.setNombre(p.getNombre());
					fa.setTotal(fa.calcularTotal());
				}
			});
			
			return fa;
		});
		log.info("factura : {} " ,factura.toString());
		
		factura.setTotal(factura.getProductos().stream().mapToDouble(p->p.getTotal()).sum());
		Factura fabD = facturaService.save(factura);
		factura.getProductos().stream().forEach(p->p.setFactura(fabD));
		productoCarritoService.saveAll(fabD.getProductos().stream().collect(Collectors.toList()));
		Map<String, Object> rpta = new HashMap<>();
		if(Objects.nonNull(fabD)) {
			rpta.put("factura", fabD);
			
			return ResponseEntity.ok(rpta);
		}
		
		rpta.put("error", "Hubo un error al comprar");
		rpta.put("status", HttpStatus.BAD_REQUEST);
		rpta.put("tiemstamp", new Date());
		return ResponseEntity.badRequest().body(rpta);

	}


}

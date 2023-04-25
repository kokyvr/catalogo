package com.catalogo.productos.app.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

	@PostMapping
	public ResponseEntity<Map<String, Object>> ventaSave(@RequestBody Factura factura) {
		Set<Producto> productos = productoService
				.findAllById(factura.getProductos().stream().map(ids -> ids.getId()).collect(Collectors.toSet()));
		factura.getProductos().stream().map(fa -> {
			productos.stream().forEach(p -> {
				if (p.getId().equalsIgnoreCase(fa.getId())){
					fa.setPrecio(p.getPrecio());
					fa.setNombre(p.getNombre());
					fa.calcularTotal();
				}
			});
			return fa;
		});
		factura.setTotal(factura.getProductos().stream().mapToDouble(p->p.getTotal()).sum());
		Factura fabD = facturaService.save(factura);
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

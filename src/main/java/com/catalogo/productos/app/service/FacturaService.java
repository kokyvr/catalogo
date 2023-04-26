package com.catalogo.productos.app.service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catalogo.productos.app.dao.FacturaDao;
import com.catalogo.productos.app.dao.ProductoCarritoDao;
import com.catalogo.productos.app.dao.ProductoDao;
import com.catalogo.productos.app.inter.crud.ICrud;
import com.catalogo.productos.app.model.Factura;
import com.catalogo.productos.app.model.Producto;
import com.catalogo.productos.app.model.ProductoCarrito;

@Service
public class FacturaService implements ICrud<Factura> {
	
	@Autowired
	private ProductoDao productoService;
	
	@Autowired
	private ProductoCarritoDao productoCarritoService;

	@Autowired
	private FacturaDao dao;

	@Override
	public List<Factura> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Factura getById(String id) {
		// TODO Auto-generated method stub
		return dao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public Factura save(Factura data) {
		Iterable<Producto> productos = productoService.findAllById(
				
		data.getProductos().stream().map(ids -> ids.getIdProducto()).collect(Collectors.toSet()));
		if (productos.spliterator().getExactSizeIfKnown() > 0) {
			Set<ProductoCarrito> newProducto = data.getProductos().stream().map(fa -> {
				productos.forEach(p -> {
					if (p.getId().equalsIgnoreCase(fa.getIdProducto())) {
						fa.setIdProducto(p.getId());
						fa.setPrecio(p.getPrecio());
						fa.setNombre(p.getNombre());
						fa.setTotal(fa.calcularTotal());

						data.agregarProducto(fa);

					}
				});
				return fa;
			}).collect(Collectors.toSet());
			
			Factura fabD = dao.save(data);
			data.setProductos(newProducto.stream().map(np -> {
				np.setFactura(fabD);
				return np;
			}).collect(Collectors.toSet()));
			data.setTotal(data.getProductos().stream().mapToDouble(p -> p.getTotal()).sum());

			productoCarritoService.saveAll(fabD.getProductos().stream().collect(Collectors.toList()));
		
		}
			
		
		return data;

	}

	@Override
	public Factura update(Factura data, String id) {
		Factura factura = getById(id);
		if (Objects.nonNull(factura)) {
			factura.setCorreo(data.getCorreo());
			factura.setNumeroTelefonico(data.getNumeroTelefonico());
			factura.setProductos(data.getProductos());
			factura.setTipoPago(data.getTipoPago());
			factura.setTotal(data.getTotal());
		}

		return factura;
	}

	@Override
	public void deleteById(String id) {
		dao.findById(id);

	}

}

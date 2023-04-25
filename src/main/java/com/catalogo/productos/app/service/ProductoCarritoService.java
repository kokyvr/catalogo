package com.catalogo.productos.app.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.productos.app.dao.ProductoCarritoDao;
import com.catalogo.productos.app.inter.crud.ICarritoCrudAll;
import com.catalogo.productos.app.model.ProductoCarrito;

@Service("productoCarritoService")
public class ProductoCarritoService implements ICarritoCrudAll<ProductoCarrito>{

	@Autowired
	private ProductoCarritoDao dao;
	
	@Override
	public List<ProductoCarrito> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public ProductoCarrito getById(String id) {
		// TODO Auto-generated method stub
		return dao.findById(id).orElse(null);
	}

	@Override
	public ProductoCarrito save(ProductoCarrito data) {
		// TODO Auto-generated method stub
		return dao.save(data);
	}

	@Override
	public ProductoCarrito update(ProductoCarrito data, String id) {
		ProductoCarrito pr = getById(id);
		if(Objects.nonNull(pr)) {
			pr.setCantidad(data.getCantidad());
			pr.setNombre(data.getNombre());
			pr.setPrecio(data.getPrecio());
			dao.save(pr);
		}
		return pr;
	}

	@Override
	public void deleteById(String id) {
		dao.deleteById(id);
		
	}

	@Override
	public void saveAll(List<ProductoCarrito> data) {
			dao.saveAll(data);
		
	}





}

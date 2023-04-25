package com.catalogo.productos.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.productos.app.dao.ProductoDao;
import com.catalogo.productos.app.inter.crud.ICrud;
import com.catalogo.productos.app.model.Producto;

@Service
public class ProductoService implements ICrud<Producto>{

	@Autowired
	private ProductoDao dao;
	
	@Override
	public List<Producto> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Producto getById(String id) {
		// TODO Auto-generated method stub
		return dao.findById(id).orElse(null);
	}

	@Override
	public Producto save(Producto data) {
		Producto producto =dao.save(data);
		
		return producto;
	}

	@Override
	public Producto update(Producto data, String id) {
		Producto producto = getById(id);
		if(producto !=null) {
			producto.setNombre(data.getNombre());
			producto.setPrecio(data.getPrecio());
			producto.setStock(data.getStock());
			producto.setCategoria(data.getCategoria());
			
			dao.save(producto);
		}
		else {
			new RuntimeException("Producto no encontrado con id :" + id);
		}
		return producto;
		
	}

	@Override
	public void deleteById(String id) {
		dao.deleteById(id);
		
	}

	@Override
	public Producto getByNombre(String nombre) {
		// TODO Auto-generated method stub
		return dao.findByNombre(nombre).orElse(null);
	}

	
	
}

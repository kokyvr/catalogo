package com.catalogo.productos.app.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.productos.app.dao.ProductoDao;
import com.catalogo.productos.app.inter.crud.ICrudProductoWithImage;
import com.catalogo.productos.app.model.Producto;

@Service
public class ProductoService implements ICrudProductoWithImage{

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

	@Override
	public List<Producto> findAllProductosWithOutImage() {
		// TODO Auto-generated method stub
		return dao.findAllWithOutImage();
	}

	@Override
	public Set<Producto> findAllById(Set<String> ids) {
		// TODO Auto-generated method stub
		return (Set<Producto>) dao.findAllById(ids);
	}

	
	
}

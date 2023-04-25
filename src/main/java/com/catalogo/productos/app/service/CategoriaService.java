package com.catalogo.productos.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.productos.app.dao.CategoriaDao;
import com.catalogo.productos.app.inter.crud.ICrudFindByString;
import com.catalogo.productos.app.model.Categoria;

@Service
public class CategoriaService implements ICrudFindByString<Categoria>{

	@Autowired
	private CategoriaDao dao;
	
	@Override
	public List<Categoria> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Categoria getById(String id) {
		// TODO Auto-generated method stub
		return dao.findById(id).orElse(null);
	}

	@Override
	public Categoria save(Categoria data) {
		Categoria categoria = dao.save(data);
		
		return categoria;
	}

	@Override
	public Categoria update(Categoria data, String id) {
		Categoria categoria = getById(id);
		if(categoria !=null) {
			categoria.setNombre(data.getNombre());
			
			dao.save(categoria);
	
		}
		return categoria;
		
	}

	@Override
	public void deleteById(String id) {
		dao.deleteById(id);
		
	}

	@Override
	public Categoria getByNombre(String nombre) {
		// TODO Auto-generated method stub
		return dao.findByNombre(nombre).orElse(null);
	}

}

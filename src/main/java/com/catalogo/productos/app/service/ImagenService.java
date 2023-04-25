package com.catalogo.productos.app.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.catalogo.productos.app.dao.ImagenDao;
import com.catalogo.productos.app.inter.crud.ICrud;
import com.catalogo.productos.app.model.Imagen;

@Service
public class ImagenService implements ICrud<Imagen>{

	@Autowired
	private ImagenDao dao;
	
	@Override
	public List<Imagen> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Imagen getById(String id) {
		// TODO Auto-generated method stub
		return dao.findById(id).orElse(null);
	}

	@Override
	public Imagen save(Imagen data) {
		// TODO Auto-generated method stub
		return dao.save(data);
	}

	@Override
	public Imagen update(Imagen data, String id) {
		// TODO Auto-generated method stub
		Imagen img = getById(id);
		if(Objects.nonNull(img)) {
			img.setFoto(data.getFoto());
		}
		
		return img;
	}

	@Override
	public void deleteById(String id) {
		dao.deleteById(id);
		
	}



}

package com.catalogo.productos.app.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catalogo.productos.app.dao.FacturaDao;
import com.catalogo.productos.app.inter.crud.ICrud;
import com.catalogo.productos.app.model.Factura;

@Service
public class FacturaService implements ICrud<Factura>{

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
		// TODO Auto-generated method stub
		return dao.save(data);
	}

	@Override
	public Factura update(Factura data, String id) {
		Factura factura = getById(id);
		if(Objects.nonNull(factura)) {
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

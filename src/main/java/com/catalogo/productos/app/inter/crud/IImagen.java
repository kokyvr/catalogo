package com.catalogo.productos.app.inter.crud;

import java.util.List;

import com.catalogo.productos.app.model.Producto;

public interface IImagen {
	
	public List<Producto> findAllProductosWithOutImage();
	
}

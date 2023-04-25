package com.catalogo.productos.app.inter.crud;

import java.util.List;

public interface ISaveAll<T> {
	
	public void saveAll(List<T> data);
}

package com.catalogo.productos.app.inter.crud;

public interface ICrudFindByString<T> extends ICrud<T> {
	public T getByNombre(String nombre);
}

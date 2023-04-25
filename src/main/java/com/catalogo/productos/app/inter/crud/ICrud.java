package com.catalogo.productos.app.inter.crud;

import java.util.List;

public interface ICrud<T> {

	public List<T> findAll();
	
	public T getById(String id);
	
	public T save(T data);
	
	public T update (T data,String id);
	
	public void deleteById(String id);
	
	public T getByNombre(String nombre);
	
	
}

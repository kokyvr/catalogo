package com.catalogo.productos.app.inter.crud;

import java.util.Set;

public interface IFindByAllId<T> {

	public Set<T> findAllById(Set<String> ids);
}

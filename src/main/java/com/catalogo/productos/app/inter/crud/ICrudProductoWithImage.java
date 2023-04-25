package com.catalogo.productos.app.inter.crud;

import com.catalogo.productos.app.model.Producto;

public interface ICrudProductoWithImage extends ICrudFindByString<Producto>,IImagen,IFindByAllId<Producto>{

}

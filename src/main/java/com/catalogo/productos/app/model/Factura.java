package com.catalogo.productos.app.model;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "facturas")
public class Factura {

	public Set<ProductoCarrito> productos;
	
	public Double total;
	
	public String tipoPago;
	
	public String correo;
	
	public String numeroTelefonico;


	
	
	public void agregarProducto(ProductoCarrito productoCar) {
		this.productos.add(productoCar);
	}

	public Set<ProductoCarrito> getProductos() {
		return productos;
	}

	public void setProductos(Set<ProductoCarrito> productos) {
		this.productos = productos;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public String getTipoPago() {
		return tipoPago;
	}

	public void setTipoPago(String tipoPago) {
		this.tipoPago = tipoPago;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getNumeroTelefonico() {
		return numeroTelefonico;
	}

	public void setNumeroTelefonico(String numeroTelefonico) {
		this.numeroTelefonico = numeroTelefonico;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Factura [productos=").append(productos).append(", total=").append(total).append(", tipoPago=")
				.append(tipoPago).append(", correo=").append(correo).append(", numeroTelefonico=")
				.append(numeroTelefonico).append("]");
		return builder.toString();
	}
	
}

package com.catalogo.productos.app.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "producto_carro")
public class ProductoCarrito {

	@Id
	private String id;
	
	private String nombre;
	
	private Integer cantidad;
	
	private Double precio;
	
	private Double total;
	
	private Factura factura;
	
	
	


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductoCarrito other = (ProductoCarrito) obj;
		return Objects.equals(id, other.id);
	}

	public ProductoCarrito(String nombre, Integer cantidad, Double precio, Factura factura) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
		this.factura = factura;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public void calcularTotal() {
		this.total = this.precio * this.cantidad;
	}

	public Factura getFactura() {
		return factura;
	}

	public void setFactura(Factura factura) {
		this.factura = factura;
	}

	public ProductoCarrito() {
	}

	public ProductoCarrito(Integer cantidad, Double total) {
		this.cantidad = cantidad;
		this.total = total;
	}

	public ProductoCarrito(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Integer getCantidad() {
		return cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Double getTotal() {
		return total;
	}


	
}

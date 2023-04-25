package com.catalogo.productos.app.model;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "producto_carro")
public class ProductoCarrito {

	@Id
	private String id;
	
	private String idProducto;
	
	private String nombre;
	
	private Integer cantidad;
	
	private Double precio;
	
	private Double total;
	
	
	@JsonIgnore
	@DBRef(lazy = false)
	private Factura factura;








	public ProductoCarrito(String idProducto, String nombre, Integer cantidad, Double precio, Double total,
			Factura factura) {
		this.idProducto = idProducto;
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
		this.total = total;
		this.factura = factura;
	}



	public String getIdProducto() {
		return idProducto;
	}



	public void setIdProducto(String idProducto) {
		this.idProducto = idProducto;
	}





	@Override
	public int hashCode() {
		return Objects.hash(idProducto);
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
		return Objects.equals(idProducto, other.idProducto);
	}



	public ProductoCarrito(String nombre, Integer cantidad, Double precio) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public void setTotal(Double total) {
		this.total = total;
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

	public double calcularTotal() {
		return this.precio * this.cantidad;
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



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ProductoCarrito [id=").append(id).append(", idProducto=").append(idProducto).append(", nombre=")
				.append(nombre).append(", cantidad=").append(cantidad).append(", precio=").append(precio)
				.append(", total=").append(total).append(", factura=").append(factura).append("]");
		return builder.toString();
	}


	
}

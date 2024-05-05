package com.coderhouse.modelos;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Schema(description = "Modelo de Producto")
@SuppressWarnings("serial")
@Entity
@Table(name = "productos")
public class Producto implements Serializable{

	@Schema(description = "Id del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id
	@Column(name = "id_producto")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Schema(description = "Nombre del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "Jabon")
	@Column(name = "nombre_producto")
	private String nombreProducto;
	@Schema(description = "Precio del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "8.0")
	@Column(name = "precio")
	private double precio;
	@Schema(description = "Stock del producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "4")
	@Column(name = "stock")
	private int stock;
	@Schema(description = "Cantidad de productos", requiredMode = Schema.RequiredMode.REQUIRED, example = "4")
	@Column(name = "cantidad")
	private int cantidad;

	@Schema(description = "Id del cliente que compra el producto")
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente cliente;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_compra")
	private Compra compra;

	public Producto() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Compra getCompra() {
		return compra;
	}

	public void setCompra(Compra compra) {
		this.compra = compra;
	}

}

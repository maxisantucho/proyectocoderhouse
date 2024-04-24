package com.coderhouse.modelos;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "compras")
public class Compra {
	
	@Id
	@Column(name = "id_compra")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "total_productos")
	private int totalProductos;
	@Column(name = "total_compra")
	private double totalCompra;
	@Column(name = "fecha_compra")
	private LocalDate fechaCompra;
	
	private Cliente cliente;
	private List<Producto> productos;
	
	public Compra() {
		super();
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getTotalProductos() {
		return totalProductos;
	}
	
	public void setTotalProductos(int totalProductos) {
		this.totalProductos = totalProductos;
	}
	
	public double getTotalCompra() {
		return totalCompra;
	}
	
	public void setTotalCompra(double totalCompra) {
		this.totalCompra = totalCompra;
	}
	
	public LocalDate getFechaCompra() {
		return fechaCompra;
	}
	
	public void setFechaCompra(LocalDate fechaCompra) {
		this.fechaCompra = fechaCompra;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public List<Producto> getProductos() {
		return productos;
	}
	
	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

}

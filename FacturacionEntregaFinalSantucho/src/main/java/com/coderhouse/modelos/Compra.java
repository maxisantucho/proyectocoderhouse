package com.coderhouse.modelos;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "compras")
public class Compra implements Serializable{
	
	@Id
	@Column(name = "id_compra")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "total_productos")
	private int totalProductos;
	@Column(name = "total_compra")
	private double totalCompra;
	
	
	private Cliente cliente;
	
	@OneToMany(mappedBy = "compra")
	@Column(name = "producto")
	private List<Producto> productos;
	
	@OneToOne
	@JoinColumn(name = "id_comprobante")
	private Comprobante comprobante;
	
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

	public Comprobante getComprobante() {
		return comprobante;
	}

	public void setComprobante(Comprobante comprobante) {
		this.comprobante = comprobante;
	}
	
}

package com.coderhouse.modelos;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Schema(description = "Modelo de Compra")
@SuppressWarnings("serial")
@Entity
@Table(name = "compras")
public class Compra implements Serializable{

	@Schema(description = "Id de al compra", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id
	@Column(name = "id_compra")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "total_productos")
	private int totalProductos;
	@Column(name = "total_compra")
	private double totalCompra;
	

	@Schema(description = "Cliente que compra", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Column(name = "id_cliente", length = 1200)
	private Cliente cliente;

	@JsonIgnore
	@OneToMany(mappedBy = "compra")
	@Column(name = "producto")
	private List<Producto> productos;

	@JsonIgnore
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

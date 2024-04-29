package com.coderhouse.modelos;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "comprobantes")
public class Comprobante implements Serializable{
	
	@Id
	@Column(name = "id_comprobante")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "total")
	private double total;
	@Column(name = "fecha_compra")
	private LocalDateTime fechaCompra;
	
	private Cliente cliente;
	
	@OneToOne(mappedBy = "comprobante")
	private Compra compra;
	
	public Comprobante() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	public LocalDateTime getFechaCompra() {
		return fechaCompra;
	}
	
	public void setFechaCompra(LocalDateTime fechaCompra) {
		this.fechaCompra = fechaCompra;
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

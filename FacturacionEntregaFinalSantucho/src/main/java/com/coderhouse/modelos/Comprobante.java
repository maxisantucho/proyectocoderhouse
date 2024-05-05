package com.coderhouse.modelos;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Schema(description = "Modelo de Comprobante")
@SuppressWarnings("serial")
@Entity
@Table(name = "comprobantes")
public class Comprobante implements Serializable{

	@Schema(description = "Id del comprobante", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id
	@Column(name = "id_comprobante")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Schema(description = "Total final de la compra", requiredMode = Schema.RequiredMode.REQUIRED, example = "30.0")
	@Column(name = "total")
	private double total;
	@Schema(description = "Fecha actual de la compra", requiredMode = Schema.RequiredMode.REQUIRED, example = "2024-3-5T10:45:33")
	@Column(name = "fecha_compra")
	private LocalDateTime fechaCompra;

	@Schema(description = "Comprobante del cliente")
	private Cliente cliente;

	@Schema(description = "Comprobante de la compra")
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

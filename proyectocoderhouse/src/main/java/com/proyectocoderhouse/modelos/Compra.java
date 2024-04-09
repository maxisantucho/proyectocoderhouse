package com.proyectocoderhouse.modelos;

import java.time.Instant;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "compras")
public class Compra {
	
	@Id
	@Column(name = "id_compra")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_compra;
	@Column(name = "total")
	private double total;
	@Column(name = "fecha_compra")
	private Instant fecha_compra;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente id_cliente;
	
	@OneToMany(mappedBy = "id_compra")
	private List<DetalleCompra> detalle;

	public Compra() {
		
	}

	public int getId_compra() {
		return id_compra;
	}

	public void setId_compra(int id_compra) {
		this.id_compra = id_compra;
	}
	
	public Instant getFecha_compra() {
		fecha_compra = Instant.now();
		return fecha_compra;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<DetalleCompra> getDetalle() {
		return detalle;
	}

	public void setDetalle(List<DetalleCompra> detalle) {
		this.detalle = detalle;
	}
	
}

package com.proyectocoderhouse.modelos;

import java.time.Instant;
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

@Entity
@Table(name = "compras")
public class Compra {
	
	@Id
	@Column(name = "id_compra")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_compra;
	@Column(name = "cantidad")
	private double cantidad;
	@Column(name = "fecha_compra")
	private Instant fecha_compra;
	
	@OneToOne
	@JoinColumn(name = "id_detalle")
	private DetalleCompra detalle;
	
	@OneToMany(mappedBy = "compra")
	private List<Producto> productos;

	public Compra() {
		
	}

	public int getId_compra() {
		return id_compra;
	}

	public void setId_compra(int id_compra) {
		this.id_compra = id_compra;
	}
	
	public double getCantidad() {
		return cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	
	public Instant getFecha_compra() {
		fecha_compra = Instant.now();
		return fecha_compra;
	}

}

package com.proyectocoderhouse.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "detalles")
public class DetalleCompra {
	
	@Id
	@Column(name = "id_detalle")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_detalle;
	@Column(name = "total")
	private double total;
	
	@OneToOne(mappedBy = "detalle")
	private Compra compra;
	
	@OneToOne(mappedBy = "detalle")
	private Cliente cliente;
	
	public DetalleCompra() {
		
	}

	public int getId_detalle() {
		return id_detalle;
	}

	public void setId_detalle(int id_detalle) {
		this.id_detalle = id_detalle;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

}

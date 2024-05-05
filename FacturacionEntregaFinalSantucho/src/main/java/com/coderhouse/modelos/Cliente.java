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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Schema(description = "Modelo de Cliente")
@SuppressWarnings("serial")
@Entity
@Table(name = "clientes")
public class Cliente implements Serializable{

	@Schema(description = "Id del cliente", example = "1")
	@Id
	@Column(name = "id_cliente")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Schema(description = "Nombre del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Maximiliano")
	@Column(name = "nombre")
	private String nombre;
	@Schema(description = "Apellido del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "Santucho")
	@Column(name = "apellido")
	private String apellido;
	@Schema(description = "DNI del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "78743421")
	@Column(name = "dni")
	private String dni;
	@Schema(description = "Email del cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "maxisantucho@gmail.com")
	@Column(name = "email")
	private String email;

	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Producto> productos;
	
	
	public Cliente() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
	
}

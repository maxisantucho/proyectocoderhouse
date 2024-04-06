package com.proyectocoderhouse.servicios;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.proyectocoderhouse.modelos.Cliente;
import com.proyectocoderhouse.repositorios.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> listarClientes() {
		List<Cliente> listaClientes = clienteRepository.findAll();
		listaClientes.forEach(this::calcularEdad);
		return listaClientes;
	}
	
	public Cliente mostrarClientePorId(int id) {
		Cliente cliente = clienteRepository.findById(id).orElse(null);
		calcularEdad(cliente);
		return cliente;
	}
	
	public Cliente agregarCliente(Cliente cliente) {
		return clienteRepository.save(cliente);
	}
	
	public Cliente editarClientePorId(int id, Cliente cliente) {
		try {
			if(clienteRepository.existsById(id)) {
				cliente.setId(id);
				return clienteRepository.save(cliente);
			}
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
		return null;
	}
	
	public boolean eliminarClientePorId(int id) {
		try {
			clienteRepository.deleteById(id);
			return true;
		} catch(EmptyResultDataAccessException e) {
			return false;
		}
	}
	
	public void calcularEdad(Cliente cliente) {
		LocalDate fecha_actual = LocalDate.now();
		if ((cliente.getFecha_nacimiento() != null) && (fecha_actual != null)) {
			int edad = Period.between(cliente.getFecha_nacimiento(), fecha_actual).getYears();
			cliente.setEdad(edad);
		}  
	}

}
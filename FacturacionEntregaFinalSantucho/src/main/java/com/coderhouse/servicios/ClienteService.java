package com.coderhouse.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.coderhouse.modelos.Cliente;
import com.coderhouse.repositorios.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public List<Cliente> listarClientes() {
		List<Cliente> listaClientes = clienteRepository.findAll();
		return listaClientes;
	}
	
	public Cliente mostrarClientePorId(int id) {
		Cliente cliente = clienteRepository.findById(id).orElse(null);
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

}

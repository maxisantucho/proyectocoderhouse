package com.proyectocoderhouse.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.proyectocoderhouse.modelos.Cliente;
import com.proyectocoderhouse.modelos.Producto;
import com.proyectocoderhouse.repositorios.ClienteRepository;

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
		List<Producto> productoLista = cliente.getProductos();
		cliente.setProductos(productoLista);
		return clienteRepository.save(cliente);
		
		/*
		 List<Producto> productoLista = cliente.getProductos();
		if(productoLista.get(cliente.getId_cliente()) == null) {
			cliente.setProductos(productoLista);
			return clienteRepository.save(cliente);
		}
		return null;
		*/
	}
	
	public Cliente editarClientePorId(int id, Cliente cliente) {
		try {
			if(clienteRepository.existsById(id)) {
				cliente.setId_cliente(id);
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
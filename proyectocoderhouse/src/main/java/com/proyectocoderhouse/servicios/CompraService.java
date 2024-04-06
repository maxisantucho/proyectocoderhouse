package com.proyectocoderhouse.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.proyectocoderhouse.modelos.Compra;
import com.proyectocoderhouse.repositorios.CompraRepository;

@Service
public class CompraService {
	
	@Autowired
	private CompraRepository compraRepository;
	
	public List<Compra> listarCompra() {
		return compraRepository.findAll();
	}
	
	public Compra mostrarCompraPorId(int id) {
		return compraRepository.findById(id).orElse(null);
	}
	
	public Compra agregarCompra(Compra compra) {
		return compraRepository.save(compra);
	}
	
	public Compra editarCompraPorId(int id, Compra compra) {
		try {
			if(compraRepository.existsById(id)) {
				compra.setId(id);
				return compraRepository.save(compra);
			}
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
		return null;
	}
	
	public boolean eliminarCompraPorId(int id) {
		try {
			compraRepository.deleteById(id);
			return true;
		} catch(EmptyResultDataAccessException e) {
			return false;
		}
	}

}

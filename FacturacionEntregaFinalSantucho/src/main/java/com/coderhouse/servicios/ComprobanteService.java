package com.coderhouse.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.modelos.Comprobante;
import com.coderhouse.repositorios.ComprobanteRepository;

@Service
public class ComprobanteService {
	
	@Autowired
	private ComprobanteRepository comprobanteRepository;
	
	public List<Comprobante> listarComprobantes() {
		List<Comprobante> listaClientes = comprobanteRepository.findAll();
		return listaClientes;
	}
	
	public Comprobante mostrarComprobantePorId(int id) {
		Comprobante comprobante = comprobanteRepository.findById(id).orElse(null);
		return comprobante;
	}

}

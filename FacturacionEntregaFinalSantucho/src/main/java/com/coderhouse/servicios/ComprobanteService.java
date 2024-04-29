package com.coderhouse.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.modelos.Cliente;
import com.coderhouse.modelos.Compra;
import com.coderhouse.modelos.Comprobante;
import com.coderhouse.repositorios.ClienteRepository;
import com.coderhouse.repositorios.CompraRepository;
import com.coderhouse.repositorios.ComprobanteRepository;

@Service
public class ComprobanteService {
	
	@Autowired
	private ComprobanteRepository comprobanteRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private CompraRepository compraRepository;
	
	@Autowired
	private FechaService fechaService;
	
	public List<Comprobante> listarComprobantes() {
		List<Comprobante> listaComprobantes = comprobanteRepository.findAll();
		return listaComprobantes;
	}
	
	public Comprobante mostrarComprobantePorId(int id) {
		Comprobante comprobante = comprobanteRepository.findById(id).orElse(null);
		return comprobante;
	}
	
	public Comprobante registrarComprobante(Comprobante comprobante) {
		Cliente cliente = clienteRepository.findById(comprobante.getCliente().getId()).orElseThrow(IllegalArgumentException::new);
		comprobante.setCliente(cliente);
		Compra compra = compraRepository.findById(comprobante.getCompra().getId()).orElseThrow(IllegalArgumentException::new);
		comprobante.setCompra(compra);
		comprobante.setTotal(compra.getTotalCompra());
		comprobante.setFechaCompra(fechaService.fechaActualComprobante());
		return comprobanteRepository.save(comprobante);
	}

}

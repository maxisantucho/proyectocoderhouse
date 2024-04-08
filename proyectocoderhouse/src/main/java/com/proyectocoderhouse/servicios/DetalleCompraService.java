package com.proyectocoderhouse.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectocoderhouse.modelos.DetalleCompra;
import com.proyectocoderhouse.repositorios.DetalleCompraRepository;

@Service
public class DetalleCompraService {
	
	@Autowired
	private DetalleCompraRepository detalleCompraRepository;
	
	public DetalleCompra agregarDetalleCompra(DetalleCompra detalleCompra) {
		return detalleCompraRepository.save(detalleCompra);
	}

}

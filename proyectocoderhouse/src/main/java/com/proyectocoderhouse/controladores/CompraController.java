package com.proyectocoderhouse.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyectocoderhouse.modelos.Compra;
import com.proyectocoderhouse.servicios.CompraService;

@RestController
@RequestMapping("/compras")
public class CompraController {
	
	@Autowired
	private CompraService compraService;
	
	@GetMapping(value = "/", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<Compra>> listarCompra() {
		try {
			List<Compra> compras = compraService.listarCompra();
			return new ResponseEntity<>(compras, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Compra> mostrarCompraPorId(@PathVariable("id") int id) {
		try {
			Compra compra = compraService.mostrarCompraPorId(id);
			if(compra != null) {
				return new ResponseEntity<>(compra, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/agregar", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Compra> agregarCompra(@RequestBody Compra compra) {
		compraService.agregarCompra(compra);
		return new ResponseEntity<>(compra, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}/editar", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Compra> editarCompraPorId(@PathVariable("id") int id, @RequestBody Compra compra) {
		try {
			Compra compraEditada = compraService.editarCompraPorId(id, compra);
			return new ResponseEntity<>(compraEditada, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/{id}/eliminar", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> eliminarCompraPorId(@PathVariable("id") int id) {
		try {
			compraService.eliminarCompraPorId(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch(EmptyResultDataAccessException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

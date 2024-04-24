package com.coderhouse.controladores;

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

import com.coderhouse.servicios.ProductoService;
import com.coderhouse.modelos.Producto;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;

	@GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Producto>> listarProducto() {
		try {
			List<Producto> producto = productoService.listarProducto();
			return new ResponseEntity<>(producto, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Producto> mostrarProductoPorId(@PathVariable("id") int id) {
		try {
			Producto producto = productoService.mostrarProductoPorId(id);
			if(producto != null) {
				return new ResponseEntity<>(producto, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PostMapping(value = "/agregar", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
		productoService.agregarProducto(producto);
		return new ResponseEntity<>(producto, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{id}/editar", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Producto> editarProductoPorId(@PathVariable("id") int id, @RequestBody Producto producto) {
		try {
			Producto productoEditado = productoService.editarProductoPorId(id, producto);
			return new ResponseEntity<>(productoEditado, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping(value = "/{id}/eliminar", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> eliminarProductoPorId(@PathVariable("id") int id) {
		try {
			productoService.eliminarProductoPorId(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch(EmptyResultDataAccessException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}

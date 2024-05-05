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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.media.Schema;

import com.coderhouse.modelos.Producto;

@RestController
@RequestMapping("/productos")
@Tag(name = "Gestion de Productos", description = "Endpoints para controlar productos")
public class ProductoController {
	
	@Autowired
	private ProductoService productoService;

	@Operation(summary = "Obtener lista de productos")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista de productos fue obtenida correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))}),
		@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)})
	@GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Producto>> listarProducto() {
		try {
			List<Producto> producto = productoService.listarProducto();
			return new ResponseEntity<>(producto, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Obtener producto por ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Producto encontrado correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))}),
		@ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)})
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
	
	@Operation(summary = "Agregar nuevo producto")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Producto agregado correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))}),
		@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)})
	@PostMapping(value = "/agregar", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Producto> agregarProducto(@RequestBody Producto producto) {
		productoService.agregarProducto(producto);
		return new ResponseEntity<>(producto, HttpStatus.CREATED);
	}
	
	@Operation(summary = "Editar producto existente")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Producto editado correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))}),
		@ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)})
	@PutMapping(value = "/{id}/editar", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Producto> editarProductoPorId(@PathVariable("id") int id, @RequestBody Producto producto) {
		try {
			Producto productoEditado = productoService.editarProductoPorId(id, producto);
			return new ResponseEntity<>(productoEditado, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Eliminar producto existente")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Producto eliminado correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class))}),
		@ApiResponse(responseCode = "404", description = "Producto no encontrado", content = @Content)})
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

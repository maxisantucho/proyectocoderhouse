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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.modelos.Compra;
import com.coderhouse.servicios.CompraService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/compras")
@Tag(name = "Gestion de Compras", description = "Endpoints para controlar compras")
public class CompraController {
	
	@Autowired
	private CompraService compraService;
	
	@Operation(summary = "Obtener lista de compras")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista de compras fue obtenida correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Compra.class))}),
		@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)})
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Compra>> listarCompras() {
		try {
			List<Compra> compras = compraService.listarCompras();
			return new ResponseEntity<>(compras, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Obtener compra por ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Compra encontrada correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Compra.class))}),
		@ApiResponse(responseCode = "404", description = "Compra no encontrado", content = @Content)})
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> mostrarCompraPorId(@PathVariable int id) {
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
	
	@Operation(summary = "Agregar nueva compra")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Compra agregada correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Compra.class))}),
		@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)})
	@PostMapping(value = "/agregar", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Compra> agregarCompra(@RequestBody Compra compra) {
		try {
			Compra hacerCompra = compraService.agregarCompra(compra);
			return new ResponseEntity<>(hacerCompra, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Eliminar compra existente")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Compra eliminada correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Compra.class))}),
		@ApiResponse(responseCode = "404", description = "Compra no encontrada", content = @Content)})
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

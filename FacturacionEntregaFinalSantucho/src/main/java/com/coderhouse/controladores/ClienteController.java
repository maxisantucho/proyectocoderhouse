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

import com.coderhouse.servicios.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.coderhouse.modelos.Cliente;

@RestController
@RequestMapping("/clientes")
@Tag(name = "Gestion de Clientes", description = "Endpoints para controlar clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@Operation(summary = "Obtener lista de clientes")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista de clientes fue obtenida correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))}),
		@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)})
	@GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Cliente>> listarClientes() {
		try {
			List<Cliente> cliente = clienteService.listarClientes();
			return new ResponseEntity<>(cliente, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Obtener cliente por ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Cliente encontrado correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))}),
		@ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)})
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Cliente> mostrarClientePorId(@PathVariable("id") int id) {
		try {
			Cliente cliente = clienteService.mostrarClientePorId(id);
			if(cliente != null) {
				return new ResponseEntity<>(cliente, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Agregar nuevo cliente")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "201", description = "Cliente agregado correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))}),
		@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)})
	@PostMapping(value = "/agregar", consumes = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Cliente> agregarCliente(@RequestBody Cliente cliente) {
		clienteService.agregarCliente(cliente);
		return new ResponseEntity<>(cliente, HttpStatus.CREATED);
	}
	
	@Operation(summary = "Editar cliente existente")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Cliente editado correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))}),
		@ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)})
	@PutMapping(value = "/{id}/editar", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Cliente> editarClientePorId(@PathVariable("id") int id, @RequestBody Cliente cliente) {
		try {
			Cliente clienteEditado = clienteService.editarClientePorId(id, cliente);
			return new ResponseEntity<>(clienteEditado, HttpStatus.OK);
		} catch(Exception e){
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Eliminar cliente existente")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "204", description = "Cliente eliminado correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class))}),
		@ApiResponse(responseCode = "404", description = "Cliente no encontrado", content = @Content)})
	@DeleteMapping(value = "/{id}/eliminar", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> eliminarClientePorId(@PathVariable("id") int id) {
		try {
			clienteService.eliminarClientePorId(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch(EmptyResultDataAccessException e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

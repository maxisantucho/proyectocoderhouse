package com.coderhouse.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.modelos.Comprobante;
import com.coderhouse.servicios.ComprobanteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/comprobantes")
@Tag(name = "Gestion de Comprobantes", description = "Endpoints para controlar comprobantes")
public class ComprobanteController {
	
	@Autowired
	private ComprobanteService comprobanteService;
	
	@PostMapping
    public ResponseEntity<?> realizarVenta(@RequestBody Comprobante comprobante) {
		try {
			Comprobante comprobanteCompra = comprobanteService.registrarComprobante(comprobante);
			if(comprobanteCompra != null) {
				return new ResponseEntity<>(comprobante, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Obtener lista de comprobantes")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Lista de comprobantes fue obtenida correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Comprobante.class))}),
		@ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)})
	@GetMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Comprobante>> listarComprobantes() {
		try {
			List<Comprobante> comprobante = comprobanteService.listarComprobantes();
			return new ResponseEntity<>(comprobante, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@Operation(summary = "Obtener comprobante por ID")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Comprobante encontrado correctamente", content = {
				@Content(mediaType = "application/json", schema = @Schema(implementation = Comprobante.class))}),
		@ApiResponse(responseCode = "404", description = "Comprobante no encontrado", content = @Content)})
	@GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Comprobante> mostrarProductoPorId(@PathVariable("id") int id) {
		try {
			Comprobante comprobante = comprobanteService.mostrarComprobantePorId(id);
			if(comprobante != null) {
				return new ResponseEntity<>(comprobante, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}

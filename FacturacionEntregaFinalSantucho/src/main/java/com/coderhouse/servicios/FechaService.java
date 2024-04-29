package com.coderhouse.servicios;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FechaService {
	
	private final static DateTimeFormatter FORMATO = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm'Z'");
    private final static String URL_API = "http://worldclockapi.com/api/json/utc/now";

    public LocalDateTime fechaActualComprobante() {
    	LocalDateTime fecha;
    	try {
    		RestTemplate restTemplate = new RestTemplate();
    		ResponseEntity<String> sentencia = restTemplate.getForEntity(URL_API, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(sentencia.getBody());
            String fechaEnString = root.path("currentDateTime").asText();
            fecha = LocalDateTime.parse(fechaEnString, FORMATO);
    	} catch (Exception e) {
    		fecha = LocalDateTime.now();
    	}
    	return fecha;
    }
    
}

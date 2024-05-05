package com.coderhouse.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {
	
	@Bean
	OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Proyecto Final | Java | Spring Boot")
						.version("1.0.0")
						.description("Proyecto CoderHouse, aplicacion de negocio para administrar ventas. "
								+ "Se pueden realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar)"
								+ " tanto de cliente, producto, compra y comprobante mediante estos endpoints. "
								+ "Esta API RESTful se hizo utilizando Java, Spring Boot y Swagger para facilitar su uso.")
						.contact(new Contact()
								.name("Maximiliano Santucho")
								.email("maximilianosantucho@outlook.es")
								.url("https://github.com/maxisantucho"))
						.license(new License()
								.name("Licencia")
								.url("https://github.com/maxisantucho/proyectocoderhouse/tree/main/FacturacionEntregaFinalSantucho"))
						)
						.servers(List.of(new Server()
								.url("http://localhost:8080")
								.description("Servidor Local")
								));
	}

}

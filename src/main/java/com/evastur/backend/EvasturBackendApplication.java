package com.evastur.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Clase de entrada a la aplicación. Cuando se ejecuta esta aplicación se lanza el método main de esta clase.
 */
@SpringBootApplication
public class EvasturBackendApplication {

	/**
	 * Punto de entrada a la aplicación.
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(EvasturBackendApplication.class, args);
	}

}

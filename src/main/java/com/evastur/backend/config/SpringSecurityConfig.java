package com.evastur.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.evastur.backend.entities.Usuario;

/**
 * Configuración de Spring Security, empleada para controlar que usuarios pueden acceder a los distintos
 * tipos de endpoints incluidos en el servicio Rest del Backend. 
 * También se definen en este fichero los endpoints para hacer login y logout con los usuarios y el tipo de
 * cifrador de contraseña que vamos a emplear.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	/**
	 * Devuelve el cifrador de contraseñas que usaremos en esta aplicación.
	 * @return cifrador de contraseñas.
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	/**
	 * Realiza la configuración principal de Spring security.
	 * @param http Objeto sobre el que se realizará la configuración.
	 * @return Configuración completada.
	 * @throws Exception Si se produce algún error al configurar saltará esta excepción.
	 */
 	@Bean
 	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
 		http
 			.csrf(conf -> conf.disable())
 			.authorizeHttpRequests( auth -> auth
 					.requestMatchers(HttpMethod.POST, "/api/v1/ropa/nueva").hasAnyRole("ALMACEN")
 					.requestMatchers(HttpMethod.POST, "/api/v1/ubicacion/nueva").hasAnyRole("ALMACEN")
 					
 					.requestMatchers(HttpMethod.GET, "/api/v1/ubicacion").hasAnyRole("ALMACEN", "TRABAJADOR")
 					 					
 					.requestMatchers(HttpMethod.PATCH, "/api/v1/ropa/*/prenda/*/incstock").hasAnyRole("ALMACEN")
 					.requestMatchers(HttpMethod.PATCH, "/api/v1/ropa/*/prenda/*/decstock").hasAnyRole("ALMACEN", "TRABAJADOR")
 					.anyRequest().permitAll()
 			)
 			.formLogin(conf -> conf
 					.usernameParameter("nombre")
 					.passwordParameter("clave")
 					.loginProcessingUrl("/api/v1/login")
 					.successHandler((request, response, authentication) -> {
 						response.setStatus(200);
 						Usuario usuario = (Usuario)authentication.getPrincipal();
 						response.getOutputStream().println("{ \"rol\": \""+usuario.getRol()+"\" }");
 					})
 					.failureHandler((request, response, authentication) -> {
 						response.setStatus(401);
  					})
 					.permitAll())
 			.logout(conf -> conf
 					.logoutUrl("/api/v1/logout")
 					.logoutSuccessHandler((request, response, authentication) -> {
 						response.setStatus(200);
 					})
 					.permitAll())
 			.exceptionHandling(conf -> conf
 					.authenticationEntryPoint((request, response, authentication) -> {
 						response.setStatus(403);
 					}));
 		
 		return http.build();
 	}

}

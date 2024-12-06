package com.evastur.backend.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.evastur.backend.entities.Usuario;
import com.evastur.backend.repositories.UsuarioRepository;

/**
 * Servicio usado por Spring Security para recuperar la información del usuario
 * a partir del nombre para utilizar durante el proceso de login.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	/**
	 * Repositorio para acceder a la información almacenada en la tabla usuarios.
	 */
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/**
	 * Recupera un usuario a partir de su nombre. Este método será llamado por Spring Security
	 * durante el proceso de login para acceder a la información del usuario asociado al nombre
	 * que se indico en el login.
	 * @throws UsernameNotFoundExcepcion Si el usuario no se encuentra.
	 * @returns Información para completar el login.
	 */
	@Override
	public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {

		Optional<Usuario> optUsuario = usuarioRepository.findByNombre(nombre);
		if (optUsuario.isEmpty()) {
			throw new UsernameNotFoundException("Usuario no encontrado");
		}
		
		return optUsuario.get();
	}

}

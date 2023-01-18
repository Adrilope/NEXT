package com.adrian.proyecto.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.adrian.proyecto.datos.usuarios.Usuario;
import com.adrian.proyecto.datos.usuarios.UsuarioDAO;


@Service
public class Autenticacion implements UserDetailsService  {

	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Optional<Usuario> user = usuarioDAO.findById(username);
	
		if (user.isPresent()) { //si el usuario esta presente lo devuelve
			return user.get();
		}
		else throw new UsernameNotFoundException(""+username);	//si no, lanza la excepcion
	}

}
package com.adrian.proyecto.datos.usuarios;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioDAO extends CrudRepository<Usuario, String> {
	
	
}

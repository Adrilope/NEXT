package com.adrian.proyecto.datos.roles;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.adrian.proyecto.datos.usuarios.Usuario;

@Entity
public class Rol {
	
	@Id
	private String nombre = "USER";
	
	
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "rol")
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	
	

	public void addUsuario(Usuario usuario) {
		if(!usuarios.contains(usuario)) {
			usuarios.add(usuario);
		}
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}



	@Override
	public String toString() {
		return "Rol [nombre=" + nombre + "]";
	}	
	
	
	
	
	
}

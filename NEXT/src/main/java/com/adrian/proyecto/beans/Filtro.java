package com.adrian.proyecto.beans;

import java.util.List;


public class Filtro {
	
	private String nombre;
	private List<String> valores;
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public List<String> getValores() {
		return valores;
	}
	public void setValores(List<String> valores) {
		this.valores = valores;
	}
	
	
	
	@Override
	public String toString() {
		return "Filtro [nombre=" + nombre + ", valores=" + valores + "]";
	}
}

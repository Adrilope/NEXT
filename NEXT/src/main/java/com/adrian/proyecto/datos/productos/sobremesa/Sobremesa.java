package com.adrian.proyecto.datos.productos.sobremesa;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import com.adrian.proyecto.datos.productos.Producto;

@Entity
@PrimaryKeyJoinColumn(name = "modelo")
public class Sobremesa extends Producto {
	
	private String procesador;
	
	private String grafica;
	
	private String almacenamiento;
	
	private String ram;
	
	private String sistemaOperativo;
	
	

	public String getProcesador() {
		return procesador;
	}

	public void setProcesador(String procesador) {
		this.procesador = procesador;
	}

	public String getGrafica() {
		return grafica;
	}

	public void setGrafica(String grafica) {
		this.grafica = grafica;
	}

	public String getAlmacenamiento() {
		return almacenamiento;
	}

	public void setAlmacenamiento(String almacenamiento) {
		this.almacenamiento = almacenamiento;
	}

	public String getRam() {
		return ram;
	}

	public void setRam(String ram) {
		this.ram = ram;
	}

	public String getSistemaOperativo() {
		return sistemaOperativo;
	}

	public void setSistemaOperativo(String sistemaOperativo) {
		this.sistemaOperativo = sistemaOperativo;
	}
	
}

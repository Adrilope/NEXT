package com.adrian.proyecto.datos.productos.portatiles;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import com.adrian.proyecto.datos.productos.Producto;

@Entity
@PrimaryKeyJoinColumn(name = "modelo")
public class Portatil extends Producto {

	private String procesador;
	
	private String grafica;
	
	private String almacenamiento;
	
	private String ram;
	
	private String tipoPantalla;
	
	private String sizePantalla;

	
	

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

	public String getTipoPantalla() {
		return tipoPantalla;
	}

	public void setTipoPantalla(String tipoPantalla) {
		this.tipoPantalla = tipoPantalla;
	}

	public String getSizePantalla() {
		return sizePantalla;
	}

	public void setSizePantalla(String sizePantalla) {
		this.sizePantalla = sizePantalla;
	}

	
}

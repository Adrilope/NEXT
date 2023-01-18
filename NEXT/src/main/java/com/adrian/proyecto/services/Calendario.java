package com.adrian.proyecto.services;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Service;

@Service
public class Calendario {

	
	/**
	 * Metodo que devuelve el dia de hoy en formato europeo
	 * 
	 * @return dia de hoy
	 */
	public String getDiaActual() {
		Calendar fecha = Calendar.getInstance();
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		String hoy = formato.format(fecha.getTime());
				
		return hoy;
	}
}

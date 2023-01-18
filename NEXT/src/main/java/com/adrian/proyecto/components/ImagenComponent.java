package com.adrian.proyecto.components;

import org.springframework.stereotype.Component;

import com.adrian.proyecto.datos.productos.Producto;

@Component
public class ImagenComponent {

	private byte[] img;
	
	
	public void saveImagen(Producto producto) {
		this.img = producto.getImagen();
	}
	
	
	public byte[] getImagen() {
		return img;
	}
	
}

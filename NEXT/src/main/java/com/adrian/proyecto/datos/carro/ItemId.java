package com.adrian.proyecto.datos.carro;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ItemId implements Serializable {

	private String productoKey;
	
	private String usuarioKey;
	
	
	
	
	public ItemId() {
		
	}
	
	public ItemId(String modelo, String username) {
		this.productoKey = modelo;
		this.usuarioKey = username;
	}


	
	public String getProductoKey() {
		return productoKey;
	}


	public void setProductoKey(String productoKey) {
		this.productoKey = productoKey;
	}

	public String getUsuarioKey() {
		return usuarioKey;
	}

	public void setUsuarioKey(String usuarioKey) {
		this.usuarioKey = usuarioKey;
	}

	
	
	
	@Override
	public String toString() {
		return "ItemId [productoKey=" + productoKey + ", usuarioKey=" + usuarioKey + "]";
	}
	
}

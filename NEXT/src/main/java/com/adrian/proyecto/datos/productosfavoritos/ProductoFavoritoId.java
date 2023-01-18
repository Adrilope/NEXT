package com.adrian.proyecto.datos.productosfavoritos;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ProductoFavoritoId implements Serializable{

	private String productoKey;
	
	private Long listaFavoritosKey;
	
	
	
	

	public String getProductoKey() {
		return productoKey;
	}

	public void setProductoKey(String productoKey) {
		this.productoKey = productoKey;
	}

	public Long getListaFavoritosKey() {
		return listaFavoritosKey;
	}

	public void setListaFavoritosKey(Long listaFavoritosKey) {
		this.listaFavoritosKey = listaFavoritosKey;
	}
}

package com.adrian.proyecto.datos.productosfavoritos;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.adrian.proyecto.datos.favoritos.ListaFavoritos;
import com.adrian.proyecto.datos.productos.Producto;

@Entity
public class ProductoFavorito implements Serializable{
	
	@EmbeddedId
	private ProductoFavoritoId identificador;
	

	@MapsId("productoKey")
	@ManyToOne
	@JoinColumn(name="modelo")
	private Producto producto = new Producto();
	
	@MapsId("listaFavoritosKey")
	@ManyToOne
	@JoinColumn(name="id")
	private ListaFavoritos listaFavoritos = new ListaFavoritos();

	
	

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public ListaFavoritos getListaFavoritos() {
		return listaFavoritos;
	}

	public void setListaFavoritos(ListaFavoritos listaFavoritos) {
		this.listaFavoritos = listaFavoritos;
	}

	public ProductoFavoritoId getIdentificador() {
		return identificador;
	}

	public void setIdentificador(ProductoFavoritoId identificador) {
		this.identificador = identificador;
	}
	
	
	

	@Override
	public String toString() {
		return "ProductoFavorito [identificador=" + identificador + ", producto=" + producto + "]";
	}
}

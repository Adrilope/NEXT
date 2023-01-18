package com.adrian.proyecto.datos.productos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.adrian.proyecto.datos.carro.Item;
import com.adrian.proyecto.datos.opinion.Opinion;
import com.adrian.proyecto.datos.productosfavoritos.ProductoFavorito;

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
public class Producto {

	@Id
	private String modelo;
	
	@Column
	private Integer precio;
	
	@Column
	private String marca;
	
	@Lob
	private byte[] imagen;
	
	@Column
	private String tipo;
	
	@Column
	private String descripcion;
	
	@OneToMany(mappedBy = "productoOpinion", cascade = CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)	//para resolver el error failed to lazily initialize a collection of role
	private List<Opinion> opiniones = new ArrayList<Opinion>();
	
	@OneToMany(mappedBy = "producto", cascade = CascadeType.REMOVE)
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ProductoFavorito> listas_favoritos = new ArrayList<ProductoFavorito>();
	
	@OneToMany(mappedBy = "producto", cascade = CascadeType.REMOVE)
	private List<Item> items = new ArrayList<Item>();
	
	
	
	
	
	public void addOpinionProducto(Opinion opinion) {
		if(!opiniones.contains(opinion)) {
			opiniones.add(opinion);
			opinion.setProductoOpinion(this);
		}
	}



	

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
	
	public byte[] getImagen() {
		return imagen;
	}


	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	
	public List<Opinion> getOpiniones() {
		return opiniones;
	}


	public void setOpiniones(List<Opinion> opiniones) {
		this.opiniones = opiniones;
	}	


	public List<ProductoFavorito> getListas_favoritos() {
		return listas_favoritos;
	}


	public void setListas_favoritos(List<ProductoFavorito> listas_favoritos) {
		this.listas_favoritos = listas_favoritos;
	}





	@Override
	public String toString() {
		return "Producto [modelo=" + modelo + ", precio=" + precio + ", marca=" + marca + ", opiniones=" + opiniones + "]";
	}
}

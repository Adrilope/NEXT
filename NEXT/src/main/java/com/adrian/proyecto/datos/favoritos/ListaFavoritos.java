package com.adrian.proyecto.datos.favoritos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.adrian.proyecto.datos.opinion.Opinion;
import com.adrian.proyecto.datos.productos.Producto;
import com.adrian.proyecto.datos.productosfavoritos.ProductoFavorito;
import com.adrian.proyecto.datos.usuarios.Usuario;

@Entity(name="favoritos")
public class ListaFavoritos {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private String nombre;
	
	@ManyToOne
	private Usuario user = new Usuario();
	
	@OneToMany(mappedBy="listaFavoritos")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ProductoFavorito> productos = new ArrayList<ProductoFavorito>();
	
	
	
	public void addProductoFavorito(ProductoFavorito producto) {
		if(!productos.contains(producto)) {
			productos.add(producto);
			producto.setListaFavoritos(this);
		}
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}	

	public List<ProductoFavorito> getProductos() {
		return productos;
	}

	public void setProductos(List<ProductoFavorito> productos) {
		this.productos = productos;
	}
	
	
	

	@Override
	public String toString() {
		return "ListaFavoritos [id=" + id + ", nombre=" + nombre + ", user=" + user + ", productos=" + productos + "]";
	}
	
}

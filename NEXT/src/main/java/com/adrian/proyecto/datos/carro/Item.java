package com.adrian.proyecto.datos.carro;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import com.adrian.proyecto.datos.productos.Producto;
import com.adrian.proyecto.datos.usuarios.Usuario;

@Entity
public class Item implements Serializable {
	
	@EmbeddedId
    private ItemId id;
	
	@MapsId("productoKey")
	@ManyToOne
	@JoinColumn(name="modelo")
	private Producto producto = new Producto();
	
	@MapsId("usuarioKey")
	@ManyToOne
	@JoinColumn(name="username")
    private Usuario usuario = new Usuario();
	
	@Column
	private int cantidad;
	
	
	
	
	public Item(ItemId itemId, Producto producto, int cantidad, Usuario usuario) {
		this.id = itemId;
		this.producto = producto;
		this.cantidad = cantidad;
		this.usuario = usuario;
	}
	
	public Item(Producto producto, int cantidad) {
		this.producto = producto;
		this.cantidad = cantidad;
	}
	
	public Item() {
		
	}
	
	
	
	public ItemId getId() {
		return id;
	}

	public void setId(ItemId id) {
		this.id = id;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	

	@Override
	public String toString() {
		return "Item [id=" + id + ", producto=" + producto + ", cantidad=" + cantidad + "]";
	}

}

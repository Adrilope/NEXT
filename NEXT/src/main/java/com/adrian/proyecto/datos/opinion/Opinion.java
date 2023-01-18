package com.adrian.proyecto.datos.opinion;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.adrian.proyecto.datos.productos.Producto;
import com.adrian.proyecto.datos.usuarios.Usuario;

@Entity
public class Opinion {

	@Id
	@Column(name = "id_opinion")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idOpinion;
	
	@Column
	private String detalle;
	
	@Column
	private boolean recomendado = true;
	
	@Column
	private Integer puntuacion;
	
	@Column
	private String fechaOpinion;
	
	@ManyToOne
	private Usuario usuario = new Usuario();

	@ManyToOne
	private Producto productoOpinion = new Producto();
	
	
	
	
	public Long getIdOpinion() {
		return idOpinion;
	}

	public void setIdOpinion(Long idOpinion) {
		this.idOpinion = idOpinion;
	}

	public String getDetalle() {
		return detalle;
	}

	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

	public boolean isRecomendado() {
		return recomendado;
	}

	public void setRecomendado(boolean recomendado) {
		this.recomendado = recomendado;
	}

	public Integer getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Integer puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getFechaOpinion() {
		return fechaOpinion;
	}

	public void setFechaOpinion(String fechaOpinion) {
		this.fechaOpinion = fechaOpinion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Producto getProductoOpinion() {
		return productoOpinion;
	}

	public void setProductoOpinion(Producto productoOpinion) {
		this.productoOpinion = productoOpinion;
	}

	
	
	@Override
	public String toString() {
		return "Opinion [idOpinion=" + idOpinion + ", detalle=" + detalle + ", recomendado=" + recomendado
				+ ", puntuacion=" + puntuacion + ", fechaOpinion=" + fechaOpinion + "]";
	}

}

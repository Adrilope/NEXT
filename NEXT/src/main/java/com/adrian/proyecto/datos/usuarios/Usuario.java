package com.adrian.proyecto.datos.usuarios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.adrian.proyecto.datos.carro.Item;
import com.adrian.proyecto.datos.favoritos.ListaFavoritos;
import com.adrian.proyecto.datos.opinion.Opinion;
import com.adrian.proyecto.datos.roles.Rol;

@Entity
public class Usuario implements UserDetails{

	@Id
	private String username;
	
	@Column
	private String password;
	
	@Column
	private Integer edad;
	
	@Column
	private String correo;
	
	@Column
	private String ciudad;
	
	@Column
	private String direccion;
	
	@ManyToOne
	private Rol rol = new Rol();
	
	@OneToMany(mappedBy = "usuario")
	private List<Opinion> opiniones = new ArrayList<Opinion>();
	
	
	@OneToMany(mappedBy = "user")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<ListaFavoritos> listas_favoritos = new ArrayList<ListaFavoritos>();
	
	
	@OneToMany(mappedBy="usuario")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Item> items = new ArrayList<Item>();
	
	
	
	
	
	public void addItemsUsuario(Item item) {
		if(!items.contains(item)) {
			items.add(item);
			item.setUsuario(this);
		}
	}
	
	
	public void addListaFavoritosUsuario(ListaFavoritos lista) {
		if(!listas_favoritos.contains(lista)) {
			listas_favoritos.add(lista);
			lista.setUser(this);
		}
	}

	
	public void addOpinionUsuario(Opinion opinion) {
		if(!opiniones.contains(opinion)) {
			opiniones.add(opinion);
			opinion.setUsuario(this);
		}
	}
	
	


	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getEdad() {
		return edad;
	}

	public void setEdad(Integer edad) {
		this.edad = edad;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Opinion> getOpiniones() {
		return opiniones;
	}

	public void setOpiniones(List<Opinion> opiniones) {
		this.opiniones = opiniones;
	}


	public List<ListaFavoritos> getListas_favoritos() {
		return listas_favoritos;
	}


	public void setListas_favoritos(List<ListaFavoritos> listas_favoritos) {
		this.listas_favoritos = listas_favoritos;
	}
	
	public List<Item> getItems() {
		return items;
	}


	public void setItems(List<Item> items) {
		this.items = items;
	}

	
	

	@Override
	public String toString() {
		return "Usuario [username=" + username + ", password=" + password + ", edad=" + edad + ", correo=" + correo
				+ ", rol=" + rol + ", listas_favoritos=" + listas_favoritos + "]";
	}
	
	
	
	


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(rol.getNombre()));
		
		return grantedAuthorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}
	
}

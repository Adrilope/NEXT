package com.adrian.proyecto.services;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.adrian.proyecto.datos.carro.Item;
import com.adrian.proyecto.datos.carro.ItemDAO;
import com.adrian.proyecto.datos.usuarios.Usuario;

@Service
public class CarroService {
	
	@Autowired 
	ItemDAO itemDAO;
	

	
	/**
	 * 
	 * @param modelo
	 * @param cart
	 * @return null si un item no existe en el carro, o el item si existiera
	 */
	public Item existe(String modelo, List<Item> cart) {
		for(Item item : cart) {
			if(item.getProducto().getModelo().equals(modelo)) {
				return item;
			}
		}
		return null;
	}
	
	
	
	/**
	 * 
	 * @param auth
	 * @param sesion
	 * @return cantidad total de productos que tiene el carro
	 */
	public int getSizeCarro(Authentication auth, HttpSession sesion) {
		int size = 0;
		List<Item> cart = getCarro(auth, sesion);
		
		if(cart != null) {
			for(Item item : cart) {
				size += item.getCantidad();
			}
		}
		
		return size;
	}
	
	
	
	/**
	 * 
	 * @param auth
	 * @param sesion
	 * @return lista de items que dependiendo de si se esta logueado o no, se cogera de la sesion o de la BBDD
	 */
	public List<Item> getCarro(Authentication auth, HttpSession sesion) {
		List<Item> cart = null;
		
		if(auth == null) {
			if(sesion.getAttribute("cart") != null) {
				cart = (List<Item>) sesion.getAttribute("cart");
			}
		}
		else {
			Usuario user = (Usuario) auth.getPrincipal();
			List<Item> items = itemDAO.getUserItems(user.getUsername());
			if(items.size() != 0) {
				cart = items;
			}
		}
		
		return cart;
	}
	
	
	
	
	public int getCantidadTotal(Item itemGuardado, int cantidad) {
		int x = 5;
		
		if(itemGuardado.getCantidad() < 5 && (itemGuardado.getCantidad() + cantidad) < 5) {
			x = itemGuardado.getCantidad() + cantidad;
		}
		
		return x;
	}
}

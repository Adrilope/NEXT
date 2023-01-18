package com.adrian.proyecto.datos.carro;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.adrian.proyecto.datos.productos.ProductoDAO;
import com.adrian.proyecto.datos.usuarios.Usuario;
import com.adrian.proyecto.services.CarroService;

@Controller
public class CarroController {
	
	@Autowired
	ProductoDAO productoDAO;
	
	@Autowired
	CarroService carroService;
	
	@Autowired
	ItemDAO itemDAO;
	

	
	@GetMapping("/cart")
	public ModelAndView carro(Authentication auth, HttpSession session) {	
		ModelAndView model = new ModelAndView();
		model.setViewName("carro");

		model.addObject("items", carroService.getCarro(auth, session));
		model.addObject("cantidad", carroService.getSizeCarro(auth, session));
		
		return model;
	}
	
	
	
	
	@PostMapping("/cart/add")
	public ModelAndView añadirProducto(@RequestParam(value = "ruta") String ruta, @RequestParam(value = "modelo") String modelo, @RequestParam(value = "cantidad") Integer cantidad, @ModelAttribute("item") Item item, HttpSession sesion, Authentication auth) {	
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:" + ruta);
		
		if(auth != null) {
			Usuario usuario = (Usuario) auth.getPrincipal();
			Item i = carroService.existe(modelo, itemDAO.getUserItems(usuario.getUsername()));
			ItemId itemId = new ItemId(modelo, usuario.getUsername()); 
			
			if(i == null) {
				item.setProducto(productoDAO.findById(modelo).get());
				item.setUsuario(usuario);
				item.setId(itemId);
				item.setCantidad(cantidad);
				itemDAO.save(item);
			}
			else {
				i.setCantidad(carroService.getCantidadTotal(i, cantidad));
				i.setId(itemId);
				itemDAO.save(i);
			}
		} 
		else {
			if(sesion.getAttribute("cart") == null) {
				List<Item> cart = new ArrayList<Item>();
				cart.add(new Item(productoDAO.findById(modelo).get(), cantidad));
				sesion.setAttribute("cart", cart);
			}
			else {
				List<Item> cart = (List<Item>) sesion.getAttribute("cart");
				Item i = carroService.existe(modelo, cart);
				if(i == null) {
					cart.add(new Item(productoDAO.findById(modelo).get(), cantidad));
				}
				else {
					i.setCantidad(carroService.getCantidadTotal(i, cantidad));
				}
			}
		}
		
		return model;
	}
	
	
	
	
	@GetMapping("/cart/delete/{modelo}")
	public String deleteProducto(@PathVariable("modelo") String modelo, Authentication auth, HttpSession session) {	
		if(auth != null) {
			Usuario user = (Usuario) auth.getPrincipal();
			ItemId itemId = new ItemId(modelo, auth.getName());
			itemDAO.deleteById(itemId);
		}
		else {
			List<Item> cart = (List<Item>) session.getAttribute("cart");
			Item item = carroService.existe(modelo, cart);
			cart.remove(item);
			if(cart.size() == 0) {
				session.removeAttribute("cart");
			}
		}
		
		return "redirect:/cart";
	}
	
	
	@GetMapping("/cart/delete")
	public String deleteCarro(Authentication auth, HttpSession session) {	
		if(auth != null) {
			Usuario user = (Usuario) auth.getPrincipal();
			List<Item> items = itemDAO.getUserItems(user.getUsername());
			if(items != null) {
				for(Item item : items) {
					itemDAO.deleteById(item.getId());
				}
			}
		}
		else {
			session.removeAttribute("cart");
		}
		
		return "redirect:/cart";
	}
	
	
}

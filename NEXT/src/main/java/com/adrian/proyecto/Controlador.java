package com.adrian.proyecto;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.adrian.proyecto.datos.carro.Item;
import com.adrian.proyecto.datos.carro.ItemDAO;
import com.adrian.proyecto.datos.carro.ItemId;
import com.adrian.proyecto.datos.productos.Producto;
import com.adrian.proyecto.datos.productos.ProductoDAO;
import com.adrian.proyecto.datos.productosfavoritos.ProductoFavorito;
import com.adrian.proyecto.datos.usuarios.Usuario;
import com.adrian.proyecto.datos.usuarios.UsuarioDAO;
import com.adrian.proyecto.services.CarroService;


@Controller
public class Controlador {
	
	
	@Autowired 
	UsuarioDAO usuarioDAO;
	
	@Autowired
	ProductoDAO productoDAO;
	
	@Autowired
	CarroService carroService;
	
	@Autowired
	ItemDAO itemDAO;

	
	
	
	@GetMapping("/")
	public ModelAndView inicio(Authentication auth, HttpSession session) {  
		ModelAndView model = new ModelAndView();
		model.setViewName("index");
		model.addObject("productos", productoDAO.getSomeProductos(PageRequest.of(0,4)));	//obtenemos cuatro productos aleatorios
		model.addObject("item", new Item());
		
		
		//guardamos en base de datos los productos del carrito de la sesion (si habia)
		if(auth != null) {
			Usuario user = (Usuario) auth.getPrincipal();
			model.addObject("listasFavoritos", user.getListas_favoritos());
			model.addObject("productoFavorito", new ProductoFavorito());
			
			if(session.getAttribute("cart") != null) {
				List<Item> cart = (List<Item>) session.getAttribute("cart");
				List<Item> userItems = itemDAO.getUserItems(user.getUsername());
				
				for(Item item : cart) {
					if(productoDAO.existsById(item.getProducto().getModelo()) == true) { //por si se borro un producto y estaba añadido en el carrito de la sesion
						item.setUsuario(user);
						item.setId(new ItemId(item.getProducto().getModelo(), auth.getName()));
						if(carroService.existe(item.getProducto().getModelo(), userItems) == null) {
							itemDAO.save(item);
						}
						else {
							Item i = itemDAO.findById(item.getId()).get();
							item.setCantidad(carroService.getCantidadTotal(i, item.getCantidad()));
							itemDAO.save(item);
						}
					}
				}
				session.removeAttribute("cart");
			}
		}
		
		
		
		model.addObject("cantidad", carroService.getSizeCarro(auth, session));
		
		return model; 
	}
	
	
	
	@GetMapping("/login")
	public ModelAndView login(Authentication auth, HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("inicioSesion");
		model.addObject("cantidad", carroService.getSizeCarro(auth, session));
		
		return model;
	}
	
	
	@GetMapping("/logout")
	public String cerrarSesion() {
		return "ok";
	}
	
	
	
	
	
	
	/**
	 * Metodo encargado de mostrar la imagen correspondiente a un producto
	 * 
	 * @param modelo
	 * @param response
	 * @param request
	 * @throws ServletException
	 * @throws IOException
	 */
	@GetMapping("/mostrarImagen/{modelo}")
	public void mostrarImagen(@PathVariable String modelo, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException{

	    Producto producto = productoDAO.findById(modelo).get();        
	    response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
	    response.getOutputStream().write(producto.getImagen());
	    response.getOutputStream().close();
	}
	
}

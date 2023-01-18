package com.adrian.proyecto.datos.favoritos;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.adrian.proyecto.datos.carro.Item;
import com.adrian.proyecto.datos.productosfavoritos.ProductoFavorito;
import com.adrian.proyecto.datos.productosfavoritos.ProductoFavoritoDAO;
import com.adrian.proyecto.datos.usuarios.Usuario;
import com.adrian.proyecto.services.CarroService;


@Controller
public class RutasFavoritos {
	
	@Autowired
	ListaFavoritosDAO listaFavoritosDAO;

	@Autowired 
	ProductoFavoritoDAO pfDAO;
	
	
	@Autowired
	CarroService carroService;
	
	
	/**
	 * ruta que nos devuelve las listas de favoritos
	 * 
	 * @param autenticacion
	 * @param session
	 * @return
	 */
	@GetMapping("/favoritos/ver")
	public ModelAndView favoritos(Authentication autenticacion, HttpSession session) {
		Usuario usuario = (Usuario) autenticacion.getPrincipal();
				
		ModelAndView model = new ModelAndView();
		model.setViewName("favoritos");
		model.addObject("usuario", usuario);
		model.addObject("listasFavoritos", listaFavoritosDAO.getListasFavoritos(usuario.getUsername()));
		model.addObject("lista", new ListaFavoritos());
		
		model.addObject("cantidad", carroService.getSizeCarro(autenticacion, session));
		
		return model;
	}
	
	
	
	/**
	 * Añade una nueva lista
	 * 
	 * @param lista
	 * @param autenticacion
	 * @return
	 */
	@PostMapping("/favoritos/add")
	public String nuevaListaFavoritos(@ModelAttribute("lista") ListaFavoritos lista, Authentication autenticacion) {
		Usuario usuario =  (Usuario) autenticacion.getPrincipal();
		usuario.addListaFavoritosUsuario(lista);	//lo añadimos manualmente para sincronizarlo, porque al utilizar el authentication si no solo se añadiria la lista al cambiar la sesion
		
		listaFavoritosDAO.save(lista);
		
		return "redirect:/favoritos/ver";
	}
	
	
	
	
	/**
	 * ruta que nos devuelve el contenido de una lista de favoritos 
	 * @param autenticacion
	 * @param session
	 * @param id
	 * @return
	 */
	@GetMapping("/favoritos/{idLista}")
	public ModelAndView miLista(Authentication autenticacion, HttpSession session, @PathVariable("idLista") Long id) {
		Usuario usuario = (Usuario) autenticacion.getPrincipal();
		ListaFavoritos listaFavoritos = listaFavoritosDAO.findById(id).get();
				
		ModelAndView model = new ModelAndView();
		if (listaFavoritos.getUser().getUsername().equals(usuario.getUsername())) {	
			List<ProductoFavorito> lista = pfDAO.getFavoritos(id);
			
			model.setViewName("lista");
			model.addObject("lista", lista);
			model.addObject("item", new Item());
			model.addObject("nombreLista", listaFavoritos.getNombre());

			model.addObject("cantidad", carroService.getSizeCarro(autenticacion, session));
		}
		else {
			model.setViewName("redirect:/favoritos/ver");
		}
		
		return model;
	}
}

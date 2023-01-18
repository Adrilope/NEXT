package com.adrian.proyecto.datos.productos;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.adrian.proyecto.datos.carro.Item;
import com.adrian.proyecto.datos.opinion.Opinion;
import com.adrian.proyecto.datos.opinion.OpinionDAO;
import com.adrian.proyecto.datos.productosfavoritos.ProductoFavorito;
import com.adrian.proyecto.datos.usuarios.Usuario;
import com.adrian.proyecto.services.CarroService;
import com.adrian.proyecto.services.Paginacion;

@Controller
public class ProductosRutas {

	@Autowired
	Paginacion paginacion;
	
	@Autowired
	ProductoDAO productoDAO;
	
	@Autowired
	OpinionDAO opinionDAO;

	
	@Autowired
	CarroService carroService;
	
	
	
	/**
	 * Ruta que mostrará los productos por páginas
	 * 
	 * @param pag
	 * @return
	 */
	@GetMapping("/productos/{tipo}/{pag}")
	public ModelAndView productosPag(@PathVariable String tipo, @PathVariable Integer pag, Authentication autenticacion, HttpSession session) {  
		PageRequest pagina = paginacion.productosPorPagina(pag);
		Page<Producto> paginaProductos = paginacion.getProductos(pagina, tipo);
		
		
		ModelAndView model = new ModelAndView();
		if(tipo.equalsIgnoreCase("portatil") || tipo.equalsIgnoreCase("sobremesa") || tipo.equalsIgnoreCase("componente")) {
			model.setViewName("productos");
			model.addObject("productos", paginaProductos.getContent());
			
			List<Integer> paginas = paginacion.paginasTotales(paginaProductos.getTotalPages());
			model.addObject("paginas", paginas);
			model.addObject("actual", pag);
			model.addObject("siguiente", pag + 1);
			model.addObject("anterior", pag - 1);
	
			
			model.addObject("item", new Item());
			model.addObject("cantidad", carroService.getSizeCarro(autenticacion, session));
			
			if(autenticacion != null) {
				Usuario usuario =  (Usuario) autenticacion.getPrincipal();
				model.addObject("listasFavoritos", usuario.getListas_favoritos());
				model.addObject("productoFavorito", new ProductoFavorito());
			}
		}else {
			model.setViewName("redirect:/");
		}
		
		return model; 
	}
	
	
	@GetMapping("/productos/busqueda/{busqueda}")
	public ModelAndView buscarProductos(@PathVariable("busqueda") String busqueda, Authentication auth, HttpSession session) {  
		List<Producto> productos = productoDAO.getProductosBusqueda(busqueda);
		
		ModelAndView model = new ModelAndView();
		model.setViewName("productos");
		model.addObject("productosBusqueda", productos);
		model.addObject("item", new Item());
		model.addObject("cantidad", carroService.getSizeCarro(auth, session));
		
		if(auth != null) {
			Usuario usuario =  (Usuario) auth.getPrincipal();
			model.addObject("listasFavoritos", usuario.getListas_favoritos());
			model.addObject("productoFavorito", new ProductoFavorito());
		}
		
		return model; 
	}
	
	
	
	
	@GetMapping("/producto/{modelo}")
	public ModelAndView producto(@PathVariable String modelo, Authentication auth, HttpSession session) {  
		Producto producto = productoDAO.findById(modelo).get();
		
		ModelAndView model = new ModelAndView();
		model.setViewName("producto");
		model.addObject("producto", producto);
		model.addObject("opinion", new Opinion());
		model.addObject("opiniones", producto.getOpiniones());
		model.addObject("item", new Item());
		model.addObject("cantidad", carroService.getSizeCarro(auth, session));
		
		if(auth != null) {
			Usuario usuario =  (Usuario) auth.getPrincipal();
			model.addObject("listasFavoritos", usuario.getListas_favoritos());
			model.addObject("productoFavorito", new ProductoFavorito());
		}
		
		return model; 
	}
	
	
}

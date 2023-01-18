package com.adrian.proyecto;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;

import com.adrian.proyecto.components.ImagenComponent;
import com.adrian.proyecto.datos.productos.FullProducto;
import com.adrian.proyecto.datos.productos.Producto;
import com.adrian.proyecto.datos.productos.ProductoDAO;
import com.adrian.proyecto.datos.usuarios.UsuarioDAO;
import com.adrian.proyecto.services.CarroService;
import com.adrian.proyecto.services.SaveTypeProducto;

@Controller
public class RutasAdmin {
	
	@Autowired 
	UsuarioDAO usuarioDAO;
	
	@Autowired
	ProductoDAO productoDAO;

	@Autowired 
	ImagenComponent imgComponent;
	
	@Autowired
	SaveTypeProducto saveTypeProducto;

	@Autowired
	CarroService carroService;
	
	
	
	
	@GetMapping("/admin/usuarios")
	public ModelAndView usuarios(Authentication auth, HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("usuarios");
		model.addObject("usuarios", usuarioDAO.findAll());
		
		model.addObject("cantidad", carroService.getSizeCarro(auth, session));
		
		return model; 
	}
	
	
	
	@GetMapping("/admin/productos")
	public ModelAndView productos(Authentication auth, HttpSession session) {
		ModelAndView model = new ModelAndView();
		model.setViewName("productosDT");
		model.addObject("productos", productoDAO.findAll());
		model.addObject("producto", new FullProducto());
		model.addObject("cantidad", carroService.getSizeCarro(auth, session));
		
		return model; 
	}
	
	
	
	@PostMapping("/admin/addProducto")
	public ModelAndView addProducto(@ModelAttribute("producto") FullProducto producto, @RequestParam("tipo") String tipo) throws IOException { 
		ModelAndView model = new ModelAndView();
		model.addObject("productos", productoDAO.findAll());
		model.setViewName("redirect:/admin/productos");
		
		saveTypeProducto.saveProducto(tipo, producto);
		
		return model; 
	}
	
	
	
	@GetMapping("/admin/editarProducto/{modelo}")
	public ModelAndView editarProducto(@PathVariable("modelo") String modelo, Authentication auth, HttpSession session) {
		ModelAndView model = new ModelAndView();
		
		Optional<Producto> optional = productoDAO.findById(modelo);
		if(productoDAO.findById(modelo) != null) {
			model.setViewName("editarProducto");
			Producto producto = optional.get();
			FullProducto productoFull = saveTypeProducto.toFullProducto(producto);
			model.addObject("producto", productoFull);
			imgComponent.saveImagen(producto);
			model.addObject("cantidad", carroService.getSizeCarro(auth, session));
		}
		else {
			model.setStatus(HttpStatus.NOT_FOUND);
		}
		
		return model; 
	}
	
	
	
	@PostMapping("/admin/updateProducto")
	public String updateProducto(@ModelAttribute("producto") FullProducto producto,  @RequestParam("imagen") MultipartFile imagen) throws IOException {
		if (imagen.isEmpty()) {			//solucion al problema de que si no se modificaba el campo de la imagen viaja vacio
			producto.setImagen(imgComponent.getImagen());
		}
		
		saveTypeProducto.saveProducto(producto.getTipo(), producto);
		
		return "redirect:/admin/productos"; 
	}
	
	
	
	
	@GetMapping("/admin/deleteProducto/{producto}")
	public String deleteProducto(@PathVariable("producto") String producto) {
		productoDAO.deleteById(producto);
		
		return "redirect:/admin/productos"; 
	}
	
	
	
	
	
	/**
	 * Metodo para convertir la clase CommonsMultipartFile de la imagen del form a byte[] 
	 * 
	 * @param request
	 * @param binder
	 * @throws ServletException
	 */
	@InitBinder
	protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws ServletException {
			binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
}

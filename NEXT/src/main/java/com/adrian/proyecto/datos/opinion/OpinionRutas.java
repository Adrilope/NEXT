package com.adrian.proyecto.datos.opinion;

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
import com.adrian.proyecto.services.Calendario;


@Controller
public class OpinionRutas {
	
	@Autowired 
	OpinionDAO opinionDAO;
	
	@Autowired 
	ProductoDAO productoDAO;
	
	@Autowired
	Calendario calendario;
	
	
	@PostMapping("/opinion/add")
	public ModelAndView addOpinion(@ModelAttribute Opinion opinion, @RequestParam("productoOpinion") String modelo, Authentication autenticacion) { 
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/producto/" + modelo);
		
		
		opinion.setFechaOpinion(calendario.getDiaActual());
		opinion.setUsuario((Usuario) autenticacion.getPrincipal());
		opinionDAO.save(opinion);
		
		return model; 
	}
	
	
	
	@GetMapping("/opinion/delete/{id}")
	public String deleteOpinion(@PathVariable("id") Long id, Authentication authentication) {
		Opinion opinion = opinionDAO.findById(id).get();
		String modelo = opinion.getProductoOpinion().getModelo();
		if (opinion.getUsuario().getUsername().equals(authentication.getName())) {
			opinionDAO.deleteById(id);
		}
		
		return "redirect:/producto/"+ modelo; 
	}
}

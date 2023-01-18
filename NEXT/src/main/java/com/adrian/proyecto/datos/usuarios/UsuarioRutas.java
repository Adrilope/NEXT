package com.adrian.proyecto.datos.usuarios;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.adrian.proyecto.beans.PasswordEncoderBuilder;
import com.adrian.proyecto.services.CarroService;


@Controller
public class UsuarioRutas {
	
	@Autowired
	UsuarioDAO usuarioDAO;
	
	@Autowired 
	PasswordEncoderBuilder passEncoder;
	
	@Autowired
	CarroService carroService;
	

	
	@GetMapping("/nuevoUser")
	public ModelAndView nuevoUser(Authentication auth, HttpSession session) {  
		ModelAndView model = new ModelAndView();
		model.addObject("usuario", new Usuario());
		model.setViewName("nuevoUsuario");
		model.addObject("cantidad", carroService.getSizeCarro(auth, session));
		
		return model; 
	}
	
	
	@PostMapping("/addUsuario")
	public ModelAndView addUser(@ModelAttribute Usuario usuario) {  
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/");
		
		if(!usuarioDAO.existsById(usuario.getUsername())) {
			usuario.setPassword(passEncoder.passwordEncoder().encode(usuario.getPassword()));
			usuarioDAO.save(usuario);
		}
		
		return model; 
	}
	
	
	
	@GetMapping("/usuario/perfil")
	public ModelAndView perfil(Authentication autenticacion, HttpSession session) {
		Usuario usuario =  (Usuario) autenticacion.getPrincipal();
		
		ModelAndView model = new ModelAndView();
		model.setViewName("perfil");
		model.addObject("usuario", usuarioDAO.findById(usuario.getUsername()).get());
		model.addObject("cantidad", carroService.getSizeCarro(autenticacion, session));
		
		return model;
	}
	
	
	
	
	@PostMapping("/usuario/editar")
	public String updateUser(@ModelAttribute("usuario") Usuario usuario) {
		
		Usuario user = usuarioDAO.findById(usuario.getUsername()).get();
		usuario.setPassword(user.getPassword());
		usuario.setRol(user.getRol());
		usuarioDAO.save(usuario);
		
		return "redirect:/";
	}
}

package it.siw.catering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import it.siw.catering.model.Buffet;
import it.siw.catering.model.Ingrediente;
import it.siw.catering.model.Piatto;
import it.siw.catering.repository.BuffetRepository;
import it.siw.catering.repository.IngredienteRepository;
import it.siw.catering.repository.PiattoRepository;

@Controller
public class HomeController {


	
	@RequestMapping("/")
	public String home () {
		return "home.html";	
	}
	
	

}

package it.siw.catering.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import it.siw.catering.model.Buffet;
import it.siw.catering.model.Chef;
import it.siw.catering.model.Piatto;
import it.siw.catering.repository.BuffetRepository;
import it.siw.catering.repository.ChefRepository;
import it.siw.catering.repository.PiattoRepository;
import it.siw.catering.service.BuffetService;

@Controller
@RequestMapping("/buffet")
public class BuffetController {

	@Autowired
	private BuffetService buffetService;

	@RequestMapping("")
	public String formContattaci(Model model) {

		List<Buffet> buffets = this.buffetService.getBuffets();

		if (buffets != null) {

			model.addAttribute("buffets", buffets);

		}

		return "buffet";
	}
	
	
	
}

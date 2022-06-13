package it.siw.catering.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import it.siw.catering.service.ChefService;

@Controller
public class ChefController {

	@Autowired
	private ChefService chefService;
	
	@GetMapping("/chef")
	public String getChefs (Model model) {
		
		model.addAttribute("chefs", this.chefService.getChefs());
		
		return "chef";
	}
	
}

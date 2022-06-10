package it.siw.catering.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.siw.catering.model.Ingrediente;
import it.siw.catering.model.Piatto;
import it.siw.catering.service.IngredientiService;

@Controller
@RequestMapping("/ingredienti")
public class IngredientiController {

	@Autowired
	private IngredientiService ingredientiService;

	@GetMapping("")
	public String getIngredienti(Model model) {

		List<Ingrediente> ingredienti = this.ingredientiService.getIngredienti();
		
		if (ingredienti != null ) {
			model.addAttribute("ingredienti", ingredienti);
		}

		return "ingredienti";
	}

	
	@GetMapping("/addIngrediente")
	public String addIngrediente(Model model) {
		
		model.addAttribute("ingrediente", new Ingrediente());
		
		return "addIngredienteForm";
	}
	
	@PostMapping("/addIngrediente")
	public String addIngrediente (@ModelAttribute("ingrediente") Ingrediente ingrediente, Model model, BindingResult bindingResult) {
		
		if (!bindingResult.hasErrors()) {
			this.ingredientiService.addIngrediente(ingrediente);
			return "redirect:/ingredienti";
		}
		return "addIngredienteForm";
	}
}

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

import it.siw.catering.model.Buffet;
import it.siw.catering.model.Ingrediente;
import it.siw.catering.model.Piatto;
import it.siw.catering.repository.IngredienteRepository;
import it.siw.catering.service.PiattoService;
import it.siw.catering.validator.PiattoController;

@Controller
@RequestMapping("/piatti")
public class PIattiController {

	@Autowired
	private PiattoService piattoService;
	
	@Autowired
	private PiattoController piattoController;

	@GetMapping("")
	public String mostraPiatti(Model model) {

		List<Piatto> piatti = this.piattoService.getPiatti();

		if (piatti != null) {
			model.addAttribute("piatti", piatti);
		}
		
		return "piatti";
	}

	@GetMapping("/addPiatto")
	public String addPiatto(Model model) {

		model.addAttribute("piatto", new Piatto());
		
		return "addPiattoForm";
	}

	@PostMapping("/addPiatto")
	public String addPiatto(@ModelAttribute("piatto") Piatto piatto, Model model, BindingResult bindingResult) {
		
		this.piattoController.validate(piatto, bindingResult);
		
		if (bindingResult.hasErrors()) {
			System.out.println();
			model.addAttribute("piatto", piatto);
			return "addPiattoForm";
		}

		String urlImmagine = "background-image: url(" + piatto.getUrlImg() + ");";
		piatto.setUrlImg(urlImmagine);
		
		
		if (!bindingResult.hasErrors()) {
			this.piattoService.addPiatto(piatto);
			return "redirect:../admin/piatti";
		}
		return "addPiattoForm";
	}
	
}

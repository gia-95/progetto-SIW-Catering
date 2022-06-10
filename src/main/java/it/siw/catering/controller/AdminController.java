package it.siw.catering.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import it.siw.catering.model.Buffet;
import it.siw.catering.model.Piatto;
import it.siw.catering.service.BuffetService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private BuffetService buffetService;

	@GetMapping(value = "/buffet")
	public String getBuffet(Model model) {

		model.addAttribute("buffets", this.buffetService.getBuffets());

		return "admin/buffetAdmin";
	}

	@GetMapping(value = "/aggiungiBuffet")
	public String aggiungiBuffet() {
		return "aggiungiBuffetForm";
	}

	@GetMapping(value = "buffet/modificaBuffet/{idBuffet}")
	public String modificaBuffet(@PathVariable Long idBuffet, Model model) {

		Buffet buffet = this.buffetService.getBuffetById(idBuffet);

		List<Piatto> primiPiatti = buffet.getPrimiPiatti();

		model.addAttribute("buffet", buffet);
		model.addAttribute("primipiatti", primiPiatti);

		return "admin/modificaBuffetAdmin";
	}

}

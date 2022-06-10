package it.siw.catering.controller;

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

	@GetMapping("/addBuffet")
	public String addBuffet(Model model) {

		model.addAttribute("buffet", new Buffet());
		
		return "addBuffetForm";
	}

	@PostMapping("/addBuffet")
	public String addBuffet(@ModelAttribute("buffet") Buffet buffet, Model model, BindingResult bindingResult) {

		if (!bindingResult.hasErrors()) {
			this.buffetService.addBuffet(buffet);
			model.addAttribute("buffets", this.buffetService.getBuffets());
			return "redirect:/buffet";
		}
		return "addBuffetForm";
	}

}

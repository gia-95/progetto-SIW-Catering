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
import it.siw.catering.model.Chef;
import it.siw.catering.service.BuffetService;
import it.siw.catering.service.ChefService;
import it.siw.catering.validator.BuffetValidator;
import it.siw.catering.validator.ChefValidator;

@Controller
@RequestMapping("/buffet")
public class BuffetController {

	@Autowired
	private BuffetService buffetService;

	@Autowired
	private ChefService chefService;
	
	@Autowired 
	BuffetValidator buffetValidator;
	
	@Autowired 
	ChefValidator chefValidator;

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
		
		buffetValidator.validate(buffet, bindingResult);
		chefValidator.validate(buffet.getChef(), bindingResult);
		
		if (bindingResult.hasErrors()) {
			System.out.println();
			model.addAttribute("buffet", buffet);
			return "addBuffetForm";
		}

		//		Controllo prima se lo chef già esiste, in caso lo setto sul buffet (lo chef che ha già un id), così spring lo riconosce
		Chef chef = this.chefService.getChefByNomeAndCognome(buffet.getChef().getNome(), buffet.getChef().getCognome());


		if (chef != null) {
			buffet.setChef(chef);
		} else {

			String urlImmagineChef = "background-image: url(" + buffet.getChef().getUrlImg() + ");";
			buffet.getChef().setUrlImg(urlImmagineChef);
		}

		//		In caso non esistesse già, spring aggiunge il nuovo chef al db al db

		String urlImmagine = "background-image: url(" + buffet.getUrlImg() + ");";
		buffet.setUrlImg(urlImmagine);


		if (!bindingResult.hasErrors()) {
			this.buffetService.addBuffet(buffet);
			model.addAttribute("buffets", this.buffetService.getBuffets());
			return "redirect:../admin/buffet";
		}
		return "addBuffetForm";
	}

}

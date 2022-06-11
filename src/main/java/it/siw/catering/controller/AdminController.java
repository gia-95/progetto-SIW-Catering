package it.siw.catering.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.siw.catering.model.Buffet;
import it.siw.catering.model.Piatto;
import it.siw.catering.service.BuffetService;
import it.siw.catering.service.PiattoService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private BuffetService buffetService;

	@Autowired
	private PiattoService piattoService;

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
		List<Piatto> secondiPiatti = buffet.getSecondiPiatti();
		List<Piatto> dolci = buffet.getDolci();

		model.addAttribute("buffet", buffet);
		model.addAttribute("primiPiatti", primiPiatti);
		model.addAttribute("secondiPiatti", secondiPiatti);
		model.addAttribute("dolci", dolci);

		model.addAttribute("listaPiatti", this.piattoService.getPiatti());
		model.addAttribute("piatto", new Piatto());

		return "admin/modificaBuffetAdmin";
	}

	@PostMapping(value = "buffet/aggiungiPrimoABuffet/{idBuffet}")
	public String addPrimoABuffet(@PathVariable Long idBuffet, @ModelAttribute("piatto") Piatto piatto, Model model) {

		Buffet buffet = this.buffetService.getBuffetById(idBuffet);

		Piatto piattoDaAggiungere = this.piattoService.getPiattoByNome(piatto.getNome());

		buffet.getPrimiPiatti().add(piattoDaAggiungere);

//		Ho aggiornato il vecchio buffet con il "nuovo" che ha il piatto in più
		buffetService.addBuffet(buffet);

		return this.modificaBuffet(idBuffet, model);
	}

	@PostMapping(value = "buffet/aggiungiSecondoABuffet/{idBuffet}")
	public String addSecondoABuffet(@PathVariable Long idBuffet, @ModelAttribute("piatto") Piatto piatto, Model model) {

		Buffet buffet = this.buffetService.getBuffetById(idBuffet);

		Piatto piattoDaAggiungere = this.piattoService.getPiattoByNome(piatto.getNome());

		buffet.getSecondiPiatti().add(piattoDaAggiungere);

//		Ho aggiornato il vecchio buffet con il "nuovo" che ha il piatto in più
		buffetService.addBuffet(buffet);

		return this.modificaBuffet(idBuffet, model);
	}

	@PostMapping(value = "buffet/aggiungiDolceABuffet/{idBuffet}")
	public String addDolceABuffet(@PathVariable Long idBuffet, @ModelAttribute("piatto") Piatto piatto, Model model) {

		Buffet buffet = this.buffetService.getBuffetById(idBuffet);

		Piatto piattoDaAggiungere = this.piattoService.getPiattoByNome(piatto.getNome());

		buffet.getDolci().add(piattoDaAggiungere);

//		Ho aggiornato il vecchio buffet con il "nuovo" che ha il piatto in più
		buffetService.addBuffet(buffet);

		return this.modificaBuffet(idBuffet, model);
	}

	@GetMapping(value = "piatto/eliminaSecondoPiatto/{idBuffet}/{idPiatto}")
	public String eliminaSecondoPiatto(@PathVariable Long idBuffet, @PathVariable Long idPiatto, Model model) {
		
		
		System.out.println("ciao");
		
		return null;
		
//		Buffet buffet = this.buffetService.getBuffetById(idBuffet);
//
//		Piatto piattoDaRimuovere = this.piattoService.getPiattoById(idPiatto);
//		
//		for (Piatto secPiatto : buffet.getSecondiPiatti()) {
//			if (secPiatto.getNome().equals(piattoDaRimuovere.getNome()))
//				buffet.getSecondiPiatti().remove(secPiatto);
//		}
//		
//		buffetService.addBuffet(buffet);
//
//		return this.modificaBuffet(idBuffet, model);

	}

}

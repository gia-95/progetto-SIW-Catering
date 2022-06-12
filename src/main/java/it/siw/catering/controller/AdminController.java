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
import it.siw.catering.model.Ingrediente;
import it.siw.catering.model.Piatto;
import it.siw.catering.service.BuffetService;
import it.siw.catering.service.IngredientiService;
import it.siw.catering.service.PiattoService;
import net.bytebuddy.asm.Advice.This;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private BuffetService buffetService;

	@Autowired
	private PiattoService piattoService;

	@Autowired
	private IngredientiService ingredientiService;

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

	/**
	 * DA SISTEMARE!!!!!!!!! ( Rimuovi piatto da buffet ( errore nel passaggio
	 * parametri path )
	 * 
	 * @param idBuffet
	 * @param idPiatto
	 * @param model
	 * @return
	 */
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

	@GetMapping(value = "buffet/eliminaBuffet/{idBuffet}")
	public String eliminaBuffet(@PathVariable Long idBuffet) {

		this.buffetService.eliminaBuffetById(idBuffet);

		return "redirect:/admin/buffet";
	}

	@GetMapping(value = "/piatti")
	public String getPiattiAdmin(Model model) {

		model.addAttribute("piatti", this.piattoService.getPiatti());

		return "admin/piattiAdmin";
	}

	@GetMapping(value = "piatti/modificaPiatto/{idPiatto}")
	public String modificaPiatto(@PathVariable Long idPiatto, Model model) {

		Piatto piattoDaModificare = this.piattoService.getPiattoById(idPiatto);

		model.addAttribute("piatto", piattoDaModificare);
		model.addAttribute("ingredienti", piattoDaModificare.getIngredienti());
		model.addAttribute("ingrediente", new Ingrediente());
		model.addAttribute("listaIngredienti", this.ingredientiService.getIngredienti());

		return "admin/modificaPiattoAdmin";
	}

	@PostMapping(value = "piatti/aggiungiIngredienteAPiatto/{idPiatto}")
	public String aggiungiIngredienteAPiatto(@PathVariable Long idPiatto,
			@ModelAttribute("ingrediente") Ingrediente ingrediente, Model model) {

		Piatto piatto = this.piattoService.getPiattoById(idPiatto);

		Ingrediente ingredienteDaAggiungere = this.ingredientiService.getIngredienteByNome(ingrediente.getNome());

		piatto.getIngredienti().add(ingredienteDaAggiungere);

		this.piattoService.addPiatto(piatto);

		return this.modificaPiatto(idPiatto, model);
	}

	@GetMapping(value = "piatti/eliminaPiatto/{idPiatto}")
	public String eliminaPiatto(@PathVariable Long idPiatto, Model model) {

//		Forse andrebbe fatto nel Service
		for (Buffet buffet : this.buffetService.getBuffets()) {
			for (Piatto piatto : buffet.getPrimiPiatti()) {
				if (piatto.getId() == idPiatto) {
					buffet.getPrimiPiatti().remove(piatto);
					this.buffetService.addBuffet(buffet);
					break;
				}
			}
			for (Piatto piatto : buffet.getSecondiPiatti()) {
				if (piatto.getId() == idPiatto) {
					buffet.getSecondiPiatti().remove(piatto);
					this.buffetService.addBuffet(buffet);
					break;
				}
			}
			for (Piatto piatto : buffet.getDolci()) {
				if (piatto.getId() == idPiatto) {
					buffet.getDolci().remove(piatto);
					this.buffetService.addBuffet(buffet);
					break;
				}
			}
		}

		this.piattoService.deletePiattoById(idPiatto);

		return "redirect:/admin/piatti";
	}
	
	
	@GetMapping (value = "/ingredienti")
	public String getIngredienti (Model model) {
		
		model.addAttribute("ingredienti", this.ingredientiService.getIngredienti());
		
		return "admin/ingredientiAdmin";
	}

	@GetMapping(value = "ingredienti/eliminaIngrediente/{idIngrediente}")
	public String eliminaIngrediente(@PathVariable Long idIngrediente) {

//		Elimino l'ingrediente dalla lista degli ingredienti di tutti i piatti
		for (Piatto piatto : this.piattoService.getPiatti()) {
			for (Ingrediente ingrediente : piatto.getIngredienti()) {
				if (ingrediente.getId() == idIngrediente) {
					piatto.getIngredienti().remove(ingrediente);
					this.piattoService.addPiatto(piatto);
					break;
				}
			}
		}
		
		this.ingredientiService.eliminaIngredienteById(idIngrediente);
		
		return "redirect:/admin/ingredienti";
	}

}

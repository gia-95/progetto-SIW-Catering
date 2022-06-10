package it.siw.catering.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.siw.catering.model.Ingrediente;
import it.siw.catering.model.Piatto;
import it.siw.catering.repository.IngredienteRepository;
import it.siw.catering.service.PiattoService;

@Controller
@RequestMapping("/piatti")
public class PIattiController {

	@Autowired
	private PiattoService piattoService;

	@GetMapping("")
	public String mostraPiatti(Model model) {

		List<Piatto> piatti = this.piattoService.getPiatti();

		if (piatti != null) {
			model.addAttribute("piatti", piatti);
		}
		
		return "piatti";
	}

	private void aggiungiIngrediente() {
		Ingrediente uova = new Ingrediente();
		uova.setNome("Uova");
	
		Ingrediente p = new Ingrediente();
		p.setNome("Pancetta");
		
		Ingrediente pec = new Ingrediente();
		pec.setNome("Pecorino");
	
		Ingrediente carneManzo = new Ingrediente("Carne di manzo");
		Ingrediente carneVitello = new Ingrediente("Carne di vitello");
		Ingrediente limone = new Ingrediente("Limone");
		Ingrediente pomodoro = new Ingrediente("Pomodoro");
		

		Piatto matriana = this.piattoService.getPiatto("Carbonara");
		
//		ingredienteRepository.save(carneManzo);
//		ingredienteRepository.save(carneVitello);
//		ingredienteRepository.save(limone);
//		ingredienteRepository.save(pomodoro);
//
//		Piatto matriana = this.piattoService.getPiatto("Matriciana");
//		matriana.getIngredienti().add(pomodoro);
//		matriana.getIngredienti().add(uo);
//
//		
		
		
		
		
		piattoService.addPiatto(matriana);

	}

}

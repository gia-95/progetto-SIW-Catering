package it.siw.catering.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contatti")
public class ContattiController {

	
	@RequestMapping("/richiestaCatering")
	public String formContattaci () {
		return "contattaciForm";
	}
}

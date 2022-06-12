package it.siw.catering.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.siw.catering.model.Chef;
import it.siw.catering.repository.ChefRepository;

@Service
public class ChefService {

	@Autowired
	private ChefRepository chefRepository;
	
	
	public Chef getChefByNomeAndCognome (String nome, String cognome) {
		
		if (this.chefRepository.findByNomeAndCognome(nome, cognome).isPresent()) {
			return this.chefRepository.findByNomeAndCognome(nome, cognome).get();
		}
		return null;
	}
	
}

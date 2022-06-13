package it.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.siw.catering.model.Chef;
import it.siw.catering.model.Piatto;
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
	
	
	public List<Chef> getChefs () {
		
		List<Chef> chefs = new ArrayList<>();

		for (Chef chef : this.chefRepository.findAll()) {
			chefs.add(chef);
		}

		return chefs;
	}
}

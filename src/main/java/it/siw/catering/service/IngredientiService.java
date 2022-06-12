package it.siw.catering.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.siw.catering.model.Ingrediente;
import it.siw.catering.repository.IngredienteRepository;

@Service
public class IngredientiService {
	
	@Autowired
	private IngredienteRepository ingredienteRepository;

	public List<Ingrediente> getIngredienti() {

		List<Ingrediente> ingredienti = new ArrayList<>();

		for (Ingrediente ingrediente : this.ingredienteRepository.findAll()) {
			ingredienti.add(ingrediente);
		}

		return ingredienti;
	}

	public boolean addIngrediente(Ingrediente ingrediente) {

		Ingrediente ingredienteSaved = this.ingredienteRepository.save(ingrediente);
		return ingredienteSaved != null;
	}
	
	public Ingrediente getIngredienteByNome (String nomeIngrediente) {
		
		return this.ingredienteRepository.findByNome(nomeIngrediente).get();
	}
	
	public void eliminaIngredienteById (Long idIngrediente) {
		
		this.ingredienteRepository.deleteById(idIngrediente);
	}
	

}

package it.siw.catering.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.siw.catering.model.Ingrediente;
import it.siw.catering.model.Piatto;

public interface IngredienteRepository extends CrudRepository<Ingrediente, Long>{
	
	public Optional<Ingrediente> findByNome(String nome);

}

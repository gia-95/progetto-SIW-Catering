package it.siw.catering.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.siw.catering.model.Chef;
import it.siw.catering.model.Credentials;

public interface ChefRepository extends CrudRepository<Chef, Long> {

	
	public Optional<Chef> findByNomeAndCognome(String nome, String cognome);

	
}

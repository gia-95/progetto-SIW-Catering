package it.siw.catering.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import it.siw.catering.model.Piatto;

public interface PiattoRepository extends CrudRepository<Piatto, Long>{
	
	public Optional<Piatto> findByNome(String nome);


}

package it.siw.catering.repository;

import org.springframework.data.repository.CrudRepository;

import it.siw.catering.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
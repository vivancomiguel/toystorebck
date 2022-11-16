package com.toystore.repository;

import java.util.Optional; // Lanza excepciones si no existe

import org.springframework.data.repository.CrudRepository;

import com.toystore.entity.*;

public interface ICostumerRepository extends CrudRepository <Costumer, Long> {
	Optional<Costumer> findByEmail(String email);
	Iterable<Costumer> findByIsActive(Boolean state);
	Iterable<Costumer> findByIsActiveOrderByLastNameAsc(Boolean state);
	boolean existsByEmail(String email);
}

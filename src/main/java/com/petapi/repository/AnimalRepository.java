package com.petapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petapi.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
	
     Animal findByCodigo(Long codigo);

     public List<Animal> findByNomeContainingIgnoreCase(String nome);

	

	
	
	
	
	
}

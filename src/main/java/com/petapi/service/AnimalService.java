package com.petapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petapi.model.Animal;
import com.petapi.repository.AnimalRepository;

@Service
public class AnimalService {
	
    @Autowired
    AnimalRepository animalRepository;
    
	public Animal salvar(Animal animal) {
		
		return animalRepository.save(animal);
	}

	
}

package com.petapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.petapi.error.ResourceNotFoundException;
import com.petapi.model.Animal;
import com.petapi.model.Cliente;
import com.petapi.repository.AnimalRepository;
import com.petapi.service.AnimalService;

@CrossOrigin("*")
@RestController
@RequestMapping("/animais")
public class AnimalResource {
	
@Autowired
AnimalRepository animalRepository;

@Autowired
AnimalService animalService;

@GetMapping
public ResponseEntity<?> listAll() {
	
	return new ResponseEntity<>(animalRepository.findAll(), HttpStatus.OK);	
}

@GetMapping("/{codigo}")
public ResponseEntity<?> buscarPeloCodigo(@PathVariable("codigo") Long codigo) {
	VerificaSeExisteAnimal(codigo);
	Animal animal = animalRepository.findById(codigo).orElse(null);
	return   new ResponseEntity<> (animal, HttpStatus.OK);    
		 
}

@GetMapping(path ="buscarAnimal/{nome}")
public ResponseEntity<?> buscarAnimal(@PathVariable String nome) {
	return new ResponseEntity<>(animalRepository.findByNomeContainingIgnoreCase(nome), HttpStatus.OK);
	
}


@PostMapping
public ResponseEntity<?> criar(@Valid @RequestBody Animal animal, HttpServletResponse response) {
	Animal animalSalva = animalRepository.save(animal);
	return new ResponseEntity<>(animalSalva, HttpStatus.CREATED);
}

@DeleteMapping("/{codigo}")
public ResponseEntity<?> remover(@PathVariable Long codigo) {
	 VerificaSeExisteAnimal(codigo);
	animalRepository.deleteById(codigo);
	return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
}

@PutMapping
public ResponseEntity<?> atualizar(@Valid @RequestBody Animal animal, HttpServletResponse response) {
	VerificaSeExisteAnimal(animal.getCodigo()); 
	animalRepository.save(animal);
	return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}
private void VerificaSeExisteAnimal(Long codigo) {
	if (animalRepository.findByCodigo(codigo) == null)
		throw 	 new ResourceNotFoundException("Animal não encontrado com  o código "+codigo);
			
}
}

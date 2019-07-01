package com.petapi.resource;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
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
import com.petapi.model.Cliente;
import com.petapi.repository.ClienteRepository;
import com.petapi.service.ClienteService;
@CrossOrigin("*")
@RestController
@RequestMapping("/clientes")
public class ClienteResource {

	@Autowired
	private ClienteRepository clienteRepository;
	
	//@Autowired
	//private ClienteService clienteService;
	
	@GetMapping
	public ResponseEntity<?> listAll() {
		
		return new ResponseEntity<>(clienteRepository.findAll(), HttpStatus.OK);	
	}
	
	@GetMapping("/{codigo}")
	public ResponseEntity<?> buscarPeloCodigo(@PathVariable Long codigo) {
		 VerificaSeExisteCliente(codigo); 
		 Cliente cliente = clienteRepository.findByCodigo(codigo);
		 return   new ResponseEntity<> (cliente, HttpStatus.OK);
	}
	
	@GetMapping(path ="buscarCliente/{nome}")
	public ResponseEntity<?> buscarCliente(@PathVariable String nome) {
		
		return new ResponseEntity<> (clienteRepository.findByNomeContainingIgnoreCaseOrderByNomeAsc(nome), HttpStatus.OK);
		
			
	}
	
	
	@PostMapping
	public ResponseEntity<?> criar(@Valid @RequestBody Cliente cliente, HttpServletResponse response) {
		Cliente clienteSalva = clienteRepository.save(cliente);
		return new ResponseEntity<>(clienteSalva, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{codigo}")
	public ResponseEntity<?> remover(@PathVariable Long codigo) {
		 VerificaSeExisteCliente(codigo); 
		clienteRepository.deleteById(codigo);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@PutMapping
	public ResponseEntity<?> atualizar(@Valid @RequestBody Cliente cliente, HttpServletResponse response) {
		 VerificaSeExisteCliente(cliente.getCodigo()); 
		clienteRepository.save(cliente);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	private void VerificaSeExisteCliente(Long codigo) {
		if (clienteRepository.findByCodigo(codigo) == null)
			throw 	 new ResourceNotFoundException("Cliente não encontrado com  o código "+codigo);
				
	}
	
}

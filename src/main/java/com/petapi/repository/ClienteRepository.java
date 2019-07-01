package com.petapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petapi.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	Cliente findByCodigo(Long codigo);

	public List<Cliente> findByNomeContainingIgnoreCase(String nome);
	public List<Cliente> findByNomeContainingIgnoreCaseOrderByNomeAsc(String nome);

	
}

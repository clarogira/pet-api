package com.petapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petapi.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {
	Venda	findByCodigo(Long codigo);

	


	
}

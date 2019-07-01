package com.petapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petapi.model.Servico;



public interface ServicoRepository extends JpaRepository<Servico, Long> {
	public Optional<Servico> findByNomeIgnoreCase(String nome);
	Servico findByCodigo(Long codigo);
}

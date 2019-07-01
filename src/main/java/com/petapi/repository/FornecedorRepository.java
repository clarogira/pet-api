package com.petapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.petapi.model.Fornecedor;

public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{
Fornecedor findByCodigo(Long codigo);
List<Fornecedor> findByNomeContainingIgnoreCase(@Param("nome") String nome);
List<Fornecedor> findAllByOrderByNomeAsc();
public Optional<Fornecedor> findByNomeIgnoreCase(String nome);

}

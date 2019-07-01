package com.petapi.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.petapi.model.Fornecedor;
import com.petapi.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	Produto findByCodigo(Long codigoProduto);

 List<Produto> findByNomeContainingIgnoreCase(@Param("nome") String nome);
 public Optional<Produto> findByNomeIgnoreCase(String nome);
	
	 @Query("select  p.fornecedor from Produto as p")
	 List<Fornecedor> fornecedoresComProdutos();

	
}
   
package com.petapi.service;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.petapi.model.Cliente;
import com.petapi.repository.ClienteRepository;

@Service
public class ClienteService {
	
    @Autowired
    ClienteRepository clienteRepository;
    
	public Cliente salvar(Long codigo, @Valid Cliente cliente) throws BeansException {
        Cliente clienteSalva = buscarClientePeloCodigo(codigo);
		
		BeanUtils.copyProperties(cliente, clienteSalva, "codigo");
		return clienteRepository.save(clienteSalva);
	}

	private Cliente buscarClientePeloCodigo(Long codigo) {
		Cliente clienteSalva = clienteRepository.findById(codigo).orElse(null);
		if (clienteSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return clienteSalva;
	}

}

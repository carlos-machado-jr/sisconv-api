package br.mil.marinha.sisconvapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Veiculos;
import br.mil.marinha.sisconvapi.repositories.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	VeiculoRepository repo;
	
	public List<Veiculos> findAll(){
		return repo.findAll();
	}
}

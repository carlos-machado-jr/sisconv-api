package br.mil.marinha.sisconvapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Montadora;
import br.mil.marinha.sisconvapi.repositories.MontadoraRepository;

@Service
public class MontadoraService {
	
	@Autowired
	MontadoraRepository repo;
	
	public List<Montadora> findAll(){
		return repo.findAll();
	}
	
	public Montadora findByDescricao(String descricao) {
		return repo.findByDescricao(descricao);
	}
}

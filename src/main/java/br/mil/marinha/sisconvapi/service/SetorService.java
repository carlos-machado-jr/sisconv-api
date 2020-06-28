package br.mil.marinha.sisconvapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Setor;
import br.mil.marinha.sisconvapi.repositories.SetorRepository;

@Service
public class SetorService {

	@Autowired
	SetorRepository repo;
	
	
	public List<Setor> findAll(){
		return repo.findAll();
	}
	
	public Setor findByDescricao(String descricao) {
		return repo.findByDescricao(descricao);
	}
}

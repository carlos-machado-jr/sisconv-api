package br.mil.marinha.sisconvapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Cor;
import br.mil.marinha.sisconvapi.repositories.CorRepository;

@Service
public class CorService {

	@Autowired
	CorRepository repo;
	
	public List<Cor> findAll(){
		return repo.findAll();
	}
	
	public Cor findByDescricao(String descricao) {
		return repo.findByDescricao(descricao);
	}
}

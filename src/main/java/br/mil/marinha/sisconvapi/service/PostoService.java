package br.mil.marinha.sisconvapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Posto;
import br.mil.marinha.sisconvapi.repositories.PostoRepository;

@Service
public class PostoService {

	@Autowired
	PostoRepository repo;
	
	public List<Posto> findAll(){
		return repo.findAll();
	}
	
	public Posto findByDescricao(String descricao) {
		return repo.findByDescricao(descricao);
	}
}

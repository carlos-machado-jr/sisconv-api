package br.mil.marinha.sisconvapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Cartao;
import br.mil.marinha.sisconvapi.repositories.CartaoRepository;

@Service
public class CartaoService {

	@Autowired
	CartaoRepository repo;
	
	public List<Cartao> findAll(){
		return repo.findAll();
	}
}

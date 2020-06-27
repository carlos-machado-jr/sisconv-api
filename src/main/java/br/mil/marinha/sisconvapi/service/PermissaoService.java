package br.mil.marinha.sisconvapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Permissoes;
import br.mil.marinha.sisconvapi.repositories.PermissaoRepository;

@Service
public class PermissaoService {

	@Autowired
	PermissaoRepository repo;
	
	public List<Permissoes> findAll(){
		return repo.findAll();
	}
}

package br.mil.marinha.sisconvapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Proprietarios;
import br.mil.marinha.sisconvapi.repositories.ProprietarioRepository;

@Service
public class ProprietarioService {
	
	@Autowired
	ProprietarioRepository repo;
	
	public List<Proprietarios> findAll(){
		return repo.findAll();
	}
}

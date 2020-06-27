package br.mil.marinha.sisconvapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Usuarios;
import br.mil.marinha.sisconvapi.repositories.UsuariosRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuariosRepository repo;
	
	public List<Usuarios> findAll(){
		return repo.findAll();
	}

}

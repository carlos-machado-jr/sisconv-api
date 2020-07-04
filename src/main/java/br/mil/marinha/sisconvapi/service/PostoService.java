package br.mil.marinha.sisconvapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Posto;
import br.mil.marinha.sisconvapi.repositories.PostoRepository;
import br.mil.marinha.sisconvapi.service.exceptions.ObjectNotFoundException;

@Service
public class PostoService {

	@Autowired
	PostoRepository postoRepository;
	
	public List<Posto> findAll(){
		return postoRepository.findAll();
	}
	
	public Posto findByDescricao(String descricao) {
		return postoRepository.findByDescricao(descricao);
	}
	
	public Posto findById(Integer id) {
		Optional<Posto> posto =  postoRepository.findById(id);
		return posto.orElseThrow(() -> objectNotFound(id));
	}
	
	public void create(Posto posto) {
		postoRepository.save(posto);
	}
	
	public void update(Posto posto) {
		postoRepository.save(posto);
	}
	
	
	private ObjectNotFoundException objectNotFound(Integer id) {
		return new ObjectNotFoundException("Posto não encontrado!  id:" + id + ", Tipo: " + Posto.class.getName());
	}
}

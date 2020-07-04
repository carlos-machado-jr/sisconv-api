package br.mil.marinha.sisconvapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Cor;
import br.mil.marinha.sisconvapi.repositories.CorRepository;
import br.mil.marinha.sisconvapi.service.exceptions.ObjectNotFoundException;

@Service
public class CorService {

	@Autowired
	CorRepository corRepository;
	
	public List<Cor> findAll(){
		return corRepository.findAll();
	}
	
	public Cor findById(Integer id) {
		Optional<Cor> cor = corRepository.findById(id);
		return cor.orElseThrow(() -> objectNotFoundException(id));
	}

	public Cor findByDescricao(String descricao) {
		return corRepository.findByDescricao(descricao);
	}
	
	public void create(Cor cor) {
		corRepository.save(cor);
	}
	
	public Cor update(Cor cor) {
		return corRepository.save(cor);
	}
	
	
	private ObjectNotFoundException objectNotFoundException(Integer id) {
		return new ObjectNotFoundException("Cor não encontrada!  id: " + id + ", Tipo: " + Cor.class.getName());
	}

	
}

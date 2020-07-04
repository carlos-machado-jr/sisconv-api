package br.mil.marinha.sisconvapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Setor;
import br.mil.marinha.sisconvapi.repositories.SetorRepository;
import br.mil.marinha.sisconvapi.service.exceptions.ObjectNotFoundException;

@Service
public class SetorService {

	@Autowired
	SetorRepository setorRepository;
	
	
	public List<Setor> findAll(){
		return setorRepository.findAll();
	}
	
	public Setor findByDescricao(String descricao) {
		return setorRepository.findByDescricao(descricao);
	}
	
	public Setor findById(Integer id) {
		Optional<Setor> setor = setorRepository.findById(id);
		return setor.orElseThrow(() -> objectNotFound(id));
	}
	
	public void create(Setor setor) {
		setorRepository.save(setor);
	}
	
	public void update(Setor s) {
		Setor setor = findById(s.getId());
		setor.setDesc_setor(s.getDesc_setor());
		setorRepository.save(setor);
	}
	
	private ObjectNotFoundException objectNotFound(Integer id) {
		return new ObjectNotFoundException("Setor não encontrado!  id: " + id + ", Tipo: " + Setor.class.getName());
	}
}

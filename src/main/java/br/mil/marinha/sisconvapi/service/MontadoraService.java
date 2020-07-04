package br.mil.marinha.sisconvapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Montadora;
import br.mil.marinha.sisconvapi.repositories.MontadoraRepository;
import br.mil.marinha.sisconvapi.service.exceptions.ObjectNotFoundException;

@Service
public class MontadoraService {

	@Autowired
	MontadoraRepository montadoraRepository;

	public List<Montadora> findAll() {
		return montadoraRepository.findAll();
	}

	public Montadora findByDescricao(String descricao) {
		return montadoraRepository.findByDescricao(descricao);
	}

	public Montadora findById(Integer id) {
		Optional<Montadora> montadora = montadoraRepository.findById(id);
		return montadora.orElseThrow(() -> objectNotFoundException(id));
	}

	public void create(Montadora montadora) {
		montadoraRepository.save(montadora);
	}

	public void update(Montadora montadora) {
		findById(montadora.getId());
		montadoraRepository.save(montadora);
	}

	private ObjectNotFoundException objectNotFoundException(Integer id) {
		return new ObjectNotFoundException(
				"Montadora não encontrada!  id: " + id + ", Tipo: " + Montadora.class.getName());
	}
}

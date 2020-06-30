package br.mil.marinha.sisconvapi.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Cor;
import br.mil.marinha.sisconvapi.domain.Montadora;
import br.mil.marinha.sisconvapi.domain.Proprietarios;
import br.mil.marinha.sisconvapi.domain.Veiculos;
import br.mil.marinha.sisconvapi.dto.VeiculosDTO;
import br.mil.marinha.sisconvapi.repositories.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	VeiculoRepository repo;

	@Autowired
	MontadoraService montadoraService;

	@Autowired
	CorService corService;

	@Autowired
	ProprietarioService proprietarioService;

	public List<Veiculos> findAll() {
		return repo.findAll();
	}

	@Transactional
	public Veiculos save(Veiculos v) {
		return repo.save(v);
	}

	public Set<Veiculos> create(Set<VeiculosDTO> veiculosLista, Proprietarios p) {
		
		return veiculosLista.stream().map(v -> transformDTO(v, p)).collect(Collectors.toSet());

	}
	
	
	
	private Veiculos transformDTO(VeiculosDTO dto, Proprietarios p) {
		Veiculos v = new Veiculos(dto.getId(), dto.getModelo(), dto.getAno(), dto.getPlaca(), dto.getChassi());
		Montadora montadora = montadoraService.findByDescricao(dto.getMontadora());
		Cor cor = corService.findByDescricao(dto.getCor());
		
		v.setCor(cor);
		v.setMontadora(montadora);
		v.setProprietario(p);
		return repo.save(v);
	}

}

package br.mil.marinha.sisconvapi.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Cor;
import br.mil.marinha.sisconvapi.domain.Montadora;
import br.mil.marinha.sisconvapi.domain.Proprietarios;
import br.mil.marinha.sisconvapi.domain.Veiculos;
import br.mil.marinha.sisconvapi.dto.VeiculosDTO;
import br.mil.marinha.sisconvapi.repositories.VeiculoRepository;
import br.mil.marinha.sisconvapi.service.exceptions.ObjectNotFoundException;

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

	public List<Veiculos> findAllActivated() {
		return repo.findAllActivated();
	}

	public List<Veiculos> findAllDisabled() {
		return repo.findAllDisabled();
	}

	public Veiculos findById(Integer id) {
		Optional<Veiculos> v = repo.findById(id);
		return v.orElseThrow(() -> objectNotFoundException(id));

	}

	public Veiculos findByIdActived(Integer id) {
		Optional<Veiculos> v = repo.findByIdAndAtivo(id, true);
		return v.orElseThrow(() -> objectNotFoundException(id));

	}

	public Veiculos findByIdDisabled(Integer id) {
		Optional<Veiculos> v = repo.findByIdAndAtivo(id, false);
		return v.orElseThrow(() -> objectNotFoundException(id));

	}

	public Set<Veiculos> createOrUpdate(Set<VeiculosDTO> veiculosLista, Proprietarios p) {

		return veiculosLista.stream().map(v -> createVeiculo(v, p)).collect(Collectors.toSet());

	}

	public void deactivate(Integer idProprietario) {
		Set<Veiculos> veiculos = repo.findByIdProprietario(idProprietario);
		veiculos.forEach(v -> {
			v.setAtivo(false);
			repo.save(v);
		});

	}
	public void deactivateById(Integer idVeiculo) {
		Veiculos v = findById(idVeiculo);
		
			v.setAtivo(false);
			repo.save(v);
		

	}
	

	private ObjectNotFoundException objectNotFoundException(Integer id) {
		return new ObjectNotFoundException("Veiculo nao encontrado! Id: " + id + ", Tipo: " + Veiculos.class.getName());
	}

	private Veiculos createVeiculo(VeiculosDTO dto, Proprietarios p) {

		Veiculos v = new Veiculos(dto.getId(), dto.getModelo(), dto.getAno(), dto.getPlaca(), dto.getChassi(), true);
		Montadora montadora = montadoraService.findByDescricao(dto.getMontadora());
		Cor cor = corService.findByDescricao(dto.getCor());

		v.setCor(cor);
		v.setMontadora(montadora);
		v.setProprietario(p);
		return repo.save(v);
	}

}

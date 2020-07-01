package br.mil.marinha.sisconvapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Posto;
import br.mil.marinha.sisconvapi.domain.Proprietarios;
import br.mil.marinha.sisconvapi.domain.Setor;
import br.mil.marinha.sisconvapi.dto.ProprietariosDTO;
import br.mil.marinha.sisconvapi.repositories.ProprietarioRepository;
import br.mil.marinha.sisconvapi.service.exceptions.ObjectNotFoundException;

@Service
public class ProprietarioService {

	@Autowired
	ProprietarioRepository repo;

	@Autowired
	PostoService postoService;

	@Autowired
	SetorService setorService;

	@Autowired
	CartaoService cartaoService;

	public List<Proprietarios> findByAllActivated() {
		return repo.findByAllActivated();
	}

	public List<Proprietarios> findByAllDisabled() {
		return repo.findByAllDisabled();

	}

	public Proprietarios findByIdAndAtivo(Integer id) {
		Optional<Proprietarios> c = repo.findByIdAndAtivo(id);
		return c.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado! Id: " + id + ", Tipo: " + Proprietarios.class.getName()));

	}

	public Proprietarios findById(Integer id) {
		Optional<Proprietarios> c = repo.findById(id);
		return c.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado! Id: " + id + ", Tipo: " + Proprietarios.class.getName()));

	}

	public Proprietarios create(ProprietariosDTO dto) {

		Proprietarios p = transformDTO(dto);
		p.setId(null);
		return repo.save(p);

	}

	public Proprietarios update(ProprietariosDTO dto) {
		Proprietarios p = transformDTO(dto);

		return repo.save(p);
	}

	public void deactivate(Integer id) {
		Proprietarios p = findByIdAndAtivo(id);
		p.setAtivo(false);
		cartaoService.deactivate(p.getCartao());
		p.setCartao(null);

		repo.save(p);

	}

	

	private Proprietarios transformDTO(ProprietariosDTO dto) {

		Proprietarios p = new Proprietarios(dto.getId(), dto.getNome(), dto.getEmail(), dto.getNip(), dto.getCnh(),
				true);
		Posto posto = postoService.findByDescricao(dto.getPosto());
		Setor setor = setorService.findByDescricao(dto.getSetor());
		p.setSetor(setor);
		p.setPosto(posto);

		p.setCartao(cartaoService.ifExist(dto));

		return p;
	}

}

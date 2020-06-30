package br.mil.marinha.sisconvapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Posto;
import br.mil.marinha.sisconvapi.domain.Proprietarios;
import br.mil.marinha.sisconvapi.domain.Setor;
import br.mil.marinha.sisconvapi.dto.ProprietariosDTO;
import br.mil.marinha.sisconvapi.repositories.ProprietarioRepository;

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

	public List<Proprietarios> findAll() {
		return repo.findAll();
	}

	public Proprietarios findById(Integer id) {
		return repo.findById(id).orElse(null);
	}

	

	public Proprietarios create(ProprietariosDTO dto) {

		if (findById(dto.getId()) == null && dto.getId() != null) {
			throw new NullPointerException("Esse id não existe");
		}
		Proprietarios p = transformDTO(dto);

		Posto posto = postoService.findByDescricao(dto.getPosto());
		Setor setor = setorService.findByDescricao(dto.getSetor());
		p.setSetor(setor);
		p.setPosto(posto);

		p.setCartao(cartaoService.ifExist(dto));

		return repo.save(p);

	}

	private Proprietarios transformDTO(ProprietariosDTO dto) {
		return new Proprietarios(dto.getId(), dto.getNome(), dto.getEmail(), dto.getNip(), dto.getCnh(), true);
	}
}

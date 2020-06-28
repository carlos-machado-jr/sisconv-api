package br.mil.marinha.sisconvapi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Cartao;
import br.mil.marinha.sisconvapi.domain.Posto;
import br.mil.marinha.sisconvapi.domain.Proprietarios;
import br.mil.marinha.sisconvapi.domain.Setor;
import br.mil.marinha.sisconvapi.domain.StatusCartao;
import br.mil.marinha.sisconvapi.dto.VeiculosDTO;
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
	
	public Proprietarios find(Integer id) {
		return repo.findById(id).orElse(null);
	}

	public Proprietarios save(Proprietarios p) {
		return repo.save(p);
	}

	public Proprietarios fromDTO(VeiculosDTO dto) {
		Proprietarios p = repo.findByNip(dto.getNip_proprietario());
		if (p == null) {
			p = transformDTO(dto);
			
			Posto posto = postoService.findByDescricao(dto.getPosto_proprietario());
			Setor setor = setorService.findByDescricao(dto.getSetor_proprietario());
			Cartao cartao = new Cartao(null, dto.getCartao_proprietario(), new Date());
			StatusCartao status = new StatusCartao();
			status.setId(1);
			cartao.setStatusCartao(status);
			p.setCartao(cartaoService.save(cartao));
			p.setSetor(setor);
			p.setPosto(posto);
			return p;
		}
		return p;
	}
	
	
	
	
	
	private Proprietarios transformDTO(VeiculosDTO dto) {
		return new Proprietarios(null, dto.getNome_proprietario(), dto.getEmail_proprietario(),
				dto.getNip_proprietario(), dto.getCnh_proprietario(), true);
	}
}

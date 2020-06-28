package br.mil.marinha.sisconvapi.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Cartao;
import br.mil.marinha.sisconvapi.domain.Cor;
import br.mil.marinha.sisconvapi.domain.Montadora;
import br.mil.marinha.sisconvapi.domain.Posto;
import br.mil.marinha.sisconvapi.domain.Proprietarios;
import br.mil.marinha.sisconvapi.domain.Setor;
import br.mil.marinha.sisconvapi.domain.StatusCartao;
import br.mil.marinha.sisconvapi.domain.Veiculos;
import br.mil.marinha.sisconvapi.dto.VeiculosDTO;
import br.mil.marinha.sisconvapi.repositories.CorRepository;
import br.mil.marinha.sisconvapi.repositories.MontadoraRepository;
import br.mil.marinha.sisconvapi.repositories.PostoRepository;
import br.mil.marinha.sisconvapi.repositories.SetorRepository;
import br.mil.marinha.sisconvapi.repositories.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	VeiculoRepository repo;
	
	@Autowired
	PostoRepository postoRepo;
	
	@Autowired
	SetorRepository setorRepo;
	
	@Autowired
	MontadoraRepository montadoraRepo;
	
	@Autowired
	CorRepository corRepo;
	
	@Autowired
	CartaoService cartaoService;
	
	@Autowired
	ProprietarioService proprietarioService;
	
	public List<Veiculos> findAll(){
		return repo.findAll();
	}
	
	@Transactional
	public Veiculos save(Veiculos v ) {
		return repo.save(v);
	}
	
	
	public Veiculos fromDTO(VeiculosDTO dto) {
		Veiculos v = new Veiculos(dto.getId(), 	dto.getModelo() , dto.getAno(), dto.getPlaca(),dto.getChassi());
		Proprietarios p = new Proprietarios(null, dto.getNome_proprietario(), dto.getEmail_proprietario(), dto.getNip_proprietario(), dto.getCnh_proprietario(), true);
		Posto posto = postoRepo.findByDescricao(dto.getPosto_proprietario());
		Setor setor = setorRepo.findByDescricao(dto.getSetor_proprietario());
		Montadora montadora = montadoraRepo.findByDescricao(dto.getMontadora());
		Cor cor = corRepo.findByDescricao(dto.getCor());
		Cartao cartao = new Cartao(null, dto.getCartao_proprietario(), new Date());
		StatusCartao status = new StatusCartao();
		status.setId(1);
		cartao.setStatusCartao(status);
		p.setCartao(cartaoService.save(cartao));
		p.setSetor(setor);
		p.setPosto(posto);
		v.setProprietario(proprietarioService.save(p));
		v.setCor(cor);
		v.setMontadora(montadora);
		return v;
	}
	
}

package br.mil.marinha.sisconvapi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Cartao;
import br.mil.marinha.sisconvapi.domain.StatusCartao;
import br.mil.marinha.sisconvapi.dto.VeiculosDTO;
import br.mil.marinha.sisconvapi.repositories.CartaoRepository;

@Service
public class CartaoService {

	@Autowired
	CartaoRepository repo;
	
	public List<Cartao> findAll(){
		return repo.findAll();
	}
	
	public Cartao save(Cartao c) {
		return repo.save(c);
	}
	
	public Cartao ifExist(VeiculosDTO dto) {
		Cartao c = repo.findByNumeroCartao(dto.getCartao_proprietario());
		
		if(c == null) {
			return new Cartao(null, dto.getCartao_proprietario(), new Date());
		} else 
		c.setValidade(new Date());
		StatusCartao statusCartao = new StatusCartao(2, "Indisponivel");
		c.setStatusCartao(statusCartao);
		
		return repo.save(c);
	}
	
	
}

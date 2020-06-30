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

	public List<Cartao> findAll() {
		return repo.findAll();
	}

	public Cartao save(Cartao c) {
		return repo.save(c);
	}

	public Cartao ifExist(VeiculosDTO dto) {
		Cartao c = repo.findByNumeroCartao(dto.getCartao_proprietario());

		if (c == null) {
			
			return setCartao(dto);
			
		} else if (c.getStatusCartao().getDesc_status_cartao().equals("Indisponivel")) {
			
			throw new NullPointerException("Esse cartao ja está em uso");
		}

		return setCartao(c);

	}

	private Cartao setCartao(Cartao c) {

		Cartao cartao = new Cartao(c.getId(), c.getNumero(), new Date());

		StatusCartao statusCartao = new StatusCartao(2, "Indisponivel");
		cartao.setStatusCartao(statusCartao);
		return repo.save(cartao);
	}
	
	private Cartao setCartao(VeiculosDTO dto) {

		Cartao cartao = new Cartao(null, dto.getCartao_proprietario(), new Date());

		StatusCartao statusCartao = new StatusCartao(2, "Indisponivel");
		cartao.setStatusCartao(statusCartao);
		return repo.save(cartao);
	}

}

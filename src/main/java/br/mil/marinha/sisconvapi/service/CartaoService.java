package br.mil.marinha.sisconvapi.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mil.marinha.sisconvapi.domain.Cartao;
import br.mil.marinha.sisconvapi.domain.StatusCartao;
import br.mil.marinha.sisconvapi.dto.CartaoDTO;
import br.mil.marinha.sisconvapi.dto.ProprietariosDTO;
import br.mil.marinha.sisconvapi.repositories.CartaoRepository;
import br.mil.marinha.sisconvapi.service.exceptions.ObjectNotFoundException;

@Service
public class CartaoService {

	@Autowired
	CartaoRepository repo;

	public List<Cartao> findAllifDisponivel() {
		return repo.findByAllStatus("Disponivel");
	}

	public List<Cartao> findAllifIndisponivel() {
		return repo.findByAllStatus("Indisponivel");
	}

	public Cartao findByNumeroCartao(String numero) {
		return repo.findByNumeroCartao(numero);

	}

	public Cartao save(Cartao c) {
		return repo.save(c);
	}

	public Cartao convertDTO(CartaoDTO dto) {
		Cartao c = new Cartao(null, dto.getNumero(), new Date());
		StatusCartao status = new StatusCartao(1, "Disponivel");
		c.setStatusCartao(status);
		return c;
	}

	public Cartao ifExist(ProprietariosDTO dto) {
		Cartao c = findByNumeroCartao(dto.getCartao());

		if (c == null) {

			return setCartao(dto);

		} else if (IfStatusIs(c, "Indisponivel") && dto.getId() == null) {

			throw new ObjectNotFoundException("Esse cartao ja está em uso");
		}

		return setCartao(c);

	}

	@Transactional
	public void deactivate(Cartao c) {

		c.setProprietario(null);
		StatusCartao status = new StatusCartao(1, "Disponivel");
		c.setStatusCartao(status);

		repo.save(c);

	}

	
	
	
	private boolean IfStatusIs(Cartao c, String status) {
		return c.getStatusCartao().getDesc_status_cartao().equals(status);
	}

	private Cartao setCartao(Cartao c) {

		Cartao cartao = new Cartao(c.getId(), c.getNumero(), new Date());

		StatusCartao statusCartao = new StatusCartao(2, "Indisponivel");
		cartao.setStatusCartao(statusCartao);
		return repo.save(cartao);
	}

	private Cartao setCartao(ProprietariosDTO dto) {

		Cartao cartao = new Cartao(null, dto.getCartao(), new Date());

		StatusCartao statusCartao = new StatusCartao(2, "Indisponivel");
		cartao.setStatusCartao(statusCartao);
		return repo.save(cartao);
	}

}

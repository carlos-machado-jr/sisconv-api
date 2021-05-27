package br.mil.marinha.sisconvapi.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.mil.marinha.sisconvapi.domain.Cartao;

import br.mil.marinha.sisconvapi.dto.CartaoDTO;
import br.mil.marinha.sisconvapi.dto.ProprietariosDTO;
import br.mil.marinha.sisconvapi.repositories.CartaoRepository;
import br.mil.marinha.sisconvapi.service.exceptions.ObjectNotFoundException;

@Service
public class CartaoService {

	@Autowired
	CartaoRepository repo;
	
	public List<Cartao> findAll(){
		return repo.findAll();
	}
	public List<Cartao> findAllifDisponivel() {
		return repo.findByAllStatus(true);
	}

	public List<Cartao> findAllifIndisponivel() {
		return repo.findByAllStatus(false);
	}
	
	public Cartao findById(Integer id) {
		Optional<Cartao> cartao =  repo.findById(id);
		return cartao.orElseThrow(() -> new ObjectNotFoundException("Cartao nao encontrado!"));
	}
	
	public Cartao findByNumeroCartao(String numero) {
		return repo.findByNumeroCartao(numero);

	}

	public Cartao save(Cartao c) {
		return repo.save(c);
	}

	public Cartao convertDTO(CartaoDTO dto) {
		Cartao c = new Cartao(null, dto.getNumero(), new Date());
		c.setStatusCartao(true);
		return c;
	}

	public Cartao ifExist(ProprietariosDTO dto) {
		Cartao c = findByNumeroCartao(dto.getCartao());

		if (c == null) {

			return setCartao(dto);

		} else if (IfStatusIs(c, false) && dto.getId() == null) {

			throw new ObjectNotFoundException("Esse cartao ja está em uso");
		}

		return setCartao(c);

	}

	@Transactional
	public void deactivate(Cartao c) {

		c.setProprietario(null);
		c.setStatusCartao(false);

		repo.save(c);

	}

	
	
	
	private boolean IfStatusIs(Cartao c, Boolean status) {
		return c.getStatusCartao().equals(status);
	}

	private Cartao setCartao(Cartao c) {

		Cartao cartao = new Cartao(c.getId(), c.getNumero(), new Date());

		cartao.setStatusCartao(false);
		return repo.save(cartao);
	}

	private Cartao setCartao(ProprietariosDTO dto) {

		Cartao cartao = new Cartao(null, dto.getCartao(), new Date());

		
		cartao.setStatusCartao(false);
		return repo.save(cartao);
	}

}

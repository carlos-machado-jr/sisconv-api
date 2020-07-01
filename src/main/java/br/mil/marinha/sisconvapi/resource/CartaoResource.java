package br.mil.marinha.sisconvapi.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.mil.marinha.sisconvapi.domain.Cartao;
import br.mil.marinha.sisconvapi.dto.CartaoDTO;
import br.mil.marinha.sisconvapi.service.CartaoService;

@RestController
@RequestMapping("/cartoes")
public class CartaoResource {

	@Autowired
	CartaoService service;

	@GetMapping("/tudo")
	public ResponseEntity<List<CartaoDTO>> findAll() {
		List<Cartao> cartaoList = service.findAllifDisponivel();
		List<CartaoDTO> dtoList = createCartaoDTO(cartaoList);

		return ResponseEntity.ok(dtoList);
	}

	@GetMapping("/disponiveis")
	public ResponseEntity<List<CartaoDTO>> findAllifDisponivel() {
		List<Cartao> cartaoList = service.findAllifDisponivel();
		List<CartaoDTO> dtoList = createCartaoDTO(cartaoList);

		return ResponseEntity.ok(dtoList);
	}

	@GetMapping("/indisponiveis")
	public ResponseEntity<List<CartaoDTO>> findAllifIndisponivel() {
		List<Cartao> cartaoList = service.findAllifIndisponivel();
		List<CartaoDTO> dtoList = createCartaoDTO(cartaoList);

		return ResponseEntity.ok(dtoList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CartaoDTO> findById(@PathVariable Integer id) {
		Cartao c = service.findById(id);
		CartaoDTO dto = new CartaoDTO(c);

		return ResponseEntity.ok(dto);
	}

	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody CartaoDTO dto) {
		Cartao c = service.convertDTO(dto);
		service.save(c);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	private List<CartaoDTO> createCartaoDTO(List<Cartao> cartaoList) {
		return cartaoList.stream()
				.map(c -> c.getProprietario() == null ? new CartaoDTO(c) : new CartaoDTO(c, c.getProprietario()))
				.collect(Collectors.toList());
	}
}

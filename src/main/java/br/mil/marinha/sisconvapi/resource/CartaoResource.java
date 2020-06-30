package br.mil.marinha.sisconvapi.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

	@GetMapping
	public @Valid ResponseEntity<List<CartaoDTO>> findAll() {
		List<Cartao> cartaoList = service.findAll();
		List<CartaoDTO> dtoList = cartaoList.stream()
				.map(c -> c.getProprietario() == null ? new CartaoDTO(c) : new CartaoDTO(c, c.getProprietario()))
				.collect(Collectors.toList());

		return ResponseEntity.ok(dtoList);
	}
	
	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody CartaoDTO dto){
		Cartao c = service.fromDTO(dto);
		service.save(c);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(c.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}

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

import br.mil.marinha.sisconvapi.domain.Proprietarios;
import br.mil.marinha.sisconvapi.dto.ProprietariosDTO;
import br.mil.marinha.sisconvapi.service.ProprietarioService;
import br.mil.marinha.sisconvapi.service.VeiculoService;

@RestController
@RequestMapping("/proprietarios")
public class ProprietarioResource {
	
	@Autowired
	ProprietarioService service;
	@Autowired
	VeiculoService veiculoService;
	
	@GetMapping
	public ResponseEntity<List<ProprietariosDTO>> findAll(){
		List<Proprietarios> proprietariosList = service.findAll();
		
		List<ProprietariosDTO> dtoList = proprietariosList.stream().map(p -> new ProprietariosDTO(p)).collect(Collectors.toList());
		return ResponseEntity.ok(dtoList);
	}
	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody ProprietariosDTO dto){
		
		Proprietarios p = service.fromDTO(dto);
		veiculoService.fromDTO(dto, p);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(p.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	
}

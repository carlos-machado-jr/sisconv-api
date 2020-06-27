package br.mil.marinha.sisconvapi.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.mil.marinha.sisconvapi.domain.Proprietarios;
import br.mil.marinha.sisconvapi.dto.ProprietariosDTO;
import br.mil.marinha.sisconvapi.service.ProprietarioService;

@RestController
@RequestMapping("/proprietarios")
public class ProprietarioResource {
	
	@Autowired
	ProprietarioService service;
	
	
	@GetMapping
	public ResponseEntity<List<ProprietariosDTO>> findAll(){
		List<Proprietarios> proprietariosList = service.findAll();
		
		List<ProprietariosDTO> dtoList = proprietariosList.stream().map(p -> new ProprietariosDTO(p)).collect(Collectors.toList());
		return ResponseEntity.ok(dtoList);
	}
	
}

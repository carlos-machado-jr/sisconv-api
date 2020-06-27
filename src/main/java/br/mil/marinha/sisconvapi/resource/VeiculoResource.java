package br.mil.marinha.sisconvapi.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.mil.marinha.sisconvapi.domain.Veiculos;
import br.mil.marinha.sisconvapi.dto.VeiculosDTO;
import br.mil.marinha.sisconvapi.service.VeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoResource {

	@Autowired
	VeiculoService service;
	
	@GetMapping
	public ResponseEntity<List<VeiculosDTO>> findAll(){
		List<Veiculos> veiculosList = service.findAll();
		
		List<VeiculosDTO> dtoList = veiculosList.stream().map(v -> new VeiculosDTO(v)).collect(Collectors.toList());
		
		return ResponseEntity.ok(dtoList);
	}
}

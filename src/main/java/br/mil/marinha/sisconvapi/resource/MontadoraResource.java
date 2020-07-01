package br.mil.marinha.sisconvapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.mil.marinha.sisconvapi.domain.Montadora;
import br.mil.marinha.sisconvapi.service.MontadoraService;

@RestController
@RequestMapping("/montadora")
public class MontadoraResource {

	@Autowired
	MontadoraService service;
	
	@GetMapping
	public ResponseEntity<List<Montadora>> findAll(){
		List<Montadora> listMontadora = service.findAll();
		
		return ResponseEntity.ok(listMontadora);
	}
}

package br.mil.marinha.sisconvapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.mil.marinha.sisconvapi.domain.Setor;
import br.mil.marinha.sisconvapi.service.SetorService;

@RestController
@RequestMapping("/setor")
public class SetorResource {

	@Autowired
	SetorService service;
	
	@GetMapping
	public ResponseEntity<List<Setor>> findAll(){
		List<Setor> listSetor = service.findAll();
		return ResponseEntity.ok(listSetor);
	}
}

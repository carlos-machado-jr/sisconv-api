package br.mil.marinha.sisconvapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.mil.marinha.sisconvapi.domain.Posto;
import br.mil.marinha.sisconvapi.service.PostoService;

@RestController
@RequestMapping("/posto")
public class PostoResource {

	@Autowired
	PostoService service;
	
	@GetMapping
	public ResponseEntity<List<Posto>> findAll(){
		List<Posto> listPosto = service.findAll();
		return ResponseEntity.ok(listPosto);
	}
}

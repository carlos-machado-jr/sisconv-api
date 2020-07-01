package br.mil.marinha.sisconvapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.mil.marinha.sisconvapi.domain.Cor;
import br.mil.marinha.sisconvapi.service.CorService;

@RestController
@RequestMapping("/cor")
public class CorResource {

	@Autowired
	CorService service;
	
	@GetMapping
	public ResponseEntity<List<Cor>> findAll(){
		List<Cor> listCor = service.findAll();
		
		return ResponseEntity.ok(listCor);
	}
}

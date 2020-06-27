package br.mil.marinha.sisconvapi.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.mil.marinha.sisconvapi.domain.Permissoes;
import br.mil.marinha.sisconvapi.service.PermissaoService;

@RestController
@RequestMapping("/permissoes")
public class PermissaoResource {
	
	@Autowired
	PermissaoService service;
	
	@GetMapping
	public ResponseEntity<List<Permissoes>> findAll(){
		List<Permissoes> listPermissoes = service.findAll();
		return ResponseEntity.ok(listPermissoes);
	}
}

package br.mil.marinha.sisconvapi.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.mil.marinha.sisconvapi.domain.Posto;
import br.mil.marinha.sisconvapi.service.PostoService;

@RestController
@RequestMapping("/posto")
public class PostoResource {

	@Autowired
	PostoService postoService;
	
	@GetMapping
	public ResponseEntity<List<Posto>> findAll(){
		List<Posto> listPosto = postoService.findAll();
		return ResponseEntity.ok(listPosto);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Posto> findById(@PathVariable Integer id){
		Posto posto = postoService.findById(id);
		return ResponseEntity.ok(posto);
	}
	
	@PreAuthorize("hasAnyRole('Administrador') || hasAnyRole('Supervisor')")
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Posto posto){
		postoService.create(posto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(posto.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('Administrador') || hasAnyRole('Supervisor')")
	@PutMapping("{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Posto posto){
		Posto p = postoService.findById(id);
		p.setDesc_posto(posto.getDesc_posto());
		postoService.update(p);
		return ResponseEntity.noContent().build();
	}
}

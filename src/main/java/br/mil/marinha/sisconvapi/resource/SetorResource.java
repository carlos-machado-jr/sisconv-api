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

import br.mil.marinha.sisconvapi.domain.Setor;
import br.mil.marinha.sisconvapi.service.SetorService;

@RestController
@RequestMapping("/setor")
public class SetorResource {

	@Autowired
	SetorService setorService;
	
	@GetMapping
	public ResponseEntity<List<Setor>> findAll(){
		List<Setor> listSetor = setorService.findAll();
		return ResponseEntity.ok(listSetor);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Setor> findById(@PathVariable Integer id){
		Setor setor = setorService.findById(id);
		return ResponseEntity.ok(setor);
	}
	
	@PreAuthorize("hasAnyRole('Administrador') || hasAnyRole('Supervisor')")
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Setor setor){
		setorService.create(setor);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(setor.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('Administrador') || hasAnyRole('Supervisor')")
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Setor setor){
		setor.setId(id);
		setorService.update(setor);
		
		return ResponseEntity.noContent().build();
	}
	
	
	
}

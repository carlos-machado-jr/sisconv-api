package br.mil.marinha.sisconvapi.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.mil.marinha.sisconvapi.domain.Montadora;
import br.mil.marinha.sisconvapi.service.MontadoraService;

@RestController
@RequestMapping("/montadora")
public class MontadoraResource {

	@Autowired
	MontadoraService montadoraService;
	
	@GetMapping
	public ResponseEntity<List<Montadora>> findAll(){
		List<Montadora> listMontadora = montadoraService.findAll();
		
		return ResponseEntity.ok(listMontadora);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Montadora> findById(@PathVariable Integer id){
		Montadora montadora = montadoraService.findById(id);
		return ResponseEntity.ok(montadora);
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Montadora montadora){
		montadoraService.create(montadora);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(montadora.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Montadora montadora){
		montadora.setId(id);
		montadoraService.update(montadora);
		
		return ResponseEntity.noContent().build();
	}
	
}

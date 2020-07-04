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

import br.mil.marinha.sisconvapi.domain.Cor;
import br.mil.marinha.sisconvapi.service.CorService;

@RestController
@RequestMapping("/cor")
public class CorResource {

	@Autowired
	CorService corService;

	@GetMapping
	public ResponseEntity<List<Cor>> findAll() {
		List<Cor> listCor = corService.findAll();

		return ResponseEntity.ok(listCor);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Cor> findById(@PathVariable Integer id) {
		Cor cor = corService.findById(id);

		return ResponseEntity.ok(cor);
	}

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody Cor cor) {
		corService.create(cor);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cor.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Cor c){
		
		Cor cor = corService.findById(id);
		cor.setDesc_cor(c.getDesc_cor());
		corService.update(cor);
		return ResponseEntity.noContent().build();
	}
	
}

package br.mil.marinha.sisconvapi.resource;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.mil.marinha.sisconvapi.domain.Proprietarios;
import br.mil.marinha.sisconvapi.dto.ProprietariosDTO;
import br.mil.marinha.sisconvapi.dto.VeiculosDTO;
import br.mil.marinha.sisconvapi.service.ProprietarioService;
import br.mil.marinha.sisconvapi.service.VeiculoService;

@RestController
@RequestMapping("/proprietarios")
public class ProprietarioResource {

	@Autowired
	ProprietarioService proprietariosService;
	@Autowired
	VeiculoService veiculoService;

	@GetMapping
	public ResponseEntity<List<ProprietariosDTO>> findByAllActivated() {
		List<Proprietarios> proprietariosList = proprietariosService.findByAllActivated();

		List<ProprietariosDTO> dtoList = proprietariosList.stream().map(p -> new ProprietariosDTO(p))
				.collect(Collectors.toList());
		return ResponseEntity.ok(dtoList);
	}
	
	@GetMapping("/desativados")
	public ResponseEntity<List<ProprietariosDTO>> findByAllDisabled() {
		List<Proprietarios> proprietariosList = proprietariosService.findByAllDisabled();

		List<ProprietariosDTO> dtoList = proprietariosList.stream().map(p -> new ProprietariosDTO(p))
				.collect(Collectors.toList());
		return ResponseEntity.ok(dtoList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProprietariosDTO> findById(@PathVariable Integer id) {

		Proprietarios p = proprietariosService.findByIdAndAtivo(id);

		ProprietariosDTO dto = new ProprietariosDTO(p);
		return ResponseEntity.ok(dto);
	}

	@Transactional
	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody ProprietariosDTO dto) {

		Proprietarios proprietario = proprietariosService.create(dto);
		Set<VeiculosDTO> veiculos = dto.getVeiculos();

		veiculoService.create(veiculos, proprietario);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proprietario.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @PathVariable Integer id, @RequestBody ProprietariosDTO dto) {
		dto.setId(id);
		Proprietarios proprietario = proprietariosService.update(dto);
		Set<VeiculosDTO> veiculos = dto.getVeiculos();

		veiculoService.create(veiculos, proprietario);

		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/desativar/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		proprietariosService.deactivate(id);
		veiculoService.deactivate(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/ativar/{id}")
	public ResponseEntity<Void> active(@PathVariable Integer id){
		proprietariosService.active(id);
		veiculoService.active(id);
		return ResponseEntity.noContent().build();
	}
	

}

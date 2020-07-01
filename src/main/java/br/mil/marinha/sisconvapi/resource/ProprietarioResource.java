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
import br.mil.marinha.sisconvapi.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping("/proprietarios")
public class ProprietarioResource {

	@Autowired
	ProprietarioService proprietariosService;
	@Autowired
	VeiculoService veiculoService;

	@GetMapping
	public ResponseEntity<List<ProprietariosDTO>> findAll() {
		List<Proprietarios> proprietariosList = proprietariosService.findAll();

		List<ProprietariosDTO> dtoList = createProprietariosDTO(proprietariosList);
		return ResponseEntity.ok(dtoList);
	}

	@GetMapping("/ativados")
	public ResponseEntity<List<ProprietariosDTO>> findAllActivated() {
		List<Proprietarios> proprietariosList = proprietariosService.findAllActivated();

		List<ProprietariosDTO> dtoList = createProprietariosDTO(proprietariosList);
		return ResponseEntity.ok(dtoList);
	}

	@GetMapping("/desativados")
	public ResponseEntity<List<ProprietariosDTO>> findAllDisabled() {
		List<Proprietarios> proprietariosList = proprietariosService.findAllDisabled();

		List<ProprietariosDTO> dtoList = createProprietariosDTO(proprietariosList);
		return ResponseEntity.ok(dtoList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProprietariosDTO> findById(@PathVariable Integer id) {

		Proprietarios p = proprietariosService.findById(id);

		ProprietariosDTO dto = new ProprietariosDTO(p);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/{id}/ativado")
	public ResponseEntity<ProprietariosDTO> findByIdActived(@PathVariable Integer id) {

		Proprietarios p = proprietariosService.findByIdActived(id);

		ProprietariosDTO dto = new ProprietariosDTO(p);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/{id}/desativado")
	public ResponseEntity<ProprietariosDTO> findByIdDisabled(@PathVariable Integer id) {

		Proprietarios p = proprietariosService.findByIdDisabled(id);

		ProprietariosDTO dto = new ProprietariosDTO(p);
		return ResponseEntity.ok(dto);
	}

	@Transactional
	@PostMapping
	public ResponseEntity<Void> save(@Valid @RequestBody ProprietariosDTO dto) {

		Proprietarios proprietario = proprietariosService.create(dto);
		Set<VeiculosDTO> veiculos = dto.getVeiculos();

		veiculoService.createOrUpdate(veiculos, proprietario);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proprietario.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@Transactional
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @PathVariable Integer id, @RequestBody ProprietariosDTO dto) {
		Proprietarios proprietario = proprietariosService.findById(id);
		if (proprietario.isAtivo()) {
			dto.setId(id);
			proprietario = proprietariosService.update(dto);
			Set<VeiculosDTO> veiculos = dto.getVeiculos();

			veiculoService.createOrUpdate(veiculos, proprietario);

			return ResponseEntity.noContent().build();
		}
		throw new ObjectNotFoundException("Usuario desativado!");
	}
	
	@Transactional
	@DeleteMapping("/desativar/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		proprietariosService.deactivate(id);
		veiculoService.deactivate(id);
		return ResponseEntity.noContent().build();
	}
	
	@Transactional
	@PutMapping("/ativar/{id}")
	public ResponseEntity<Void> active(@PathVariable Integer id, @RequestBody ProprietariosDTO dto) {
		Proprietarios proprietario = proprietariosService.findById(id);

		if (!proprietario.isAtivo()) {
			dto.setId(id);
			proprietario = proprietariosService.update(dto);
			Set<VeiculosDTO> veiculos = dto.getVeiculos();
			veiculoService.createOrUpdate(veiculos, proprietario);
			return ResponseEntity.noContent().build();
		}
		throw new ObjectNotFoundException("Usuario ja esta ativado!");

	}

	private List<ProprietariosDTO> createProprietariosDTO(List<Proprietarios> proprietariosList) {
		return proprietariosList.stream().map(p -> new ProprietariosDTO(p)).collect(Collectors.toList());
	}

}

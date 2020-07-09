package br.mil.marinha.sisconvapi.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.mil.marinha.sisconvapi.domain.Veiculos;
import br.mil.marinha.sisconvapi.dto.VeiculosDTO;
import br.mil.marinha.sisconvapi.service.VeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoResource {

	@Autowired
	VeiculoService veiculoService;
	

	@PreAuthorize("hasAnyRole('Administrador')")
	@GetMapping
	public ResponseEntity<List<VeiculosDTO>> findAll(){
		List<Veiculos> veiculosList = veiculoService.findAll();
		
		List<VeiculosDTO> dtoList = createVeiculosDTO(veiculosList);
		
		return ResponseEntity.ok(dtoList);
	}
	
	
	@GetMapping("/ativados")
	public ResponseEntity<List<VeiculosDTO>> findAllActivated(){
		List<Veiculos> veiculosList = veiculoService.findAllActivated();
		
		List<VeiculosDTO> dtoList = createVeiculosDTO(veiculosList);
		
		return ResponseEntity.ok(dtoList);
	}
	
	@PreAuthorize("hasAnyRole('Administrador')")
	@GetMapping("/desativados")
	public ResponseEntity<List<VeiculosDTO>> findAllDisabled(){
		List<Veiculos> veiculosList = veiculoService.findAllDisabled();
		
		List<VeiculosDTO> dtoList = createVeiculosDTO(veiculosList);
		
		return ResponseEntity.ok(dtoList);
	}
	
	
	@PreAuthorize("hasAnyRole('Administrador')")
	@GetMapping("/{id}")
	public ResponseEntity<VeiculosDTO> findById(@PathVariable Integer id) {

		Veiculos v = veiculoService.findById(id);

		VeiculosDTO dto = new VeiculosDTO(v);
		return ResponseEntity.ok(dto);
	}
	
	
	@GetMapping("/{id}/ativado")
	public ResponseEntity<VeiculosDTO> findByIdActived(@PathVariable Integer id) {

		Veiculos v = veiculoService.findByIdActived(id);

		VeiculosDTO dto = new VeiculosDTO(v);
		return ResponseEntity.ok(dto);
	}
	
	@PreAuthorize("hasAnyRole('Administrador')")
	@GetMapping("/{id}/desativado")
	public ResponseEntity<VeiculosDTO> findByIdDisabled(@PathVariable Integer id) {

		Veiculos v = veiculoService.findByIdDisabled(id);

		VeiculosDTO dto = new VeiculosDTO(v);
		return ResponseEntity.ok(dto);
	}
	
	@PreAuthorize("hasAnyRole('Administrador') || hasAnyRole('Supervisor')")
	@DeleteMapping("/desativar/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		veiculoService.deactivateById(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('Administrador') || hasAnyRole('Supervisor')")
	private List<VeiculosDTO> createVeiculosDTO(List<Veiculos> veiculosList){
		return veiculosList.stream().map(v -> new VeiculosDTO(v)).collect(Collectors.toList());
	}
	
	
}

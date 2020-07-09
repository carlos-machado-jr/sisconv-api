package br.mil.marinha.sisconvapi.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.mil.marinha.sisconvapi.domain.Usuarios;
import br.mil.marinha.sisconvapi.dto.UsuarioNewDTO;
import br.mil.marinha.sisconvapi.dto.UsuariosDTO;
import br.mil.marinha.sisconvapi.service.UsuarioService;
import br.mil.marinha.sisconvapi.service.exceptions.ObjectNotFoundException;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

	@Autowired
	UsuarioService usuarioService;
	
	@PreAuthorize("hasAnyRole('Administrador')")
	@GetMapping
	public ResponseEntity<List<UsuariosDTO>> findAll(){
		List<Usuarios> usuariosList = usuarioService.findAll();
		List<UsuariosDTO> dtoList = createUsuariosDTO(usuariosList);
		return ResponseEntity.ok(dtoList);
	}
	
	@PreAuthorize("hasAnyRole('Administrador') || hasAnyRole('Supervisor')")
	@GetMapping("/comuns")
	public ResponseEntity<List<UsuariosDTO>> findAllUsuarioComum(){
		List<Usuarios> usuariosList = usuarioService.findAllUsuarioComum();
		List<UsuariosDTO> dtoList = usuariosList.stream().map(u -> new UsuariosDTO(u)).collect(Collectors.toList());
		return ResponseEntity.ok(dtoList);
	}
	
	@PreAuthorize("hasAnyRole('Administrador')")
	@GetMapping("/ativados")
	public ResponseEntity<List<UsuariosDTO>> findAllActivated() {
		List<Usuarios> usuariosList = usuarioService.findAllActivated();

		List<UsuariosDTO> dtoList = createUsuariosDTO(usuariosList);
		return ResponseEntity.ok(dtoList);
	}
	
	@PreAuthorize("hasAnyRole('Administrador')")
	@GetMapping("/desativados")
	public ResponseEntity<List<UsuariosDTO>> findAllDisabled() {
		List<Usuarios> usuariosList = usuarioService.findAllDisabled();

		List<UsuariosDTO> dtoList = createUsuariosDTO(usuariosList);
		return ResponseEntity.ok(dtoList);
	}
	
	@PreAuthorize("hasAnyRole('Administrador')")
	@GetMapping("/{id}")
	public ResponseEntity<UsuariosDTO> findById(@PathVariable Integer id) {

		Usuarios usuario = usuarioService.findById(id);

		UsuariosDTO dto = new UsuariosDTO(usuario);
		return ResponseEntity.ok(dto);
	}
	
	@GetMapping("/{id}/ativado")
	public ResponseEntity<UsuariosDTO> findByIdActived(@PathVariable Integer id) {

		Usuarios usuario = usuarioService.findByIdActived(id);

		UsuariosDTO dto = new UsuariosDTO(usuario);
		return ResponseEntity.ok(dto);
	}
	
	@PreAuthorize("hasAnyRole('Administrador')")
	@GetMapping("/{id}/desativado")
	public ResponseEntity<UsuariosDTO> findByIdDisabled(@PathVariable Integer id) {
		
		Usuarios usuario = usuarioService.findByIdDisabled(id);
		
		UsuariosDTO dto = new UsuariosDTO(usuario);
		return ResponseEntity.ok(dto);
	}

	
	
	
	@PreAuthorize("hasAnyRole('Administrador') || hasAnyRole('Supervisor')")
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody UsuarioNewDTO newDTO){
		
		Usuarios u = usuarioService.create(newDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(u.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('Administrador') || hasAnyRole('Supervisor')")
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody UsuarioNewDTO newDTO, @PathVariable Integer id){
		
		newDTO.setId(id);
		usuarioService.update(newDTO);
		
		return ResponseEntity.noContent().build();
	}
	
	
	@PreAuthorize("hasAnyRole('Administrador')")
	@DeleteMapping("/desativar/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		usuarioService.deactivate(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('Administrador')")
	@PutMapping("/ativar/{id}")
	public ResponseEntity<Void> active(@PathVariable Integer id, @RequestBody UsuarioNewDTO dto) {
		Usuarios usuario = usuarioService.findById(id);

		if (!usuario.isAtivo()) {
			dto.setId(id);
			usuarioService.update(dto);
			return ResponseEntity.noContent().build();
		}
		throw new ObjectNotFoundException("Usuario ja esta ativado!");

	}
	
	
	private List<UsuariosDTO> createUsuariosDTO(List<Usuarios> usuariosList) {
		return usuariosList.stream().map(u -> new UsuariosDTO(u)).collect(Collectors.toList());
	}
}

package br.mil.marinha.sisconvapi.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.mil.marinha.sisconvapi.domain.Usuarios;
import br.mil.marinha.sisconvapi.dto.UsuarioNewDTO;
import br.mil.marinha.sisconvapi.dto.UsuariosDTO;
import br.mil.marinha.sisconvapi.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

	@Autowired
	UsuarioService service;
	
	@GetMapping
	public ResponseEntity<List<UsuariosDTO>> findAll(){
		List<Usuarios> usuariosList = service.findAll();
		List<UsuariosDTO> dtoList = usuariosList.stream().map(u -> new UsuariosDTO(u)).collect(Collectors.toList());
		return ResponseEntity.ok(dtoList);
	}
	
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody UsuarioNewDTO newDTO){
		
		Usuarios u = service.create(newDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(u.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}

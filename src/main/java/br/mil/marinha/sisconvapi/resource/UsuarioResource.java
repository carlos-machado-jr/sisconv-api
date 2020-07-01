package br.mil.marinha.sisconvapi.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.mil.marinha.sisconvapi.domain.Usuarios;
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
}

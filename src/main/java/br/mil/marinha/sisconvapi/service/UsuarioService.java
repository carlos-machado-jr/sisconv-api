package br.mil.marinha.sisconvapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Permissoes;
import br.mil.marinha.sisconvapi.domain.Usuarios;
import br.mil.marinha.sisconvapi.dto.UsuarioNewDTO;
import br.mil.marinha.sisconvapi.repositories.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository repo;
	
	@Autowired
	PermissaoService permissaoService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public List<Usuarios> findAll(){
		return repo.findAll();
	}
	
	public Usuarios create(UsuarioNewDTO newDTO) {
		
		Usuarios usuario = createUsuario(newDTO);
		return repo.save(usuario);
		
	}
	
	private Usuarios createUsuario(UsuarioNewDTO newDTO) {
		
		Usuarios usuario = new Usuarios(null, newDTO.getNome_usuario(), newDTO.getEmail(), newDTO.getNip_responsavel(), encoder.encode(newDTO.getSenha()), true);
		Permissoes permissao = permissaoService.findByDescricao(newDTO.getPermissao());
		usuario.setPermissoes(permissao);
		return usuario;
	}

}

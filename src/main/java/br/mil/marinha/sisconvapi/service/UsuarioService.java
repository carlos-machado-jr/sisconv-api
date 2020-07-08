package br.mil.marinha.sisconvapi.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Permissoes;
import br.mil.marinha.sisconvapi.domain.Usuarios;
import br.mil.marinha.sisconvapi.dto.UsuarioNewDTO;
import br.mil.marinha.sisconvapi.repositories.UsuarioRepository;
import br.mil.marinha.sisconvapi.security.UserSS;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	UsuarioRepository repo;

	@Autowired
	UsuarioRepository UsuarioRepository;

	@Autowired
	PermissaoService permissaoService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	public List<Usuarios> findAll() {
		return repo.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String nome_usuario) throws UsernameNotFoundException {
		Usuarios p = UsuarioRepository.findByNomeUsuario(nome_usuario);
		if (p == null) {
			throw new UsernameNotFoundException(nome_usuario);
		}
		List<GrantedAuthority> listGrantAuthority = new ArrayList<GrantedAuthority>();
		checkGrantAuthorities(p, listGrantAuthority);
		return new UserSS(p.getId(), p.getNome_usuario(), p.getSenha(), listGrantAuthority);
	}

	public Usuarios create(UsuarioNewDTO newDTO) {

		Usuarios usuario = createUsuario(newDTO);
		return repo.save(usuario);

	}

	private Usuarios createUsuario(UsuarioNewDTO newDTO) {

		Usuarios usuario = new Usuarios(null, newDTO.getNome_usuario(), newDTO.getEmail(), newDTO.getNip_responsavel(),
				encoder.encode(newDTO.getSenha()), true);
		Permissoes permissao = permissaoService.findByDescricao(newDTO.getPermissao());
		usuario.setPermissoes(permissao);
		return usuario;
	}

	private void checkGrantAuthorities(Usuarios user, List<GrantedAuthority> listGrantAuthority) {
		if (user != null && user.getPermissoes() != null) {
			
				final String PREFIX = "ROLE_";
				String role = PREFIX + user.getPermissoes().getDesc_permissoes();
				listGrantAuthority.add( new SimpleGrantedAuthority(role));
		}
	}

}

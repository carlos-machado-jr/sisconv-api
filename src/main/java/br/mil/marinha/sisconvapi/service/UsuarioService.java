package br.mil.marinha.sisconvapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.mil.marinha.sisconvapi.domain.Usuarios;
import br.mil.marinha.sisconvapi.dto.UsuarioNewDTO;
import br.mil.marinha.sisconvapi.repositories.UsuarioRepository;
import br.mil.marinha.sisconvapi.security.UserSS;
import br.mil.marinha.sisconvapi.service.exceptions.AuthorizationException;
import br.mil.marinha.sisconvapi.service.exceptions.ObjectNotFoundException;

@Service
public class UsuarioService implements UserDetailsService {

	@Autowired
	UsuarioRepository usuarioRepository;


	@Autowired
	PermissaoService permissaoService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	 

	// administrador
	public List<Usuarios> findAll() {
		return usuarioRepository.findAll();
	}

	// administrador
	public List<Usuarios> findAllActivated() {
		return usuarioRepository.findAllActivated();
	}

	// administrador
	public List<Usuarios> findAllDisabled() {
		return usuarioRepository.findAllDisabled();

	}

	// administrador e supervisor
	public List<Usuarios> findAllUsuarioComum() {
		return usuarioRepository.findAllUsuarioComum("Usuario");
	}
	
	public Usuarios findByNomeUsuario(String nome_usuario) {
		Usuarios usuario = usuarioRepository.findByNomeUsuario(nome_usuario);
		if (usuario == null) {
			throw new ObjectNotFoundException("Usuario não encontrado!");
		}
		return usuario;
	}
	
	
	// administrador
	public Usuarios findById(Integer id) {
		Optional<Usuarios> usuario = usuarioRepository.findById(id);
		return usuario.orElseThrow(() -> objectNotFoundException(id, "Usuario não encontrado!"));
	}

	// todos
	public Usuarios findByIdActived(Integer id) {
		UserSS userAuthenticated = UserService.authenticated();
		// usuario só pode acessar dados dele mesmo.
		if (userAuthenticated == null
				|| userAuthenticated.hasRole("Usuario") && !id.equals(userAuthenticated.getId())) {
			throw new AuthorizationException("Acesso negado");
		}
		Optional<Usuarios> c = usuarioRepository.findByIdAndAtivo(id, true);
		String permissao = c.get().getPermissoes().getDesc_permissoes();

		// Somente administrador pode acessar dados de administrador
		if (permissao.equals("Administrador") && !userAuthenticated.hasRole("Administrador")) {
			throw new AuthorizationException("Acesso negado");
		}
		return c.orElseThrow(() -> objectNotFoundException(id, "Usuario desativado!"));

	}

	// administrador
	public Usuarios findByIdDisabled(Integer id) {
		Optional<Usuarios> c = usuarioRepository.findByIdAndAtivo(id, false);
		
		return c.orElseThrow(() -> objectNotFoundException(id, "Usuario ativado!"));

	}

	// login
	@Override
	public UserDetails loadUserByUsername(String nome_usuario) throws UsernameNotFoundException {
		Usuarios p = usuarioRepository.findByNomeUsuario(nome_usuario);
		if (p == null) {
			throw new UsernameNotFoundException(nome_usuario);
		}
		List<GrantedAuthority> listGrantAuthority = new ArrayList<GrantedAuthority>();
		checkGrantAuthorities(p, listGrantAuthority);
		return new UserSS(p.getId(), p.getNome_usuario(), p.getSenha(), listGrantAuthority);
	}

	// administrador e supervisor
	public Usuarios create(UsuarioNewDTO newDTO) {
		
		newDTO.setId(null);
		Usuarios usuario = transformDTO(newDTO);
		return usuarioRepository.save(usuario);

	}
	
	// administrador e supervisor
	public void update(UsuarioNewDTO newDTO) {
		Usuarios usuario = transformDTO(newDTO);

		usuarioRepository.save(usuario);
	}
	
	//administrador
	public void deactivate(Integer id) {
		Usuarios usuario = findById(id);
		usuario.setAtivo(false);
		usuarioRepository.save(usuario);
	}
	
	// metodos privados
	
	
	private ObjectNotFoundException objectNotFoundException(Integer id, String message) {
		return new ObjectNotFoundException(
				message + " id: " + id + ", Tipo: " + Usuarios.class.getName());
	}

	private Usuarios transformDTO(UsuarioNewDTO newDTO) {
		UserSS userAuthenticated = UserService.authenticated();
		// se o usuario autenticado nao for administrador, ele nao poderá cadastrar usuarios administradores!
		if (userAuthenticated.hasRole("Usuario") && newDTO.getPermissao().equals("Administrador")) {
			 throw new AuthorizationException("Acesso negado!");
		}
		
		Usuarios usuario = new Usuarios(newDTO.getId(), newDTO.getNome_usuario(), newDTO.getEmail(),
				newDTO.getNip_responsavel(), encoder.encode(newDTO.getSenha()), true);

		usuario.setPermissoes(permissaoService.findByDescricao(newDTO.getPermissao()));
		return usuario;
	}

	private void checkGrantAuthorities(Usuarios user, List<GrantedAuthority> listGrantAuthority) {
		if (user != null && user.getPermissoes() != null) {

			final String PREFIX = "ROLE_";
			String role = PREFIX + user.getPermissoes().getDesc_permissoes();
			listGrantAuthority.add(new SimpleGrantedAuthority(role));
		}
	}

}

package br.mil.marinha.sisconvapi.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.mil.marinha.sisconvapi.domain.Usuarios;

public class UsuariosDTO {

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatorio")
	@Length(max = 10,  message = "Insira um login com ate 10 caracteres!")
	private String nome_usuario;
	
	
	@Email
	private String email;
	
	
	@Length(min = 8, max = 8, message = "Insira um NIP valido!")
	private String nip_responsavel;
	
	private String permissao;
	
	private String ativo;
	
	public UsuariosDTO() {
		// TODO Auto-generated constructor stub
	}

	public UsuariosDTO(Usuarios u){
		super();
		this.id = u.getId();
		this.nome_usuario = u.getNome_usuario();
		this.email = u.getEmail();
		this.nip_responsavel = u.getNip();
		this.permissao = u.getPermissoes().getDesc_permissoes();
		this.ativo = u.isAtivo() ? "ativado": "desativado";
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	

	public String getNome_usuario() {
		return nome_usuario;
	}

	public void setNome_usuario(String nome_usuario) {
		this.nome_usuario = nome_usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNip_responsavel() {
		return nip_responsavel;
	}

	public void setNip_responsavel(String nip) {
		this.nip_responsavel = nip;
	}

	public String getPermissao() {
		return permissao;
	}

	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}
	
	
	
	
}

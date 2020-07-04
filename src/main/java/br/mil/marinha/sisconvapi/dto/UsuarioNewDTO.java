package br.mil.marinha.sisconvapi.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class UsuarioNewDTO {

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatorio")
	@Length(max = 10, message = "Insira um login com ate 10 caracteres!")
	private String nome_usuario;

	@Email
	private String email;

	@NotEmpty(message = "Preenchimento obrigatorio")
	private String senha;
	
	@Length(min = 8, max = 8, message = "Insira um NIP valido!")
	private String nip_responsavel;

	private String permissao;

	public UsuarioNewDTO() {
		// TODO Auto-generated constructor stub
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

	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

}

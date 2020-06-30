package br.mil.marinha.sisconvapi.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.mil.marinha.sisconvapi.domain.Usuarios;

public class UsuariosDTO {

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatorio")
	@Length(min = 5,  message = "Insira um nome completo!")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Email
	private String email;
	
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Length(min = 8, max = 8, message = "Insira um NIP valido!")
	private String nip;
	
	
	public UsuariosDTO() {
		// TODO Auto-generated constructor stub
	}

	public UsuariosDTO(Usuarios u){
		super();
		this.id = u.getId();
		this.nome = u.getNome();
		this.email = u.getEmail();
		this.nip = u.getNip();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}
	
	
}

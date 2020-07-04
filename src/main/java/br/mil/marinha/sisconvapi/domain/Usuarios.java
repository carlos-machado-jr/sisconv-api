package br.mil.marinha.sisconvapi.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuarios implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome_usuario;
	
	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	private String nip;
	
	@JsonIgnore
	private String senha;
	
	private boolean ativo;
	
	@ManyToOne
	@JoinColumn(name = "id_permissao")
	private Permissoes permissoes;
	
	
	public Usuarios() {
		// TODO Auto-generated constructor stub
	}


	public Usuarios(Integer id, String nome, String email, String nip, String senha, boolean ativo) {
		super();
		this.id = id;
		this.nome_usuario = nome;
		this.email = email;
		this.nip = nip;
		this.senha = senha;
		this.ativo = ativo;
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


	public String getNip() {
		return nip;
	}


	public void setNip(String nip) {
		this.nip = nip;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public boolean isAtivo() {
		return ativo;
	}


	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	

	public Permissoes getPermissoes() {
		return permissoes;
	}


	public void setPermissoes(Permissoes permissoes) {
		this.permissoes = permissoes;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuarios other = (Usuarios) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}

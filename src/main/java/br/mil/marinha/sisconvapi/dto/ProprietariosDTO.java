package br.mil.marinha.sisconvapi.dto;

import br.mil.marinha.sisconvapi.domain.Proprietarios;

public class ProprietariosDTO {

	private Integer id;
	private String nome;
	private String email;
	private String nip;
	private String cng;
	
	public ProprietariosDTO() {
		// TODO Auto-generated constructor stub
	}

	public ProprietariosDTO(Proprietarios p) {
		super();
		this.id = p.getId();
		this.nome = p.getNome();
		this.email = p.getEmail();
		this.nip = p.getNip();
		this.cng = p.getCnh();
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

	public String getCng() {
		return cng;
	}

	public void setCng(String cng) {
		this.cng = cng;
	}
	
	
	
}

package br.mil.marinha.sisconvapi.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import br.mil.marinha.sisconvapi.domain.Cartao;
import br.mil.marinha.sisconvapi.domain.Proprietarios;

public class CartaoDTO {

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatorio")
	private String numero;


	private Date validade;

	private boolean status_cartao;
	
	private String nome_proprietario;
	
	private String nip_proprietario;

	public CartaoDTO() {
		// TODO Auto-generated constructor stub
	}

	public CartaoDTO(Cartao c) {
		super();
		this.id = c.getId();
		this.numero = c.getNumero();
		this.validade = c.getValidade();
		this.status_cartao = c.getStatusCartao();
		
	}
	
	public CartaoDTO(Cartao c, Proprietarios p) {
		super();
		this.id = c.getId();
		this.numero = c.getNumero();
		this.validade = c.getValidade();
		this.status_cartao = c.getStatusCartao();
		this.nip_proprietario = p.getNip();
		this.nome_proprietario = p.getNome();
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getValidade() {
		return validade;
	}

	public void setValidade(Date validade) {
		this.validade = validade;
	}

	public Boolean getStatus_cartao() {
		return status_cartao;
	}

	public void setStatus_cartao(Boolean status_cartao) {
		this.status_cartao = status_cartao;
	}

	public String getNome_proprietario() {
		return nome_proprietario;
	}

	public void setNome_proprietario(String nome_proprietario) {
		this.nome_proprietario = nome_proprietario;
	}

	public String getNip_proprietario() {
		return nip_proprietario;
	}

	public void setNip_proprietario(String nip_proprietario) {
		this.nip_proprietario = nip_proprietario;
	}
	
	
	
	
}

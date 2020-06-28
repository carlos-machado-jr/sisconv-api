package br.mil.marinha.sisconvapi.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.mil.marinha.sisconvapi.domain.Cartao;
import br.mil.marinha.sisconvapi.domain.Proprietarios;

public class CartaoDTO {

	private Integer id;

	@NotEmpty(message = "Preenchimento obrigatorio")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String numero;

	@NotEmpty(message = "Preenchimento obrigatorio")
	private Date validade;

	private String status_cartao;
	
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
		this.status_cartao = c.getStatusCartao().getDesc_status_cartao();
		
	}
	
	public CartaoDTO(Cartao c, Proprietarios p) {
		super();
		this.id = c.getId();
		this.numero = c.getNumero();
		this.validade = c.getValidade();
		this.status_cartao = c.getStatusCartao().getDesc_status_cartao();
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

	public String getStatus_cartao() {
		return status_cartao;
	}

	public void setStatus_cartao(String status_cartao) {
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

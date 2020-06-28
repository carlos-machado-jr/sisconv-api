package br.mil.marinha.sisconvapi.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.mil.marinha.sisconvapi.domain.Veiculos;

public class VeiculosDTO {
	
	
	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String modelo;
	
	
	@NotEmpty(message = "Preenchimento obrigatorio")
	private String ano;
	
	
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	@NotEmpty(message = "Preenchimento obrigatorio")
	private String placa;
	
	
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	@NotEmpty(message = "Preenchimento obrigatorio")
	private String chassi;
	
	private String montadora;
	
	private String cor;
	
	private String nome_proprietario;
	
	private String email_proprietario;
	
	private String cnh_proprietario;
	
	private String nip_proprietario;
	
	private String posto_proprietario;
	
	private String setor_proprietario;
	
	private String cartao_proprietario;
	
	private Date validade_cartao;
	
	public VeiculosDTO() {
		// TODO Auto-generated constructor stub
	}

	
	
	public VeiculosDTO(Veiculos v) {
		super();
		this.id = v.getId();
		this.modelo = v.getModelo();
		this.ano = v.getAno();
		this.placa = v.getPlaca();
		this.chassi = v.getChassi();
		this.montadora = v.getMontadora().getDesc_montadora();
		this.cor = v.getCor().getDesc_cor();
		this.nome_proprietario = v.getProprietario().getNome();
		this.email_proprietario = v.getProprietario().getEmail();
		this.cnh_proprietario = v.getProprietario().getCnh();
		this.nip_proprietario = v.getProprietario().getNip();
		this.posto_proprietario = v.getProprietario().getPosto().getDesc_posto();
		this.setor_proprietario = v.getProprietario().getSetor().getDesc_setor();
		this.cartao_proprietario = v.getProprietario().getCartao().getNumero();
		this.validade_cartao = v.getProprietario().getCartao().getValidade();
		
	}



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public String getAno() {
		return ano;
	}


	public void setAno(String ano) {
		this.ano = ano;
	}


	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}


	public String getChassi() {
		return chassi;
	}


	public void setChassi(String chassi) {
		this.chassi = chassi;
	}



	public String getMontadora() {
		return montadora;
	}



	public void setMontadora(String montadora) {
		this.montadora = montadora;
	}



	public String getCor() {
		return cor;
	}



	public void setCor(String cor) {
		this.cor = cor;
	}



	public String getNome_proprietario() {
		return nome_proprietario;
	}



	public void setNome_proprietario(String nome_proprietario) {
		this.nome_proprietario = nome_proprietario;
	}



	public String getEmail_proprietario() {
		return email_proprietario;
	}



	public void setEmail_proprietario(String email_proprietario) {
		this.email_proprietario = email_proprietario;
	}



	public String getCnh_proprietario() {
		return cnh_proprietario;
	}



	public void setCnh_proprietario(String cnh_proprietario) {
		this.cnh_proprietario = cnh_proprietario;
	}



	public String getNip_proprietario() {
		return nip_proprietario;
	}



	public void setNip_proprietario(String nip_proprietario) {
		this.nip_proprietario = nip_proprietario;
	}



	public String getPosto_proprietario() {
		return posto_proprietario;
	}



	public void setPosto_proprietario(String posto_proprietario) {
		this.posto_proprietario = posto_proprietario;
	}



	public String getSetor_proprietario() {
		return setor_proprietario;
	}



	public void setSetor_proprietario(String setor_proprietario) {
		this.setor_proprietario = setor_proprietario;
	}



	public String getCartao_proprietario() {
		return cartao_proprietario;
	}



	public void setCartao_proprietario(String cartao_proprietario) {
		this.cartao_proprietario = cartao_proprietario;
	}



	public Date getValidade_cartao() {
		return validade_cartao;
	}



	public void setValidade_cartao(Date validade_cartao) {
		this.validade_cartao = validade_cartao;
	}
	
	
}

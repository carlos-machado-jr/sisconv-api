package br.mil.marinha.sisconvapi.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.mil.marinha.sisconvapi.domain.Veiculos;

public class VeiculosDTO {
	
	
	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatorio")
	private String modelo;
	
	
	@NotEmpty(message = "Preenchimento obrigatorio")
	private String ano;
	
	
	@Length(min = 8, max = 8, message = "Insira uma placa valida!")
	@NotEmpty(message = "Preenchimento obrigatorio")
	private String placa;
	
	
	@Length(min = 17, max = 17, message = "Insira uma chassi valido!")
	@NotEmpty(message = "Preenchimento obrigatorio")
	private String chassi;
	
	@NotEmpty(message = "Preenchimento obrigatorio")
	private String montadora;
	
	@NotEmpty(message = "Preenchimento obrigatorio")
	private String cor;
	
	
	
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




	
}

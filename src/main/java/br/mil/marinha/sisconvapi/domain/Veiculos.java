package br.mil.marinha.sisconvapi.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Veiculos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String modelo;
	
	private String ano;
	
	@Column(unique = true)
	private String placa;
	
	@Column(unique = true)
	private String chassi;
	
	@ManyToOne
	@JoinColumn(name = "id_proprietario")
	private Proprietarios proprietario;
	
	@ManyToOne
	@JoinColumn(name = "id_montadora")
	private Montadora montadora;
	
	@ManyToOne
	@JoinColumn(name = "id_cor")
	private Cor cor;
	
	public Veiculos() {
		// TODO Auto-generated constructor stub
	}

	public Veiculos(Integer id, String modelo, String ano, String placa, String chassi) {
		super();
		this.id = id;
		this.modelo = modelo;
		this.ano = ano;
		this.placa = placa;
		this.chassi = chassi;
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
	
	
	public Proprietarios getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietarios proprietario) {
		this.proprietario = proprietario;
	}

	public Montadora getMontadora() {
		return montadora;
	}

	public void setMontadora(Montadora montadora) {
		this.montadora = montadora;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
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
		Veiculos other = (Veiculos) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}

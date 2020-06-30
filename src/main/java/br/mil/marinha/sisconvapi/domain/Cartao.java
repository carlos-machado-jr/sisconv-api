package br.mil.marinha.sisconvapi.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Cartao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private String numero;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date validade;
	
	@ManyToOne
	@JoinColumn(name = "id_status_cartao")
	private StatusCartao statusCartao;
	
	@OneToOne( mappedBy = "cartao")
	private Proprietarios proprietario;
	
	public Cartao() {
		// TODO Auto-generated constructor stub
	}


	public Cartao(Integer id, String numero, Date validade) {
		super();
		this.id = id;
		this.numero = numero;
		this.validade = validade;
		
	}

	public Cartao(Integer id,  Date validade) {
		super();
		this.id = id;
		this.validade = validade;
		
	}
	


	public StatusCartao getStatusCartao() {
		return statusCartao;
	}


	public void setStatusCartao(StatusCartao statusCartao) {
		this.statusCartao = statusCartao;
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
	
	

	public Proprietarios getProprietario() {
		return proprietario;
	}


	public void setProprietario(Proprietarios proprietario) {
		this.proprietario = proprietario;
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
		Cartao other = (Cartao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}

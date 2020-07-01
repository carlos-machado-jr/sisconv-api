package br.mil.marinha.sisconvapi.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Proprietarios implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	@Column(unique = true)
	private String email;
	
	@Column(unique = true)
	private String nip;
	
	@Column(unique = true)
	private String cnh;
	
	private boolean ativo;
	
	@ManyToOne
	@JoinColumn(name = "id_posto")
	private Posto posto;
	
	@ManyToOne
	@JoinColumn(name = "id_setor")
	private Setor setor;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "id_cartao", referencedColumnName = "id")
	private Cartao cartao;
	
	@JsonIgnore
	@OneToMany(mappedBy = "proprietario")
	private Set<Veiculos> veiculos;
	
	public Proprietarios() {
		// TODO Auto-generated constructor stub
	}

	public Proprietarios(Integer id, String nome, String email, String nip, String cnh, boolean ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.nip = nip;
		this.cnh = cnh;
		this.ativo = ativo;
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

	public String getCnh() {
		return cnh;
	}

	public void setCnh(String cnh) {
		this.cnh = cnh;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	
	public Posto getPosto() {
		return posto;
	}

	public void setPosto(Posto posto) {
		this.posto = posto;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public Set<Veiculos> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(Set<Veiculos> veiculos) {
		this.veiculos = veiculos;
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
		Proprietarios other = (Proprietarios) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
}

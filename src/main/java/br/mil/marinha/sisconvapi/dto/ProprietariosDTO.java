package br.mil.marinha.sisconvapi.dto;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.mil.marinha.sisconvapi.domain.Proprietarios;

public class ProprietariosDTO {

	private Integer id;
	
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	@Email
	private String email;
	
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String nip;
	
	@NotEmpty(message = "Preenchimento obrigatorio")
	@Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 e 120 caracteres")
	private String cnh;
	
	private String setor;
	
	private String posto;
	
	private String cartao;
	
	private Set<VeiculosDTO> veiculos;
	
	
	
	public ProprietariosDTO() {
		// TODO Auto-generated constructor stub
	}

	public ProprietariosDTO(Proprietarios p) {
		super();
		this.id = p.getId();
		this.nome = p.getNome();
		this.email = p.getEmail();
		this.nip = p.getNip();
		this.cnh = p.getCnh();
		this.setor = p.getSetor().getDesc_setor();
		this.posto = p.getPosto().getDesc_posto();
		this.cartao = p.getCartao().getNumero();
		this.veiculos = p.getVeiculos().stream().map(v -> new VeiculosDTO(v)).collect(Collectors.toSet());
		
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

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getPosto() {
		return posto;
	}

	public void setPosto(String posto) {
		this.posto = posto;
	}
	

	public String getCartao() {
		return cartao;
	}

	public void setCartao(String cartao) {
		this.cartao = cartao;
	}

	public Set<VeiculosDTO> getVeiculos() {
		return veiculos;
	}

	public void setVeiculos(Set<VeiculosDTO> veiculos) {
		this.veiculos = veiculos;
	}

	
	
	
	
}

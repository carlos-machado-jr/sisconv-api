package br.mil.marinha.sisconvapi.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "status_cartao")
public class StatusCartao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String desc_status_cartao;
	
	@OneToMany(mappedBy = "statusCartao")
	private List<Cartao> cartao;
	
	public StatusCartao() {
		// TODO Auto-generated constructor stub
	}

	public StatusCartao(Integer id, String desc_status_cartao) {
		super();
		this.id = id;
		this.desc_status_cartao = desc_status_cartao;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesc_status_cartao() {
		return desc_status_cartao;
	}

	public void setDesc_status_cartao(String desc_status_cartao) {
		this.desc_status_cartao = desc_status_cartao;
	}
	
	

	public List<Cartao> getCartao() {
		return cartao;
	}

	public void setCartao(List<Cartao> cartao) {
		this.cartao = cartao;
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
		StatusCartao other = (StatusCartao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}

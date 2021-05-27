package br.mil.marinha.sisconvapi.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Audited
@AuditTable("setor_Audit")
public class Setor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(unique = true)
	private String desc_setor;
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "setor")
	private List<Proprietarios> proprietarios;
	
	public Setor() {
		// TODO Auto-generated constructor stub
	}

	public Setor(Integer id, String desc_setor) {
		super();
		this.id = id;
		this.desc_setor = desc_setor;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDesc_setor() {
		return desc_setor;
	}

	public void setDesc_setor(String desc_setor) {
		this.desc_setor = desc_setor;
	}
	
	

	public List<Proprietarios> getProprietarios() {
		return proprietarios;
	}

	public void setProprietarios(List<Proprietarios> proprietarios) {
		this.proprietarios = proprietarios;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desc_setor == null) ? 0 : desc_setor.hashCode());
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
		Setor other = (Setor) obj;
		if (desc_setor == null) {
			if (other.desc_setor != null)
				return false;
		} else if (!desc_setor.equals(other.desc_setor))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}

package br.mil.marinha.sisconvapi.history.entity;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;


import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import br.mil.marinha.sisconvapi.history.listener.AuditListener;

@Entity(name="historico_Geral")
@RevisionEntity(AuditListener.class)
public class AuditEntity  {


	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "revisao_Sequence")
	@SequenceGenerator(name = "revisao_Sequence", sequenceName = "REVISAO_SEQ")
	@RevisionNumber
	private Long revision_Id;
	
	@RevisionTimestamp
	@DateTimeFormat
	private Date revision_Date;
	
	
	private String revision_Username;
	
	
	private String revision_UserIp;
	
	
	
	
	
	public AuditEntity(Long idRevision, Date dataRevision, String usuario, String ip) {
		super();
		this.revision_Id = idRevision;
		this.revision_Date = dataRevision;
		this.revision_Username = usuario;
		this.revision_UserIp = ip;
	}
	
	public AuditEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getRevision_Id() {
		return revision_Id;
	}

	public void setRevision_Id(Long revision_Id) {
		this.revision_Id = revision_Id;
	}

	public Date getRevision_Date() {
		return revision_Date;
	}

	public void setRevision_Date(Date revision_Date) {
		this.revision_Date = revision_Date;
	}

	public String getRevision_Username() {
		return revision_Username;
	}

	public void setRevision_Username(String revision_Username) {
		this.revision_Username = revision_Username;
	}

	public String getRevision_UserIp() {
		return revision_UserIp;
	}

	public void setRevision_UserIp(String revision_UserIp) {
		this.revision_UserIp = revision_UserIp;
	}
	
	
	
	
	
	
	
	

}

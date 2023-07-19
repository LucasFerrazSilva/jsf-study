package com.ferraz.erp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name="TB_EMPRESA")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(name="NOME_FANTASIA", nullable=false, length=80)
	private String nomeFantasia;
	
	@NotBlank
	@Column(name="RAZAO_SOCIAL", nullable=false, length=120)
	private String razaoSocial;

	@NotBlank
	@CNPJ
	@Column(nullable=false, length=18)
	private String cnpj;
	
	@NotNull
	@Past
	@Temporal(TemporalType.DATE)
	@Column(name="DATA_FUNDACAO")
	private Date dataFundacao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="RAMO_ATIVIDADE_ID", nullable=false)
	private RamoAtividade ramoAtividade;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name="TIPO_EMPRESA", nullable=false, length=30)
	private TipoEmpresa tipoEmpresa;
	
	
	public Empresa() {}
	
	public Empresa(String nomeFantasia, String razaoSocial, String cnpj, Date dataFundacao,
			RamoAtividade ramoAtividade, TipoEmpresa tipoEmpresa) {
		super();
		this.nomeFantasia = nomeFantasia;
		this.razaoSocial = razaoSocial;
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
		this.ramoAtividade = ramoAtividade;
		this.tipoEmpresa = tipoEmpresa;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(Date dataFundacao) {
		this.dataFundacao = dataFundacao;
	}

	public RamoAtividade getRamoAtividade() {
		return ramoAtividade;
	}

	public void setRamoAtividade(RamoAtividade ramoAtividade) {
		this.ramoAtividade = ramoAtividade;
	}

	public TipoEmpresa getTipoEmpresa() {
		return tipoEmpresa;
	}

	public void setTipoEmpresa(TipoEmpresa tipoEmpresa) {
		this.tipoEmpresa = tipoEmpresa;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Empresa other = (Empresa) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + "]";
	}
	
}

package com.ferraz.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ferraz.erp.converter.RamoAtividadeConverter;
import com.ferraz.erp.model.Empresa;
import com.ferraz.erp.model.RamoAtividade;
import com.ferraz.erp.model.TipoEmpresa;
import com.ferraz.erp.repository.EmpresaDAO;
import com.ferraz.erp.repository.RamoAtividadeDAO;
import com.ferraz.erp.service.CadastroEmpresaService;
import com.ferraz.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmpresaDAO empresaDao;
	
	@Inject
	private CadastroEmpresaService cadastroEmpresaService;
	
	@Inject
	private RamoAtividadeDAO ramoAtividadeDAO;
	
	private Converter ramoAtividadeConverter;
	
	@Inject
	private FacesMessages messages;
	
	private List<Empresa> empresas;
	
	private String termoPesquisa;
	
	private Empresa empresa;
	
	
	public void pesquisar() {
		if (termoPesquisa == null)
			termoPesquisa = "";
		
		empresas = empresaDao.search(termoPesquisa);
		
		if (empresas.isEmpty()) {
			messages.info("Sua consulta não retornou registros.");
		}
	}
	
	public void buscaEmpresas() {
		empresas = empresaDao.list();
	}
	
	public List<RamoAtividade> completarRamoAtividade(String termo) {
		List<RamoAtividade> list = ramoAtividadeDAO.search(termo);
		
		ramoAtividadeConverter = new RamoAtividadeConverter(list);
		
		return list;
	}

	public void prepararNovaEmpresa() {
		empresa = new Empresa();
	}
	
	public void salvar() {
		cadastroEmpresaService.save(empresa);
		pesquisar();
		messages.info("Empresa cadastrada com sucesso!");;
	}
	
	public Converter getRamoAtividadeConverter() {
		return ramoAtividadeConverter;
	}
	
	public List<Empresa> getEmpresas() {
		return empresas;
	}
	
	public String getTermoPesquisa() {
		return termoPesquisa;
	}
	
	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
	
	public TipoEmpresa[] getTiposEmpresa() {
		return TipoEmpresa.values();
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
}

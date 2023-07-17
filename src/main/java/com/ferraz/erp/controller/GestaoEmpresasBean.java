package com.ferraz.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ferraz.erp.model.Empresa;
import com.ferraz.erp.repository.EmpresaDAO;
import com.ferraz.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmpresaDAO empresaDao;
	
	@Inject
	private FacesMessages facesMessages;
	
	private List<Empresa> empresas;
	
	private String termoPesquisa;
	
	
	public void pesquisar() {
		empresas = empresaDao.search(termoPesquisa);
		
		if (empresas.isEmpty()) {
			facesMessages.info("Sua consulta não retornou registros.");
		}
	}
	
	public void buscaEmpresas() {
		empresas = empresaDao.list();
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

}

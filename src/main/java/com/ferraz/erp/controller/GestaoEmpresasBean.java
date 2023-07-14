package com.ferraz.erp.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ferraz.erp.model.Empresa;
import com.ferraz.erp.repository.EmpresaDAO;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmpresaDAO empresaDao;
	
	private List<Empresa> empresas;
	
	
	public void buscaEmpresas() {
		empresas = empresaDao.list();
	}
	
	public List<Empresa> getEmpresas() {
		return empresas;
	}

}

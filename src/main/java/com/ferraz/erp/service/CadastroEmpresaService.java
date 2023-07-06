package com.ferraz.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.ferraz.erp.model.Empresa;
import com.ferraz.erp.repository.EmpresaDAO;
import com.ferraz.erp.util.Transactional;

public class CadastroEmpresaService implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private EmpresaDAO dao;
	
	@Transactional
	public void save(Empresa empresa) {
		dao.save(empresa);
	}
	
	@Transactional
	public void delete(Long id) {
		dao.delete(id);
	}

}

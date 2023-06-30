package com.ferraz.erp.repository;

import java.util.List;

import javax.persistence.EntityManager;

import com.ferraz.erp.model.Empresa;

public class EmpresaDAO {
	
	private EntityManager em;
	
	
	public EmpresaDAO() {}
	
	public EmpresaDAO(EntityManager em) {
		this.em = em;
	}
	
	public Empresa findById(Long id) {
		return em.find(Empresa.class, id);
	}
	
	public List<Empresa> list() {
		return em.createQuery("from Empresa", Empresa.class).getResultList();
	}
	
	public List<Empresa> findByNomeFantasia(String nomeFantasia) {
		String jpql = "from Empresa where nomeFantasia like :nomeFantasia";
		
		return em.createQuery(jpql, Empresa.class)
					.setParameter("nomeFantasia", "%" + nomeFantasia + "%")
					.getResultList();
	}
	
	public Empresa save(Empresa empresa) {
		em.merge(empresa);
		return empresa;
	}
	
	public void delete(Long id) {
		Empresa empresa = this.findById(id);
		em.remove(empresa);
	}

}

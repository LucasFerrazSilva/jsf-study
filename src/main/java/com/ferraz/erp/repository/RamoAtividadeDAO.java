package com.ferraz.erp.repository;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.ferraz.erp.model.RamoAtividade;

public class RamoAtividadeDAO {
	
	@Inject
	private EntityManager em;
	

	public RamoAtividadeDAO() {}

	public RamoAtividadeDAO(EntityManager em) {
		this.em = em;
	}
	
	
	public List<RamoAtividade> search(String descricao) {
		CriteriaBuilder criteria = em.getCriteriaBuilder();
		CriteriaQuery<RamoAtividade> query = criteria.createQuery(RamoAtividade.class);
		Root<RamoAtividade> root = query.from(RamoAtividade.class);
		query.select(root);
		query.where(criteria.like(root.get("descricao"), "%" + descricao + "%"));
		return em.createQuery(query).getResultList();
	}

}

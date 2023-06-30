package com.ferraz.erp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import com.ferraz.erp.model.RamoAtividade;

@TestInstance(Lifecycle.PER_CLASS)
public class RamoAtividadeDAOTest {
	
	private EntityManagerFactory factory;
	private EntityManager em;
	private RamoAtividadeDAO dao;
	
	@BeforeAll
	public void beforeAll() {
		this.factory = Persistence.createEntityManagerFactory("JsfStudy");
		this.em = factory.createEntityManager();
		this.dao = new RamoAtividadeDAO(em);
	}
	
	@AfterAll
	public void afterAll() {
		em.close();
		factory.close();
	}
	
	@BeforeEach
	public void beforeEach() {
		em.getTransaction().begin();
		em.createQuery("delete from Empresa").executeUpdate();
		em.createQuery("delete from RamoAtividade").executeUpdate();
		em.getTransaction().commit();		
	}
	
	private RamoAtividade createRamoAtividade() {
		em.getTransaction().begin();
		RamoAtividade ramoAtividade = new RamoAtividade("Descrição do ramo de atividade");
		em.persist(ramoAtividade);
		em.getTransaction().commit();	
		return ramoAtividade;
	}
	
	@Test
	public void testValidSearch() {
		// Given
		RamoAtividade ramoAtividade = createRamoAtividade();
		createRamoAtividade();
		
		// When
		List<RamoAtividade> list = dao.search(ramoAtividade.getDescricao());
		
		// Then
		assertNotNull(list);
		assertEquals(2, list.size());
		assertTrue(list.contains(ramoAtividade));
	}
	
	@Test
	public void testValidSearchSubstring() {
		// Given
		RamoAtividade ramoAtividade = createRamoAtividade();
		createRamoAtividade();
		
		// When
		List<RamoAtividade> list = dao.search(ramoAtividade.getDescricao().substring(3, 10));
		
		// Then
		assertNotNull(list);
		assertEquals(2, list.size());
		assertTrue(list.contains(ramoAtividade));
	}
	
	@Test
	public void testInvalidSearch() {
		// Given
		createRamoAtividade();
		createRamoAtividade();
		
		// When
		List<RamoAtividade> list = dao.search("Descrição inválida");
		
		// Then
		assertNotNull(list);
		assertEquals(0, list.size());
	}
	
}

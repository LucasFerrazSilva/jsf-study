package com.ferraz.erp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.math.BigDecimal;
import java.util.Date;
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

import com.ferraz.erp.model.Empresa;
import com.ferraz.erp.model.RamoAtividade;
import com.ferraz.erp.model.TipoEmpresa;

@TestInstance(Lifecycle.PER_CLASS)
public class EmpresaDAOTest {
	
	private EntityManagerFactory factory;
	private EntityManager em;
	private EmpresaDAO dao;
	
	@BeforeAll
	public void beforeAll() {
		this.factory = Persistence.createEntityManagerFactory("JsfStudy");
		this.em = factory.createEntityManager();
		this.dao = new EmpresaDAO(em);
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
	
	private Empresa createEmpresa() {
		RamoAtividade ramoAtividade = createRamoAtividade();
		
		em.getTransaction().begin();
		Empresa empresa = new Empresa("Nome Fantasia", "Razao Social", "123456789", new Date(1995, 7, 17), ramoAtividade, TipoEmpresa.MEI);		
		em.persist(empresa);
		em.getTransaction().commit();	
		
		return empresa;
	}
	
	@Test
	public void testList() {
		// Given
		Empresa empresa = createEmpresa();
		
		// When
		List<Empresa> list = this.dao.list();
		
		// Then
		assertNotNull(list);
		assertEquals(1, list.size());
		assertEquals(empresa, list.get(0));
	}
	
	@Test
	public void testEmptyList() {
		// Given
		
		// When
		List<Empresa> list = this.dao.list();
		
		// Then
		assertNotNull(list);
		assertEquals(0, list.size());
	}
	
	@Test
	public void testFindByNomeFantasia() {
		// Given
		Empresa empresa = createEmpresa();
		
		// When
		List<Empresa> list = this.dao.search(empresa.getNomeFantasia());
		
		// Then
		assertNotNull(list);
		assertEquals(1, list.size());
		assertEquals(empresa, list.get(0));
	}
	
	@Test
	public void testFindByNomeFantasiaSubstring() {
		// Given
		Empresa empresa = createEmpresa();
		
		// When
		List<Empresa> list = this.dao.search(empresa.getNomeFantasia().substring(2, 10));
		
		// Then
		assertNotNull(list);
		assertEquals(1, list.size());
		assertEquals(empresa, list.get(0));
	}
	
	@Test
	public void testInvalidFindByNomeFantasia() {
		// Given
		Empresa empresa = createEmpresa();
		
		// When
		List<Empresa> list = this.dao.search("Nome fantasia invalido");
		
		// Then
		assertNotNull(list);
		assertEquals(0, list.size());
	}
	
	@Test
	public void testSave() {
		// Given
		RamoAtividade ramoAtividade = createRamoAtividade();
		Empresa empresa = new Empresa("Nome Fantasia X", "Razao Social", "123456789", new Date(1995, 7, 17), ramoAtividade, TipoEmpresa.MEI);
		
		// When
		em.getTransaction().begin();
		this.dao.save(empresa);
		em.getTransaction().commit();
		
		// Then
		List<Empresa> list = dao.search(empresa.getNomeFantasia());
		assertNotNull(list);
		assertEquals(1, list.size());
	}
	
	@Test
	public void testDelete() {
		// Given
		Empresa empresa = createEmpresa();
		
		// When
		this.dao.delete(empresa.getId());		
		
		// Then
		Empresa result = em.find(Empresa.class, empresa.getId());
		assertNull(result);
	}

}

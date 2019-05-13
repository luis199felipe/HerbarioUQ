package co.alfite.sis;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import co.alfite.sis.entidades.*;

/**
 * Clase encargada de probar las consultas usando JPQL
 * 
 * @author Melissa Neyder Pipe
 * @version 1.0
 */
@RunWith(Arquillian.class)
public class TestJPQL {

	/**
	 * instancia para realizar las transaciones con las entidades
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * general el archivo de depliegue de pruebas
	 * 
	 * @return genera un archivo de configuracion web
	 */
	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(Persona.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");

	}

	/**
	 * permite cargar la lista de personas
	 */
	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void listarPersonaTest() {
		Query query = entityManager.createQuery("select p1 from Persona p1");
		List listaPersona = query.getResultList();
		Iterator ite = listaPersona.iterator();

		while (ite.hasNext()) {
			System.out.println("1 " + ite.next());
		}
	}

	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void listarPersonaNamedTest() {
		TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.LISTAR_TODOS, Persona.class);
		List<Persona> personas = query.getResultList();
		// Assert.assertEquals(personas.get(0).getNombre(), "Melissa");
		Iterator ite = personas.iterator();

		while (ite.hasNext()) {
			System.out.println("2 " + ite.next());
		}
	}

	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "familiaPlanta.json" })
	public void loginTest() {
		TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.LISTAR_TODOS, Persona.class);

		query.setParameter("email", "email");
		query.setParameter("password", "password");

		Persona p = query.getSingleResult();

		Assert.assertEquals(p.getNombre(), "nombre");
	}

	//@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "familiaPlanta.json" })
	public void cantidadFamiliasTest() {
		TypedQuery<FamiliaPlanta> query = entityManager.createNamedQuery(FamiliaPlanta.FAMILIA_GET_NUMBER,
				FamiliaPlanta.class);

		List<FamiliaPlanta> p = query.getResultList();

		System.out.println(p.size());
		for (int i = 0; i < p.size(); i++) {
			System.out.println(p.get(i));	
		}
		
		
	}
	
	//@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json","registro.json","especiePlanta.json" })
	public void personasSinRegistros() {
		TypedQuery<Trabajador> query = entityManager.createNamedQuery(Trabajador.TRABAJADOR_GET_EMPTY_REGISTERS,
				Trabajador.class);

		List<Trabajador> p = query.getResultList();

		//deberia devolver todas las personas que no tienenregistros
		System.out.println(p.size()+"R");
		for (int i = 0; i < p.size(); i++) {
			System.out.println(p.get(i)+"R");	
		}
		
		
	}
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json","registro.json","especiePlanta.json" })
	public void registrosTrabajador() {
		TypedQuery<DTO> query = entityManager.createNamedQuery(Trabajador.TRABAJADOR_GET_REGISTERS,
				DTO.class);

		List<co.alfite.sis.DTO> p = query.getResultList();

		//deberia devolver todas las personas que no tienenregistros
		System.out.println(p.size()+"T");
		for (int i = 0; i < p.size(); i++) {
			System.out.println(p.get(i).getCedula()+"T");	
		}
		
		
	}
}

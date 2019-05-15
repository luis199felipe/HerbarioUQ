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
import co.alfite.sis.entidades.RegistroEspecie.Estado;

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
	public void cantidadFamiliasRegistradasTest() {
		TypedQuery<Long> query = entityManager.createNamedQuery(FamiliaPlanta.FAMILIA_GET_NUMBER, Long.class);

		Long a = query.getSingleResult();

		System.out.println(a);
	}

	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "especiePlanta.json", "generoPlanta.json", "familiaPlanta.json" })
	public void personasSinRegistros() {
		TypedQuery<Trabajador> query = entityManager.createNamedQuery(Trabajador.TRABAJADOR_GET_EMPTY_REGISTERS,
				Trabajador.class);

		List<Trabajador> p = query.getResultList();

		// haye un problema con el trabajor 9, 14
		Trabajador x = entityManager.find(Trabajador.class, "14");
		System.out.println(x.getNombre() + "##############");
		for (int i = 0; i < p.size(); i++) {
			System.out.println(p.get(i) + "R");
		}

	}

	//@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "especiePlanta.json", "generoPlanta.json", "familiaPlanta.json" })
	public void registrosTrabajador() {
		TypedQuery<DTO> query = entityManager.createNamedQuery(RegistroEspecie.TRABAJADOR_GET_REGISTERS, DTO.class);

		List<co.alfite.sis.DTO> p = query.getResultList();

		// deberia devolver todas las personas que no tienenregistros

		for (int i = 0; i < p.size(); i++) {
			System.out.println(p.get(i).getCedula() + "," + p.get(i).getNumeroRegistros());
		}

	}

	// @Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "especiePlanta.json", "generoPlanta.json", "familiaPlanta.json" })
	public void registrosAceptados() {
		TypedQuery<Long> query = entityManager.createNamedQuery(RegistroEspecie.TRABAJADOR_GET_ENVIOS, Long.class);

		query.setParameter("est", Estado.aprobado);
		List<Long> p = query.getResultList();

		for (int i = 0; i < p.size(); i++) {
			System.out.println(p.get(i));
		}

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "especiePlanta.json", "generoPlanta.json", "familiaPlanta.json" })
	public void familiaMasEspecies() {
		TypedQuery<DTO> query = entityManager.createNamedQuery(EspeciePlanta.FAMILIA_MAX_ESPECIE, DTO.class);

		List<DTO> p = query.getResultList();
		
		//System.out.println(p.get(0).getCedula() + ", "+ p.get(0).getNumeroRegistros());
		
//		DTO p2 = query.getSingleResult();
//		System.out.println(p2);
		
		// estoy reutilizando el dto por eso cedula y num registros
		// pero en realidad es nombrefamilia y numero de especies

		for (int i = 0; i < p.size(); i++) {
			System.out.println(p.get(i).getCedula() + "," + p.get(i).getNumeroRegistros());
		}

	}
}

package co.alfite.sis;

import static org.junit.Assert.assertEquals;

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

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "familiaPlanta.json" })
	public void cantidadFamiliasRegistradasTest() {
		TypedQuery<Long> query = entityManager.createNamedQuery(FamiliaPlanta.FAMILIA_GET_NUMBER, Long.class);

		Long a = query.getSingleResult();

		assertEquals("cantidad de familias incorrectas", 5 + "", a + "");

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "especiePlanta.json", "generoPlanta.json", "familiaPlanta.json" })
	public void personasSinRegistros() {
		TypedQuery<Trabajador> query = entityManager.createNamedQuery(Trabajador.TRABAJADOR_GET_EMPTY_REGISTERS,
				Trabajador.class);

		List<Trabajador> p = query.getResultList();
		assertEquals("cantidad de personas  sin registro incorrectas", 5, p.size());
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "especiePlanta.json", "generoPlanta.json", "familiaPlanta.json" })
	public void registrosTrabajador() {
		TypedQuery<DTO> query = entityManager.createNamedQuery(RegistroEspecie.TRABAJADOR_GET_REGISTERS, DTO.class);

		List<co.alfite.sis.DTO> p = query.getResultList();

		assertEquals("cantidad de registros asociados incorrectos", 3, p.size());

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "especiePlanta.json", "generoPlanta.json", "familiaPlanta.json" })
	public void registrosAceptados() {
		TypedQuery<Long> query = entityManager.createNamedQuery(RegistroEspecie.TRABAJADOR_GET_ENVIOS, Long.class);

		query.setParameter("est", Estado.aprobado);
		List<Long> p = query.getResultList();

		assertEquals("cantidad de registros aceptados incorrectos", 3, p.size());

	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "especiePlanta.json", "generoPlanta.json", "familiaPlanta.json" })
	public void familiaDadaUnaEspecie() {
		TypedQuery<FamiliaPlanta> query = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_FAMILIA_ID,
				FamiliaPlanta.class);

		query.setParameter("idEspecie", "esp1");
		FamiliaPlanta p = query.getSingleResult();

		assertEquals("la familia no existe", "fam1", p.getIdFamilia());
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "especiePlanta.json", "generoPlanta.json", "familiaPlanta.json" })
	public void especiesDelGenero() {
		TypedQuery<EspeciePlanta> query = entityManager.createNamedQuery(GeneroPlanta.GENERO_GET_ESPECIES,
				EspeciePlanta.class);
		query.setParameter("var", "gen1");
		List<EspeciePlanta> p = query.getResultList();

		assertEquals("la cantidad de especies es incorrecta", 3, p.size());
	}
	
	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json", "registro.json", "especiePlanta.json", "generoPlanta.json", "familiaPlanta.json" })
	public void trabajadoresConRegistro() {
		TypedQuery<Trabajador> query = entityManager.createNamedQuery(RegistroEspecie.TRABAJADOR_WITH_REGISTERS,
				Trabajador.class);
	
		List<Trabajador> p = query.getResultList();

		assertEquals("la cantidad de trabajadores sin registro es  incorrecta", 5, p.size());
	}

}

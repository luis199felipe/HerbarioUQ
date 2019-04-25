package co.alfite.sis;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
 * Clase de pruebas dedicada para la pruebas de las entidades
 * 
 * @author Melissa Neyder Pipe
 * @version 1.0
 */
@RunWith(Arquillian.class)
public class TestModelo {

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

	@Test
	@UsingDataSet({"persona.json"})
	@Transactional(value = TransactionMode.COMMIT)
	public void insertarPersonaTest() {
		Administrador administrador = new Administrador();
		administrador.setIdPersona("1321");
		administrador.setNombre("melissa");
		administrador.setTelefono("305");
		administrador.setFechaNacimiento(new Date());
		administrador.setEmail("mdn@com");
		administrador.setPassword("111111");

		entityManager.persist(administrador);
		
		Administrador a = entityManager.find(Administrador.class, administrador.getIdPersona());
		
		Assert.assertNotNull(a);
	}

}

package co.alfite.sis;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

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

import org.junit.Test;
import org.junit.runner.RunWith;

import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.entidades.Recolector;

import co.alfite.sis.entidades.Persona;

/*
 * clase que permite testear o verificar que se pueda eliminar correctamente  de las entidades
 * 
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * 
 * @version 1.0
 */

@RunWith(Arquillian.class)
public class TestDelete {

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

	/*
	 * metodo que elimina el empleado
	 */

	@Test
	@UsingDataSet({ "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void eliminarEmpleado() {
		Empleado e1 = entityManager.find(Empleado.class, "2");
		entityManager.remove(e1);

		assertEquals("El empleado no se elimino", null, entityManager.find(Empleado.class, "2"));

	}

	/*
	 * metodo que elimina un recolector
	 */
	@Test
	@UsingDataSet({ "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void eliminarRecolector() {

		Recolector r1 = entityManager.find(Recolector.class, "1");
		entityManager.remove(r1);

		assertEquals("El recolector no se elimino", null, entityManager.find(Recolector.class, "1"));

	}

	/*
	 * metodo que elimina la familia de una planta
	 */
	@Test
	@UsingDataSet({ "familiaPlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void eliminarFamiliaPlanta() {

		FamiliaPlanta familiaPlanta = entityManager.find(FamiliaPlanta.class, 1);
		entityManager.remove(familiaPlanta);
		assertEquals("El la familia no se elimino", null, entityManager.find(FamiliaPlanta.class, 1));

	}

	/*
	 * metodo que elimina el genero de una planta
	 */
	@Test
	@UsingDataSet({ "generoPlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void eliminarGeneroPlanta() {

		GeneroPlanta generoPlanta = entityManager.find(GeneroPlanta.class, 1);
		entityManager.remove(generoPlanta);
		assertEquals("El genero no se elimino", null, entityManager.find(GeneroPlanta.class, 1));

	}

	/*
	 * metdo que elimina un planta
	 */
	@Test
	@UsingDataSet({ "especiePlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void eliminarEspeciePlanta() {

		EspeciePlanta especiePlanta = entityManager.find(EspeciePlanta.class, 1);
		entityManager.remove(especiePlanta);
		assertEquals("la planta  no se elimino", null, entityManager.find(EspeciePlanta.class, 1));
	}

}

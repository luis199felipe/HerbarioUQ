package co.alfite.sis;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.runner.RunWith;

import co.alfite.sis.entidades.Persona;

@RunWith(Arquillian.class)

public class TestRead {

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
	 * permite ver la informacion detallada
	 */
	public void verDetalleEspecie() {

	}
	
	/*
	 * permite ver la informacion detallada
	 */
	public void listarEspecies() {

	}
	/*
	 * permite ver la informacion detallada
	 */
	public void listarEspeciesPorGenero() {

	}
	/*
	 * permite ver la informacion detallada
	 */
	public void listarEspeciesPorFamilia() {

	}
	/*
	 * permite ver la informacion detallada
	 */
	public void listarEspeciesRechazadas() {

	}
	
	public void listarEspeciesAceptadas() {

	}
	
}

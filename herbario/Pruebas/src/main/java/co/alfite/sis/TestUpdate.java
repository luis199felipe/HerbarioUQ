package co.alfite.sis;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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

import co.alfite.sis.entidades.Administrador;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Recolector;

/**
 * Clase encargada de probar las consultas usando JPQL
 * 
 * @author Melissa Neyder Pipe
 * @version 1.0
 */
@RunWith(Arquillian.class)
public class TestUpdate {

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
	 * Permite la edicion de un Empleado
	 */
	@Test
	@UsingDataSet({ "persona.json"})
	@Transactional(value = TransactionMode.ROLLBACK)
	public void editarEmpleado() {
		
		//Obtener empleado a modificar
		Empleado empEditar = entityManager.find(Empleado.class, "9");
		
		//Edicion del nombre del empleado
		empEditar.setTelefono("123456789");
		
		//Modificacion de empleado
		entityManager.merge(empEditar);
		
		//Obtener empleado ya modificado
		Empleado empEditar2 = entityManager.find(Empleado.class, "9");

		//Verificacion de que el empleado cambio el nombre
		Assert.assertEquals("No cambio el telefono", "123456789", empEditar2.getTelefono());

	}
	
	/*
	 * Permite la edicion de un Empleado
	 */
	@Test
	@UsingDataSet({ "generoPlanta.json"})
	@Transactional(value = TransactionMode.ROLLBACK)
	public void editarGeneroPlanta() {
		
		//Obtener genero de la planta a modificar
		GeneroPlanta generoPlantaEditar = entityManager.find(GeneroPlanta .class, "gen1");
		
		//Edicion del nombre del genero de la planta de Barnadesioideae a Barnade
		generoPlantaEditar.setNombre("Barnade");
		
		//Modificacion de genero de la planta
		entityManager.merge(generoPlantaEditar);
		
		//Obtener  genero de la planta ya modificado
		GeneroPlanta  genPlanta2 = entityManager.find(GeneroPlanta .class, "gen1");

		//Verificacion de que el  genero de la planta cambio el nombre
		Assert.assertEquals("No cambio el nombre", "Barnade", genPlanta2.getNombre());

	}


	public void editarFamiliaPlanta() {
		//Obtener Familia Planta a modificar
		FamiliaPlanta familiaPlantaEditar = entityManager.find(FamiliaPlanta.class, "fam1");
		
		//Edicion del nombre del Familia Planta de "familia asteraceae" a "asteraceae"
		familiaPlantaEditar.setNombre("asteraceae");
		
		//Modificacion de Familia Planta 
		entityManager.merge(familiaPlantaEditar);
		
		//Obtener Familia Planta ya modificado
		FamiliaPlanta familiaPlantaEditar2 = entityManager.find(FamiliaPlanta.class, "gen1");

		//Verificacion de que la Familia Planta  cambio el nombre
		Assert.assertEquals("No cambio el nombre", "asteraceae", familiaPlantaEditar2.getNombre());

	}

	public void editarEmpleado(Empleado e) {
		
	}

	public void editarRecolector(Recolector e) {
		entityManager.merge(e);
	}

	public void editarAdministrador(Administrador e) {
		entityManager.merge(e);
	}

}

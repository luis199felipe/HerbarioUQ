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
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * @version 1.0
 */
@RunWith(Arquillian.class)
public class TestCreate {

	/**
	 * Instancia para realizar las transaciones con las entidades
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
	 * test que crea un nuevo genero de planta
	 */

	@Test
	@UsingDataSet({ "generoPlanta.json", "familiaPlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void createGeneroPlantaTest() {
		FamiliaPlanta familia = entityManager.find(FamiliaPlanta.class, "fam1");

		GeneroPlanta genero = new GeneroPlanta();
		genero.setFamiliaPlanta(familia);
		genero.setIdGenero("gen11");
		genero.setNombre("Planteusom");

		entityManager.persist(genero);

		GeneroPlanta generoGuardado = entityManager.find(GeneroPlanta.class, genero.getIdGenero());
		Assert.assertNotNull(generoGuardado);
	}

	/**
	 * test que crea una nueva familia de planta
	 */

	@Test
	@UsingDataSet({ "herbarioUq.json", "familiaPlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void createFamiliaPlantaTest() {
		HerbarioUQ herbario = entityManager.find(HerbarioUQ.class, "her1");

		FamiliaPlanta familia = new FamiliaPlanta();
		familia.setHerbario(herbario);
		familia.setIdFamilia("fam6");
		familia.setNombre("familiusos");

		entityManager.persist(familia);

		FamiliaPlanta familiaGuardado = entityManager.find(FamiliaPlanta.class, familia.getIdFamilia());
		Assert.assertNotNull(familiaGuardado);

	}

	/**
	 * test que crea un nuevo empleado
	 */

	@Test
	@UsingDataSet({ "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void createEmpleadoTest() {
		HerbarioUQ herbario = entityManager.find(HerbarioUQ.class, "her1");

		Empleado empleado = new Empleado();
		empleado.setIdPersona("15");
		empleado.setNombre("Mariana");
		empleado.setTelefono("3202394356");
		empleado.setEmail("mariana@gmail.com");
		empleado.setFechaNacimiento(new Date());
		empleado.setPassword("mari1234");
		empleado.setHerbario(herbario);

		entityManager.persist(empleado);

		Empleado empleadoGuardado = entityManager.find(Empleado.class, empleado.getIdPersona());
		Assert.assertNotNull(empleadoGuardado);

	}

	/**
	 * test que crea un nuevo recolector
	 */

	@Test
	@UsingDataSet({ "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void createRecolectorTest() {
		HerbarioUQ herbario = entityManager.find(HerbarioUQ.class, "her1");

		Recolector recolector = new Recolector();
		recolector.setIdPersona("17");
		recolector.setNombre("Jose");
		recolector.setTelefono("3233456546");
		recolector.setEmail("jose@gmail.com");
		recolector.setFechaNacimiento(new Date());
		recolector.setPassword("root");
		recolector.setHerbario(herbario);

		entityManager.persist(recolector);

		Recolector recolectorGuardado = entityManager.find(Recolector.class, recolector.getIdPersona());
		Assert.assertNotNull(recolectorGuardado);
	}

	/**
	 * test que crea un nuevo Usuario
	 */

	@Test
	@UsingDataSet({ "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void createUsuarioTest() {
		HerbarioUQ herbario = entityManager.find(HerbarioUQ.class, "her1");

		Usuario usuario = new Usuario();
		usuario.setIdPersona("16");
		usuario.setNombre("Maria");
		usuario.setTelefono("3234943546");
		usuario.setEmail("maria@gmail.com");
		usuario.setFechaNacimiento(new Date());
		usuario.setPassword("root");
		usuario.setHerbario(herbario);

		entityManager.persist(usuario);

		Usuario recolectorGuardado = entityManager.find(Usuario.class, usuario.getIdPersona());
		Assert.assertNotNull(recolectorGuardado);
	}
	
	/**
	 * test que crea un nuevo registro de una especie
	 */

	@Test
	@UsingDataSet({ "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void createRegistoEspecieTest() {
		Trabajador trabajador = entityManager.find(Trabajador.class, "3");
		RegistroEspecie registro = new RegistroEspecie();
		registro.setIdRegistro(21);
		registro.setMensaje("Espere...");
		registro.setEstado(Estado.aprobado);
		registro.setTrabajador(trabajador);
		
		entityManager.persist(registro);
		
		RegistroEspecie registroGuardado = entityManager.find(RegistroEspecie.class, 21);
		Assert.assertNotNull(registroGuardado);
	}

}

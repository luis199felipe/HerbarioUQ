package co.alfite.sis;

import static org.junit.Assert.assertEquals;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.RegistroEspecie.Estado;

/*
 * clase que permite testear o verificar que las consultas se esten haciendo
 * bien
 * 
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * 
 * @version 1.0
 */
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
	@Test
	@UsingDataSet({ "familiaPlanta.json", "especiePlanta.json", "herbarioUq.json", "registro.json", "persona.json",
			"generoPlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void verDetalleEspeciePorNombre() {
		String nombrePlanta = "planta4";
		Query query = entityManager.createQuery(
				"SELECT especie FROM EspeciePlanta especie where especie.nombre='" + nombrePlanta + "'",
				EspeciePlanta.class);
		EspeciePlanta resultado = (EspeciePlanta) query.getSingleResult();
		assertEquals("No existe la planta con el nombre dado", nombrePlanta, resultado.getNombre());
	}

	/*
	 * permite probar el listado de especies
	 */
	@Test
	@UsingDataSet({ "familiaPlanta.json", "especiePlanta.json", "herbarioUq.json", "registro.json", "persona.json",
			"generoPlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarEspecies() {
		Query query = entityManager.createNamedQuery(EspeciePlanta.ESPECIE_GET_ALL);
		List<EspeciePlanta> resultados = query.getResultList();
		assertEquals("Cantidad de especies erroneas", 20, resultados.size());
	}

	/*
	 * permite probar la lista de especies de acuerdo a un genero dado
	 */
	@Test
	@UsingDataSet({ "familiaPlanta.json", "especiePlanta.json", "herbarioUq.json", "registro.json", "persona.json",
			"generoPlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarEspeciesPorGenero() {
		Query query = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_GENERO);
		query.setParameter("gen", "gen1");
		List<EspeciePlanta> resultados = query.getResultList();
		assertEquals("Cantidad de especies por genero erroneas", 2, resultados.size());
	}

	/*
	 * permite probar la lista de especies dada una familia
	 */
	@Test
	@UsingDataSet({ "familiaPlanta.json", "especiePlanta.json", "herbarioUq.json", "registro.json", "persona.json",
			"generoPlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarEspeciesPorFamilia() {
		Query query = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_FAMILIA);

		query.setParameter("fam", "fam1");
		List resultados = query.getResultList();
		assertEquals("Cantidad de especies por familia erroneas", 4, resultados.size());
	}

	/*
	 * permite probar la lista de especies rechazadas
	 */
	@Test
	@UsingDataSet({ "familiaPlanta.json", "especiePlanta.json", "herbarioUq.json", "registro.json", "persona.json",
			"generoPlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarEspeciesRechazadas() {
		Query query = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_ESTADO);
		query.setParameter("est", Estado.rechazado);
		List resultados = query.getResultList();
		assertEquals("Cantidad de especies rechazadas erroneas", 3, resultados.size());
	}

	/*
	 * 
	 * permite probar la lista de especies rechazadas
	 */
	@Test
	@UsingDataSet({ "familiaPlanta.json", "especiePlanta.json", "herbarioUq.json", "registro.json", "persona.json",
			"generoPlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarEspeciesAceptadas() {
		Query query = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_ESTADO);
		query.setParameter("est", Estado.aprobado);
		List resultados = query.getResultList();
		assertEquals("Cantidad de especies aceptadas erroneas", 7, resultados.size());
	}

}

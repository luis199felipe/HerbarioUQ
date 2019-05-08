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
import co.alfite.sis.entidades.Resenia.Estado;

/**
 * Clase encargada de probar la opcion unica del proyecto
 * 
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * @version 1.0
 */
@RunWith(Arquillian.class)
public class TestOpcionUnica {

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
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(MeGustaEspeciePlanta.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	/**
	 * un usuario le da me gusta a una especie
	 */
	@Test
	@UsingDataSet({ "meGusta.json", "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void createMeGusta() {
		Usuario usuario = entityManager.find(Usuario.class, "4");

		EspeciePlanta especie = entityManager.find(EspeciePlanta.class, "esp20");

		MeGustaEspeciePlanta meGusta = new MeGustaEspeciePlanta();
		meGusta.setFecha(new Date());
		meGusta.setIdMegusta(7);
		meGusta.setUsuario(usuario);
		meGusta.setEspecie(especie);

		entityManager.persist(meGusta);

		MeGustaEspeciePlanta meGustaGuardado = entityManager.find(MeGustaEspeciePlanta.class, 7);
		Assert.assertNotNull(meGustaGuardado);
	}

	/**
	 * un usuario da una reseña a una especie
	 */
	@Test
	@UsingDataSet({ "resenia.json", "persona.json", "especiePlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void createResenia() {
		Usuario usuario = entityManager.find(Usuario.class, "12");

		EspeciePlanta especie = entityManager.find(EspeciePlanta.class, "esp15");

		Resenia resenia = new Resenia();
		resenia.setIdResenia(10);
		resenia.setEstado(Estado.aprobado);
		resenia.setTexto("La especie tambien se conoce como plenauseaces");
		resenia.setUsuario(usuario);
		resenia.setEspecie(especie);

		entityManager.persist(resenia);

		Resenia reseniaGuardado = entityManager.find(Resenia.class, 10);
		Assert.assertNotNull(reseniaGuardado);
	}

	/**
	 * un usuario puede ver un listado de me gusta de una especie
	 */
	@Test
	@UsingDataSet({ "meGusta.json", "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void ReadMeGustaDeUnaEspecie() {
		TypedQuery<MeGustaEspeciePlanta> query = entityManager
				.createNamedQuery(MeGustaEspeciePlanta.MEGUSTAESPECIE_ESPECIE, MeGustaEspeciePlanta.class);
		query.setParameter("esp", "esp1");
		List<MeGustaEspeciePlanta> meGustas = query.getResultList();

		Assert.assertEquals("El tamanio no coincide", 2, meGustas.size());

//		Iterator ite = meGustas.iterator();
//
//		while (ite.hasNext()) {
//			System.out.println("1 " + ite.next());
//		}
	}

	/**
	 * un usuario puede ver un listado de las personas que hicieron una reseña de
	 * una especie
	 */
	@Test
	@UsingDataSet({ "resenia.json", "persona.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void ReadReseniasDeUnaEspecie() {

		TypedQuery<Resenia> query = entityManager
				.createNamedQuery(Resenia.RESENIA_ESPECIE, Resenia.class);
		query.setParameter("esp", "esp12");
		List<Resenia> resenias = query.getResultList();

		Assert.assertEquals("El tamanio no coincide", 3, resenias.size());
		
//		Iterator ite = personas.iterator();
//
//		while (ite.hasNext()) {
//			System.out.println("2 " + ite.next());
//		}
	}

	/**
	 * un usuario puede eliminar su megusta de una especie
	 */
	@Test
	@UsingDataSet({ "meGusta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void deleteMeGusta() {

		MeGustaEspeciePlanta meGusta = entityManager.find(MeGustaEspeciePlanta.class, 4);
		entityManager.remove(meGusta);

		Assert.assertEquals("El meGusta no se elimino", null, entityManager.find(MeGustaEspeciePlanta.class, 4));

	}

	/**
	 * un usuario puede eliminar su reseña de una especie
	 */
	@Test
	@UsingDataSet({ "resenia.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void deleteResenia() {
		Resenia resenia = entityManager.find(Resenia.class, 5);
		entityManager.remove(resenia);

		Assert.assertEquals("El resenia no se elimino", null, entityManager.find(Resenia.class, 5));

	}

	/**
	 * un usuario puede cambiar el texto de su reseña
	 */
	@Test
	@UsingDataSet({ "resenia.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void upDateResenia() {
		Resenia resenia = entityManager.find(Resenia.class, 3);

		resenia.setTexto("la planta es muy linda");

		entityManager.merge(resenia);

		Resenia reseniaEditado = entityManager.find(Resenia.class, 3);

		Assert.assertEquals("No cambio el telefono", "la planta es muy linda", reseniaEditado.getTexto());
	}

	/**
	 * Busca la especie que tiene mas me gusta de todas las especies
	 */
	@Test
	@UsingDataSet({ "meGusta.json", "especiePlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void ReadEspecieConMasMeGustas() {
		TypedQuery<MeGustaEspeciePlanta> query = entityManager
				.createNamedQuery(MeGustaEspeciePlanta.MEGUSTAESPECIE_ESPECIEMASMEGUSTAS, MeGustaEspeciePlanta.class);
		MeGustaEspeciePlanta meGustas = query.getSingleResult();
		System.out.println(meGustas.getEspecie().getIdEspecie());
	}

	/**
	 * Busca la especie que tiene mas reseñas de todas las especies
	 */
	@Test
	@UsingDataSet({ "resenia.json", "especiePlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void ReadEspecieConMasResenias() {

	}

}

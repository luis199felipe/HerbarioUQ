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
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackage(Persona.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}
	
	/**
	 * un usuario le da me gusta a una especie
	 */
	@Test
	@UsingDataSet({ "meGusta.json","resenia.json" })
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
	@UsingDataSet({ "herbarioUq.json", "familiaPlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void createResenia() {
		
	}
	
	/**
	 * un usuario puede ver un listado de las reseñas de una especie
	 */
	@Test
	@UsingDataSet({ "herbarioUq.json", "familiaPlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void ReadReseniasDeUnaEspecie() {
		
	}
	
	/**
	 * un usuario puede ver un listado de me gusta de una especie
	 */
	@Test
	@UsingDataSet({ "herbarioUq.json", "familiaPlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void ReadMeGustaDeUnaEspecie() {
		
	}
	
	/**
	 * un usuario puede eliminar su megusta de una especie
	 */
	@Test
	@UsingDataSet({ "herbarioUq.json", "familiaPlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void deleteMeGusta() {
		
	}
	
	/**
	 * un usuario puede eliminar su reseña de una especie
	 */
	@Test
	@UsingDataSet({ "herbarioUq.json", "familiaPlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void deleteResenia() {
		
	}
	
	/**
	 * un usuario puede modificar la fecha de su me gusta 
	 */
	@Test
	@UsingDataSet({ "herbarioUq.json", "familiaPlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void upDateMeGusta() {
		
	}
	
	/**
	 * un usuario puede cambiar el texto de su reseña
	 */
	@Test
	@UsingDataSet({ "herbarioUq.json", "familiaPlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void upDateResenia() {
		
	}
	
	/**
	 * Busca la especie que tiene mas me gusta de todas las especies
	 */
	@Test
	@UsingDataSet({ "herbarioUq.json", "familiaPlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void ReadEspecieConMasMeGustas() {
		
	}
	
	/**
	 * Busca la especie que tiene mas reseñas de todas las especies
	 */
	@Test
	@UsingDataSet({ "herbarioUq.json", "familiaPlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void ReadEspecieConMasResenias() {
		
	}
	
	
	
}

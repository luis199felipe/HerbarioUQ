package co.alfite.sis;

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
import org.junit.Test;
import org.junit.runner.RunWith;

import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.RegistroEspecie.Estado;

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
	 * permite ver las especies por genero especifico
	 */
	@Test
	@UsingDataSet({"familiaPlanta.json","especiePlanta.json","herbarioUq.json","registro.json","persona.json","generoPlanta.json"})
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarEspeciesPorGenero() {
		Query query = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_GENERO);
		query.setParameter("gen", "gen1");
		List<EspeciePlanta> resultados = query.getResultList();
		Iterator<EspeciePlanta> ep = resultados.iterator();
		while (ep.hasNext()) {
			EspeciePlanta especiePlanta = (EspeciePlanta) ep.next();
			System.out.println(especiePlanta.getNombre());
		}
	}
	/*
	 * permite ver la informacion detallada
	 */
	@Test
	@UsingDataSet({"familiaPlanta.json","especiePlanta.json","herbarioUq.json","registro.json","persona.json","generoPlanta.json"})
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarEspeciesPorFamilia() {
		Query query = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_FAMILIA);
		
		query.setParameter("fam", "fam1");
		List resultados = query.getResultList();
		Iterator ep = resultados.iterator();
		while (ep.hasNext()) {
			Object especiePlanta =  ep.next();
			System.out.println(especiePlanta);
		}
	}
	/*
	 * permite ver la informacion detallada
	 */
	@Test
	@UsingDataSet({"familiaPlanta.json","especiePlanta.json","herbarioUq.json","registro.json","persona.json","generoPlanta.json"})
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarEspeciesRechazadas() {
		Query query = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_ESTADO);
		query.setParameter("est", Estado.rechazado);
		List resultados = query.getResultList();
		Iterator ep = resultados.iterator();
		while (ep.hasNext()) {
			Object especiePlanta = ep.next();
			System.out.println(especiePlanta);
		}
	}
	
	@Test
	@UsingDataSet({"familiaPlanta.json","especiePlanta.json","herbarioUq.json","registro.json","persona.json","generoPlanta.json"})
	@Transactional(value = TransactionMode.ROLLBACK)
	public void listarEspeciesAceptadas() {
		Query query = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_ESTADO);
		query.setParameter("est", Estado.aprobado);
		List resultados = query.getResultList();
		Iterator ep = resultados.iterator();
		while (ep.hasNext()) {
			Object especiePlanta = ep.next();
			System.out.println(especiePlanta);
		}
	}
	
	
}

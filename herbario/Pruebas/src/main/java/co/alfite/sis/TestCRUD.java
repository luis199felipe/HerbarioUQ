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
 * Clase encargada de probar las consultas usando JPQL
 * 
 * @author Melissa Neyder Pipe
 * @version 1.0
 */
@RunWith(Arquillian.class)
public class TestCRUD {

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
	@UsingDataSet({ "persona.json", "registro.json","resenia.json","herbarioUq.json", "generoPlanta.json",
			"familiaPlanta.json", "especiePlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void insertarPersonaTest() {
		Empleado e1 = new Empleado("25", "Julio", "23423432", new Date("14/12/1999"), "123", "julio@gmail.com");
		crearEmpleado(e1);

		Assert.assertNotNull(buscarEmpleado("25"));

		List<Empleado> misEmp = getAllEmpleados();
		Iterator<Empleado> it = misEmp.iterator();
		while (it.hasNext()) {
			Empleado p = (Empleado) it.next();
			System.out.println(p.toString());
		}

	}

	public void crearEmpleado(Empleado e) {
		entityManager.persist(e);
	}

	public void editarEmpleado(Empleado e) {
		entityManager.merge(e);
	}

	public void eliminarEmpleado(Empleado e) {
		entityManager.remove(e);
	}

	public Empleado buscarEmpleado(String idPersona) {
		Empleado e = entityManager.find(Empleado.class, idPersona);
		return e;
	}

	public List<Empleado> getAllEmpleados() {
		Query q = entityManager.createNamedQuery(Empleado.EMPLEADO_GET_ALL);
		List<Empleado> empleados = q.getResultList();
		return empleados;
	}

}

package co.alfite.sis;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.Persona;

public class TestAdministrador {

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

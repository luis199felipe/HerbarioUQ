package co.alfite.sis;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.persistence.TypedQuery;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.alfite.sis.ejb.AdministradorEJB;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;

@RunWith(Arquillian.class)
public class TestAdministradorEJB {

	@EJB
	private AdministradorEJB adminEJB;

	@Deployment
	public static Archive<?> createDeploymentPackage() {
		return ShrinkWrap.create(JavaArchive.class).addClass(AdministradorEJB.class)
				.addPackage(Persona.class.getPackage())
				.addAsResource("persistenceForTest.xml", "META-INF/persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Test
	@Transactional(value = TransactionMode.ROLLBACK)
	@UsingDataSet({ "persona.json" })
	public void insertarEmpleado() {

		Empleado e = new Empleado();
		e.setNombre("juan");
		e.setEmail("juan@hotmail.com");
		e.setPassword("7777");
		e.setTelefono("3424256373");
		e.setFechaNacimiento(new Date());
		e.setIdPersona("67");
		try {
			adminEJB.insertarEmpleado(e);
		} catch (ElementoRepetidoExcepcion e1) {
			Assert.fail(e1.getMessage());
		} catch (Exception e2) {
			Assert.fail(String.format("error inesperado %s", e2.getMessage()));
		}
	}
}

package co.alfite.sis;

import static org.junit.Assert.assertEquals;


import java.text.SimpleDateFormat;
import java.util.Date;
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
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.RegistroEspecie;
/*
 * clase que permite testear o verificar que las consultas se esten haciendo
 * bien
 * 
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * 
 * @version 1.0
 */
@RunWith(Arquillian.class)
public class TestConsultas {
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
	 * permite ver la informacion detallada de un registro
	 */
	@Test
	@UsingDataSet({ "familiaPlanta.json", "especiePlanta.json", "herbarioUq.json", "registro.json", "persona.json",
			"generoPlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void verDetalleRegistroPorFecha() {
		Date fecha = null;
		try {
			fecha = new SimpleDateFormat("yyyy-MM-dd").parse("1999-10-12");
		} catch (Exception e) {

		}	
		TypedQuery query = entityManager.createNamedQuery(RegistroEspecie.REGISTRO_FECHA,RegistroEspecie.class);
		query.setParameter("fecha",fecha);
		List<Object> resultado = query.getResultList();
		assertEquals("Numero de registros en fecha 1999-10-12 inesperado",6, resultado.size());
	}
	
	/*
	 * permite ver la informacion detallada de un registro Con DTO
	 */
	@Test
	@UsingDataSet({ "familiaPlanta.json", "especiePlanta.json", "herbarioUq.json", "registro.json", "persona.json",
			"generoPlanta.json" })
	@Transactional(value = TransactionMode.ROLLBACK)
	public void verDetalleRegistroPorFechaConDTO() {
//		Date fecha = null;
//		try {
//			fecha = new SimpleDateFormat("yyyy-MM-dd").parse("1999-10-12");
//		} catch (Exception e) {
//
//		}	
//		TypedQuery query = entityManager.createNamedQuery(RegistroEspecie.REGISTRO_FECHA_DTO,RegistroFechaDTO.class);
//		query.setParameter("fecha",fecha);
//		List<co.alfite.sis.entidades.RegistroFechaDTO> resultado = query.getResultList();
//		assertEquals("Numero de registros en fecha 1999-10-12 inesperado",6, resultado.size());
	}
	
	

}

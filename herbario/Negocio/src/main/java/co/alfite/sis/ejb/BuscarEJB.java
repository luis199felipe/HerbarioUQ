package co.alfite.sis.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.sun.javafx.scene.control.skin.TreeTableRowSkin;

import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.RegistroEspecie.Estado;

/**
 * Session Bean implementation class BuscarEJB
 */
@Stateless
@LocalBean
public class BuscarEJB implements BuscarEJBRemote {

	@PersistenceContext
	private EntityManager entityManager;

	public BuscarEJB() {

	}

	public Persona personaPorCredenciales(String correo, String password) {

		TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.PERSONA_POR_CREDENCIALES, Persona.class);

		query.setParameter("email", correo);
		query.setParameter("password", password);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}
	public Persona personaPorCorreo(String correo) {

		TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.PERSONA_POR_EMAIL, Persona.class);

		query.setParameter("email", correo);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	public List<EspeciePlanta> especiesPorEstado(Estado est) {

		TypedQuery<EspeciePlanta> query = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_ESTADO,
				EspeciePlanta.class);

		query.setParameter("est", est);

		return query.getResultList();

	}

	public List<EspeciePlanta> especiesPorFamilia(String nombreFamilia) {

		TypedQuery<EspeciePlanta> query = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_POR_NOMBRE_FAMILIA,
				EspeciePlanta.class);

		query.setParameter("nombreFam", nombreFamilia);

		return query.getResultList();

	}

//	public List<EspeciePlanta> especiesPorGenero(Estado est) {
//
//		TypedQuery<EspeciePlanta> query = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_ESTADO,
//				EspeciePlanta.class);
//
//		query.setParameter("est", est);
//
//		return query.getResultList();
//
//	}

}

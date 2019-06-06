package co.alfite.sis.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.alfite.sis.entidades.Administrador;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.RegistroEspecie.Estado;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;

/**
 * Session Bean implementation class ActualizarEJB
 */
@Stateless
@LocalBean
public class ActualizarEJB implements ActualizarEJBRemote {

	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ActualizarEJB() {
		// TODO Auto-generated constructor stub
	}



	@Override
	public Empleado ActualizarEmpleado(Empleado empleado){

		if (entityManager.find(Empleado.class, empleado.getIdPersona()) != null) {
			try {
				entityManager.merge(empleado);
				return empleado;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;

		}
	}

	@Override
	public Recolector ActualizarRecolector(Recolector recolector) {

		if (entityManager.find(Recolector.class, recolector.getIdPersona()) != null) {
			try {
				entityManager.merge(recolector);
				return recolector;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

		} else {
			return null;

		}
		
	}

	@Override
	public Object ActualizarAdministrador(Administrador administrador) {
		if (entityManager.find(Recolector.class, administrador.getIdPersona()) != null) {
			try {
				entityManager.merge(administrador);
				return administrador;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}

		} else {
			return null;

		}
		
	}

}

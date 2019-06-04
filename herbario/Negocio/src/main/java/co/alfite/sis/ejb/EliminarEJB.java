package co.alfite.sis.ejb;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.RegistroEspecie.Estado;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;

public class EliminarEJB implements EliminarEJBRemote{
	/**
	 * Default constructor.
	 */

	@PersistenceContext
	EntityManager entityManager;

	public EliminarEJB() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public boolean eliminarRegistro(RegistroEspecie registro) {

		if (entityManager.find(RegistroEspecie.class, registro.getIdRegistro()) != null) {
			try {
				entityManager.remove(registro);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {

			return false;
		}

	}
	@Override
	public boolean eliminarEmpleado(Empleado empleado) {

		if (entityManager.find(Empleado.class, empleado.getIdPersona()) != null) {
			try {
				entityManager.remove(empleado);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		} else {
			return false;
		}
	}
	@Override
	public boolean eliminarRecolector(Recolector recolector) {

		if (entityManager.find(Recolector.class, recolector.getIdPersona()) != null) {
			try {
				entityManager.remove(recolector);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		} else {
			return false;
		}
	}
}

package co.alfite.sis.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;

/**
 * Session Bean implementation class ListarBean
 */
@Stateless
@LocalBean
public class ListarEJB implements ListarEJBRemote {

	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ListarEJB() {
		// TODO Auto-generated constructor stub
	}

	public List<Empleado> listarEmpleados() {
		TypedQuery<Empleado> query = entityManager.createNamedQuery(Empleado.EMPLEADO_GET_ALL, Empleado.class);
		return query.getResultList();
	}

	public List<Recolector> listarRecolectores() {

		TypedQuery<Recolector> query = entityManager.createNamedQuery(Recolector.RECOLECTOR_GET_ALL, Recolector.class);
		return query.getResultList();
	}

	public List<RegistroEspecie> listarRegistros() {

		TypedQuery<RegistroEspecie> query = entityManager.createNamedQuery(RegistroEspecie.REGISTRO_GET_ALL,
				RegistroEspecie.class);
		return query.getResultList();
	}

}

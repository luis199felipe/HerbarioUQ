package co.alfite.sis.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.RegistroEspecie.Estado;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;

/**
 * Session Bean implementation class AdministradorEJB
 */
@Stateless
@LocalBean
public class AdministradorEJB implements AdministradorEJBRemote {

	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public AdministradorEJB() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.alfite.sis.ejb.AdministradorEJBRemote#insertarEmpleado(co.alfite.sis.
	 * entidades.Empleado)
	 */
	public Empleado insertarEmpleado(Empleado empleado) throws ElementoRepetidoExcepcion {

		if (entityManager.find(Empleado.class, empleado.getIdPersona()) != null) {
			throw new ElementoRepetidoExcepcion("el empleado con esa cedula ya fue registrado");

		} else if (buscarPorEmail(empleado) != null) {
			throw new ElementoRepetidoExcepcion("el empleado con ese email ya fue registrado");

		}
		try {
			entityManager.persist(empleado);
			return empleado;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private Persona buscarPorEmail(Persona per) {

		try {
			TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.PERSONA_POR_EMAIL, Persona.class);

			query.setParameter("email", per.getEmail());
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	public FamiliaPlanta insertarFamilia(FamiliaPlanta familia) throws ElementoRepetidoExcepcion {

		if (entityManager.find(FamiliaPlanta.class, familia.getIdFamilia()) != null) {
			throw new ElementoRepetidoExcepcion("la familia con este id ya fue registrada");

		}

		try {
			entityManager.persist(familia);
			return familia;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public GeneroPlanta insertarGenero(GeneroPlanta genero) throws ElementoRepetidoExcepcion {

		if (entityManager.find(GeneroPlanta.class, genero.getIdGenero()) != null) {
			throw new ElementoRepetidoExcepcion("el genero con este id ya fue registrado");

		}

		try {
			entityManager.persist(genero);
			return genero;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

//	public boolean validarRegistro(RegistroEspecie registro) throws ElementoRepetidoExcepcion {
//
////		boolean r = true;
////		if (entityManager.find(RegistroEspecie.class, registro.get) != null
////				&& entityManager.find(RegistroEspecie.class, registro.getIdRegistro()) != null) {
////
////			r = false;
////		} else {
////
////		}
//
//	}
}
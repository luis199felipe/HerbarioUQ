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
import co.alfite.sis.entidades.Recolector;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.alfite.sis.ejb.AdministradorEJBRemote#insertarRecolector(co.alfite.sis.
	 * entidades.Recolector)
	 */
	public Recolector insertarRecolector(Recolector recolector) throws ElementoRepetidoExcepcion {

		if (entityManager.find(Recolector.class, recolector.getIdPersona()) != null) {
			throw new ElementoRepetidoExcepcion("el recolector con esa cedula ya fue registrado");

		} else if (buscarPorEmail(recolector) != null) {
			throw new ElementoRepetidoExcepcion("el recolector con ese email ya fue registrado");

		}
		try {
			entityManager.persist(recolector);
			return recolector;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * @param per
	 * @return
	 */
	private Persona buscarPorEmail(Persona per) {

		try {
			TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.PERSONA_POR_EMAIL, Persona.class);

			query.setParameter("email", per.getEmail());
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.alfite.sis.ejb.AdministradorEJBRemote#insertarFamilia(co.alfite.sis.
	 * entidades.FamiliaPlanta)
	 */
	public FamiliaPlanta insertarFamilia(FamiliaPlanta familia) throws ElementoRepetidoExcepcion {

		if (entityManager.find(FamiliaPlanta.class, familia.getNombre()) != null) {
			throw new ElementoRepetidoExcepcion("la familia con este Nombre ya fue registrada");

		}

		try {
			entityManager.persist(familia);
			return familia;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.alfite.sis.ejb.AdministradorEJBRemote#insertarGenero(co.alfite.sis.
	 * entidades.GeneroPlanta)
	 */
	public GeneroPlanta insertarGenero(GeneroPlanta genero) throws ElementoRepetidoExcepcion {

		if (entityManager.find(GeneroPlanta.class, genero.getNombre()) != null) {
			throw new ElementoRepetidoExcepcion("el genero con este nombre ya fue registrado");

		}

		try {
			entityManager.persist(genero);
			return genero;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.alfite.sis.ejb.AdministradorEJBRemote#insertarEspecie(co.alfite.sis.
	 * entidades.EspeciePlanta)
	 */
	public EspeciePlanta insertarEspecie(EspeciePlanta especie) throws ElementoRepetidoExcepcion {

		if (entityManager.find(EspeciePlanta.class, especie.getNombre()) != null) {
			throw new ElementoRepetidoExcepcion("la especie con este nombre ya fue registrada");

		}
		try {
			entityManager.persist(especie);
			return especie;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.alfite.sis.ejb.AdministradorEJBRemote#insertarRegistro(co.alfite.sis.
	 * entidades.RegistroEspecie)
	 */
	public RegistroEspecie insertarRegistro(RegistroEspecie registro) {

		if (registro.getGenero() != null && registro.getFamilia() != null) {
			if (entityManager.find(GeneroPlanta.class, registro.getGenero().getFamiliaPlanta().getIdFamilia())
					.equals(registro.getFamilia().getIdFamilia())) {

				registro.setEstado(Estado.aprobado);

			} else {
				registro.setEstado(Estado.rechazado);

			}

		} else {
			registro.setEstado(Estado.rechazado);

		}

		try {
			entityManager.persist(registro);
			insertarEspecie(registro.getEspecie());
			return registro;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}

package co.alfite.sis.ejb;

import java.sql.ResultSet;

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
import co.alfite.sis.entidades.ImagenPlanta;
import co.alfite.sis.entidades.MeGustaEspeciePlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.RegistroEspecie.Estado;
import co.alfite.sis.entidades.Resenia;
import co.alfite.sis.entidades.Usuario;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;

/**
 * Session Bean implementation class InsertarEJB
 */
@Stateless
@LocalBean
public class InsertarEJB implements InsertarEJBRemote {

	/**
	 * Default constructor.
	 */

	@PersistenceContext
	EntityManager entityManager;

	public InsertarEJB() {
		// TODO Auto-generated constructor stub
	}

	public MeGustaEspeciePlanta insertarMeGusta(MeGustaEspeciePlanta meGusta) throws ElementoRepetidoExcepcion {
		try {
			entityManager.persist(meGusta);
			return meGusta;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public Resenia insertarResenia(Resenia resenia) {
		try {
			entityManager.persist(resenia);
			return resenia;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public RegistroEspecie insertarRegistroEspecie(RegistroEspecie registro) {

		try {
			entityManager.persist(registro);
			return registro;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

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

	public Usuario insertarUsuario(Usuario nuevoUsuario) throws ElementoRepetidoExcepcion {

		if (entityManager.find(Usuario.class, nuevoUsuario.getIdPersona()) != null) {
			throw new ElementoRepetidoExcepcion("el Usuario con esa cedula ya fue registrado");

		} else if (buscarPorEmail(nuevoUsuario) != null) {
			throw new ElementoRepetidoExcepcion("el Usuario con ese email ya fue registrado");

		}
		try {
			entityManager.persist(nuevoUsuario);
			return nuevoUsuario;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

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
	
	private FamiliaPlanta buscarFamiliaPorNombre(FamiliaPlanta familia) {

		try {
			TypedQuery<FamiliaPlanta> query = entityManager.createNamedQuery(FamiliaPlanta.FAMILIA_NOMBRE, FamiliaPlanta.class);
			
			query.setParameter("var", familia);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public FamiliaPlanta insertarFamilia(FamiliaPlanta familia) throws ElementoRepetidoExcepcion {

		if (buscarFamiliaPorNombre(familia)!= null) {
			throw new ElementoRepetidoExcepcion("La familia con el id ya fue registrado");

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
			throw new ElementoRepetidoExcepcion("El genero con el id ya fue registrado");

		}
		try {
			entityManager.persist(genero);
			return genero;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public EspeciePlanta insertarEspecie(EspeciePlanta especie) throws ElementoRepetidoExcepcion {

		if (entityManager.find(EspeciePlanta.class, especie.getIdEspecie()) != null) {
			throw new ElementoRepetidoExcepcion("El genero con el id ya fue registrado");

		}
		try {
			entityManager.persist(especie);
			return especie;
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

	public ImagenPlanta insertarImagenPlanta(ImagenPlanta y) {

		try {
			entityManager.persist(y);
			return y;
		} catch (Exception e) {
			return null;
		}
	}

	public ImagenPlanta obtenerImagen(int id) {

		return entityManager.find(ImagenPlanta.class, id);
	}

}

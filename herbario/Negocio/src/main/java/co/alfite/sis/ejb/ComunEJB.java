package co.alfite.sis.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.ImagenPlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.RegistroEspecie.Estado;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;

/**
 * Session Bean implementation class ComunEJB
 */
@Stateless
@LocalBean
public class ComunEJB implements ComunEJBRemote {

	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ComunEJB() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.alfite.sis.ejb.ComunEJBRemote#insertarRegistro(co.alfite.sis.entidades.
	 * RegistroEspecie)
	 */
	public RegistroEspecie insertarRegistro(RegistroEspecie registro) {

		registro.setEstado(Estado.enviado);

		try {
			insertarEspecie(registro.getEspecie());
			insertarImagenPlanta(registro.getImagenPlanta());
			entityManager.persist(registro);
			return registro;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 
	 * @param especie
	 * @return
	 * @throws ElementoRepetidoExcepcion
	 */

	private boolean insertarEspecie(EspeciePlanta especie) {

		boolean i = false;
		if (entityManager.find(EspeciePlanta.class, especie.getNombre()) == null) {

			i = true;
			entityManager.persist(especie);
		}

		return i;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.alfite.sis.ejb.ComunEJBRemote#personaPorCredenciales(java.lang.String,
	 * java.lang.String)
	 */
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see co.alfite.sis.ejb.ComunEJBRemote#recuperarContrasenia(java.lang.String)
	 */
	public String recuperarContrasenia(String correo) {

		TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.PERSONA_POR_EMAIL, Persona.class);

		query.setParameter("email", correo);
		try {
			return query.getSingleResult().getPassword();
		} catch (Exception e) {
			return null;
		}

	}

	public EspeciePlanta verDetalleEspecie(int id) {

		TypedQuery<EspeciePlanta> query = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_POR_ID,
				EspeciePlanta.class);

		return query.getSingleResult();
	}

	private ImagenPlanta insertarImagenPlanta(ImagenPlanta img) {

		try {
			entityManager.persist(img);
			return img;
		} catch (Exception e) {
			return null;
		}
	}

}

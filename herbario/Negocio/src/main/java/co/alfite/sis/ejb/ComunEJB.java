package co.alfite.sis.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.alfite.sis.entidades.EspeciePlanta;
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

	public RegistroEspecie insertarRegistro(RegistroEspecie registro) {

		registro.setEstado(Estado.enviado);

		try {
			entityManager.persist(registro);
			insertarEspecie(registro.getEspecie());
			return registro;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

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

}

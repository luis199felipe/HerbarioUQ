package co.alfite.sis.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;

/**
 * Session Bean implementation class EmpleadoEJB
 */
@Stateless
@LocalBean
public class EmpleadoEJB implements EmpleadoEJBRemote {
	
	@PersistenceContext
	EntityManager entityManager;


    /**
     * Default constructor. 
     */
    public EmpleadoEJB() {
    	
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

}

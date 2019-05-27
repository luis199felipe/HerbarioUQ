package co.alfite.sis.ejb;

import javax.ejb.LocalBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.alfite.sis.entidades.MeGustaEspeciePlanta;
import co.alfite.sis.entidades.Resenia;

/**
 * Session Bean implementation class UsuarioEJB
 */
@Stateless
@LocalBean
public class UsuarioEJB implements UsuarioEJBRemote {

	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public UsuarioEJB() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.alfite.sis.ejb.UsuarioEJBRemote#insertarResenia(co.alfite.sis.entidades.
	 * Resenia)
	 */
	public Resenia insertarResenia(Resenia resenia) {

		try {
			entityManager.persist(resenia);
			return resenia;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * co.alfite.sis.ejb.UsuarioEJBRemote#insertarMegusta(co.alfite.sis.entidades.
	 * MeGustaEspeciePlanta)
	 */
	public MeGustaEspeciePlanta insertarMegusta(MeGustaEspeciePlanta meGusta) {

		try {
			entityManager.persist(meGusta);
			return meGusta;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}

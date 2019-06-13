package co.alfite.sis.ejb;

import java.util.List;

import javax.ejb.LocalBean;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.MeGustaEspeciePlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.Resenia;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;

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

	public Resenia insertarResenia(Resenia resenia) {

		try {
			entityManager.persist(resenia);
			return resenia;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public MeGustaEspeciePlanta insertarMegusta(MeGustaEspeciePlanta meGusta) {

		try {
			entityManager.persist(meGusta);
			return meGusta;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean eliminarResenia(Resenia resenia) {
		Resenia e = entityManager.find(Resenia.class, resenia.getIdResenia());
		if (e!= null) {
			try {
				entityManager.remove(e);
				return true;
			} catch (Exception e1) {
				e1.printStackTrace();
				return false;
			}
		} else {

			return false;
		}
	}
	
	public boolean eliminarMeGusta(MeGustaEspeciePlanta meGusta) {
		MeGustaEspeciePlanta e = entityManager.find(MeGustaEspeciePlanta.class, meGusta.getIdMegusta());
		if (e!= null) {
			try {
				entityManager.remove(e);
				return true;
			} catch (Exception e1) {
				e1.printStackTrace();
				return false;
			}
		} else {

			return false;
		}
	}

	public Resenia ActualizarResenia(Resenia r){
		Resenia res = entityManager.find(Resenia.class, r.getIdResenia()); 
		if (res != null) {
			res.setEspecie(r.getEspecie());
			res.setEstado(r.getEstado());
			res.setTexto(r.getTexto());
			res.setUsuario(r.getUsuario());
			
			try {
				entityManager.merge(res);
				return res;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;

		}
	}
	
	public MeGustaEspeciePlanta ActualizarMeGusta (MeGustaEspeciePlanta m){
		MeGustaEspeciePlanta mg = entityManager.find(MeGustaEspeciePlanta.class, m.getIdMegusta()); 
		if (mg!= null) {
			mg.setEspecie(m.getEspecie());
			mg.setFecha(m.getFecha());
			mg.setUsuario(m.getUsuario());
			mg.setEstado(m.getEstado());
			
			try {
				entityManager.merge(mg);
				return mg;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;

		}
	}
	
	public List<Resenia> listarMisResenias(String idPersona) {

		TypedQuery<Resenia> query = entityManager.createNamedQuery(Resenia.RESENIA_USUARIO, Resenia.class);
		query.setParameter("per", idPersona);
		return query.getResultList();
	}
	
	public List<MeGustaEspeciePlanta> listarMisMeGustas(String idPersona) {

		TypedQuery<MeGustaEspeciePlanta> query = entityManager
				.createNamedQuery(MeGustaEspeciePlanta.MEGUSTAESPECIE_USUARIO, MeGustaEspeciePlanta.class);
		query.setParameter("per", idPersona);
		return query.getResultList();
	}

	public List<MeGustaEspeciePlanta> listarMeGustasDeUnaEspecie(String nombreCientifico) {
		TypedQuery<MeGustaEspeciePlanta> query = entityManager
				.createNamedQuery(MeGustaEspeciePlanta.MEGUSTAESPECIE_ESPECIE_NOMBRECIENTIFICO, MeGustaEspeciePlanta.class);
		query.setParameter("nom", nombreCientifico);
		return query.getResultList();
	}

	public List<Resenia> listarReseniasDeUnaEspecie(String nombreCientifico) {
		TypedQuery<Resenia> query = entityManager.createNamedQuery(Resenia.RESENIA_ESPECIE_NOMBRECIENTIFICO, Resenia.class);
		query.setParameter("nom", nombreCientifico);
		return query.getResultList();
	}

	

}

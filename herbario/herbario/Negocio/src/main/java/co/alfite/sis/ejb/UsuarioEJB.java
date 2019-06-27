package co.alfite.sis.ejb;

import java.util.List;

import javax.ejb.LocalBean;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.ImagenPlanta;
import co.alfite.sis.entidades.MeGustaEspeciePlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie.Estado;
import co.alfite.sis.entidades.Resenia;
import co.alfite.sis.entidades.Usuario;
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

			insertarMegusta(meGusta.getImagen());

			return meGusta;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public MeGustaEspeciePlanta eliminarMegusta(MeGustaEspeciePlanta meGusta) {

		try {
			MeGustaEspeciePlanta x = entityManager.find(MeGustaEspeciePlanta.class, meGusta.getIdMegusta());

			eliminarMegusta(meGusta.getImagen());
			entityManager.remove(x);

			return meGusta;
		} catch (

		Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private void insertarMegusta(ImagenPlanta img) {

		// probando la taoria de pipe
		if (img.getNumeroLikes() == null) {

			img.setNumeroLikes(1l);
		} else {
			Long x = img.getNumeroLikes();
			img.setNumeroLikes(x + 1l);
		}

		entityManager.merge(img);
	}

	private void eliminarMegusta(ImagenPlanta img) {

		// probando la taoria de pipe

		img.setNumeroLikes(img.getNumeroLikes() - 1);
		entityManager.merge(img);
	}

	public List<ImagenPlanta> obtenerListaImagenes() {

		TypedQuery<ImagenPlanta> query = entityManager.createNamedQuery(ImagenPlanta.IMAGEN_GET_ALL,
				ImagenPlanta.class);
		return query.getResultList();
	}
	
	public List<ImagenPlanta> obtenerListaImagenesAceptadas() {

		try {
		TypedQuery<ImagenPlanta> query = entityManager.createNamedQuery(ImagenPlanta.IMAGENES_ACEPTADAS,
				ImagenPlanta.class);
		query.setParameter("est", Estado.aprobado);
		return query.getResultList();
		}catch (Exception e) {
			return null;
		}
	}

	/*
	 * reseñas de un usuario
	 */
	public List<Resenia> obtenerListaResenias(String id) {

		TypedQuery<Resenia> query = entityManager.createNamedQuery(Resenia.RESENIA_USUARIO, Resenia.class);
		query.setParameter("esp", id);
		return query.getResultList();
	}
	
	public List<Resenia> listaReseniasPorImagen(Integer id) {

		TypedQuery<Resenia> query = entityManager.createNamedQuery(Resenia.RESENIA_IMAGEN, Resenia.class);

		query.setParameter("id", id);
		return query.getResultList();
	}

	/*
	 * likes de un usuario
	 */
	public List<MeGustaEspeciePlanta> obtenerListaMeGusta(String id) {

		TypedQuery<MeGustaEspeciePlanta> query = entityManager
				.createNamedQuery(MeGustaEspeciePlanta.MEGUSTAESPECIE_USUARIO, MeGustaEspeciePlanta.class);
		query.setParameter("esp", id);
		return query.getResultList();
	}

	public List<ImagenPlanta> obtenerListaImagenesOrdenadasPorLikes() {

		TypedQuery<ImagenPlanta> query = entityManager.createNamedQuery(ImagenPlanta.ESPECIES_POR_LIKES_DES,
				ImagenPlanta.class);
		
		query.setParameter("est", Estado.aprobado);
		return query.getResultList();
	}



	public List<MeGustaEspeciePlanta> obtenerListaLikesPorEspecie(String id) {

		TypedQuery<MeGustaEspeciePlanta> query = entityManager
				.createNamedQuery(MeGustaEspeciePlanta.MEGUSTAESPECIE_ESPECIE, MeGustaEspeciePlanta.class);

		query.setParameter("esp", id);
		return query.getResultList();
	}
}

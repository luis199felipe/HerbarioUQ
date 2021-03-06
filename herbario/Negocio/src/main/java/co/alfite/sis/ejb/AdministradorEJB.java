package co.alfite.sis.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import co.alfite.sis.entidades.Administrador;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.entidades.ImagenPlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.Usuario;
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

	/**
	 * METODOS DE CREAR(INSERTAR)
	 */
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

	/**
	 * inserta empleado
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

	public FamiliaPlanta insertarFamilia(FamiliaPlanta familia) throws ElementoRepetidoExcepcion {
//		if (entityManager.find(FamiliaPlanta.class, familia.getNombre()) != null) {
//			throw new ElementoRepetidoExcepcion("La familia con el id ya fue registrado");
//
//		}
		System.out.println(familia.getNombre());
		try {
			entityManager.persist(familia);
			return familia;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public GeneroPlanta insertarGenero(GeneroPlanta genero) throws ElementoRepetidoExcepcion {

//		if (entityManager.find(GeneroPlanta.class, genero.getNombre()) != null) {
//			throw new ElementoRepetidoExcepcion("El genero con el id ya fue registrado");
//
//		}
		try {
			entityManager.persist(genero);
			return genero;
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

	public boolean insertarEspecie(EspeciePlanta especie) {

		boolean i = false;
		// if (entityManager.find(EspeciePlanta.class, especie.getIdEspecie()) == null)
		// {
		i = true;
		try {
			entityManager.persist(especie);
		} catch (Exception e) {
			i = false;
		}

		// }

		return i;

	}

	/**
	 * METODOS DE MODIFICAR(ACTUALIZAR)
	 */

	public Usuario actualizarUsuario(Usuario us) {
		Usuario u = entityManager.find(Usuario.class, us.getIdPersona());
		if (u != null) {

			try {
				entityManager.merge(us);
				return us;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;

		}
	}

	public Empleado actualizarEmpleado(Empleado em) {
		Empleado ps = entityManager.find(Empleado.class, em.getIdPersona());
		if (ps != null) {
			try {
				entityManager.merge(em);
				return em;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;

		}
	}

	public Recolector actualizarRecolector(Recolector rc) {
		Recolector ps = entityManager.find(Recolector.class, rc.getIdPersona());
		if (ps != null) {
			try {
				entityManager.merge(rc);
				return rc;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;

		}
	}

	public FamiliaPlanta actualizarFamiliaPlanta(FamiliaPlanta f) {
		FamiliaPlanta fam = entityManager.find(FamiliaPlanta.class, f.getIdFamilia());

		if (fam != null) {
			try {
				entityManager.merge(f);
				return f;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	public GeneroPlanta actualizarGeneroPlanta(GeneroPlanta g) {
		GeneroPlanta gen = entityManager.find(GeneroPlanta.class, g.getIdGenero());

		if (gen != null) {
			try {
				entityManager.merge(g);
				return g;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	public ImagenPlanta actualizarImagenPlanta(ImagenPlanta g) {
		ImagenPlanta gen = entityManager.find(ImagenPlanta.class, g.getIdImagen());

		if (gen != null) {

			entityManager.merge(g);
			return gen;

		} else {
			return null;
		}
	}

	public EspeciePlanta actualizarEspeciePlanta(EspeciePlanta esp) {
		EspeciePlanta plant = entityManager.find(EspeciePlanta.class, esp.getIdEspecie());

		if (plant != null) {

			try {
				entityManager.merge(esp);
				return esp;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	public RegistroEspecie actualizarRegistroEspecie(RegistroEspecie registro) {
		RegistroEspecie rs = entityManager.find(RegistroEspecie.class, registro.getIdRegistro());

		if (rs != null) {
			try {
				entityManager.merge(registro);
				return registro;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}

	}

	/**
	 * 
	 * METODOS ELIMINAR
	 */

	public boolean eliminarEspecie(EspeciePlanta esp) {

		EspeciePlanta e = entityManager.find(EspeciePlanta.class, esp.getIdEspecie());
		if (e != null) {
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

	public boolean eliminarGenero(String nombre) {

		try {
			entityManager.remove(buscarGeneroPlanta(nombre));
			return true;
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}

	}

	public boolean eliminarFamilia(String nombre) {

		try {
			entityManager.remove(buscarFamiliaPlanta(nombre));
			return true;
		} catch (Exception e1) {
			e1.printStackTrace();
			return false;
		}

	}

	public boolean inactivarRecolector(Recolector recolector) {

		Recolector r = entityManager.find(Recolector.class, recolector.getIdPersona());
		if (r != null) {
			try {
				r.setEstado(co.alfite.sis.entidades.Persona.Estado.inactivo);
				entityManager.merge(r);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		} else {
			return false;
		}
	}

	public boolean actualizarEstadoPersona(String id, co.alfite.sis.entidades.Persona.Estado est) {

		Persona r = entityManager.find(Persona.class, id);
		if (r != null) {
			try {
				r.setEstado(est);
				entityManager.merge(r);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		} else {
			return false;
		}
	}

	public boolean inactivarEmpleado(Empleado empleado) {

		Empleado e = entityManager.find(Empleado.class, empleado.getIdPersona());
		if (e != null) {
			try {
				e.setEstado(co.alfite.sis.entidades.Persona.Estado.inactivo);
				entityManager.merge(e);
				return true;
			} catch (Exception e1) {
				e1.printStackTrace();
				return false;
			}

		} else {
			return false;
		}
	}

	public boolean inactivarUsuario(Usuario usuario) {

		Usuario u = entityManager.find(Usuario.class, usuario.getIdPersona());
		if (u != null) {
			try {
				u.setEstado(co.alfite.sis.entidades.Persona.Estado.inactivo);
				entityManager.merge(u);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		} else {
			return false;
		}
	}

	/**
	 * 
	 * METODOS LISTAR
	 */

	public List<Usuario> listarUsuarios() {
		TypedQuery<Usuario> query = entityManager.createNamedQuery(Usuario.USUARIO_GET_ALL, Usuario.class);
		return query.getResultList();
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

	public List<FamiliaPlanta> listarFamilias() {

		TypedQuery<FamiliaPlanta> query = entityManager.createNamedQuery(FamiliaPlanta.FAMILIA_GET_ALL,
				FamiliaPlanta.class);
		return query.getResultList();
	}

	public List<GeneroPlanta> listarGeneros() {

		TypedQuery<GeneroPlanta> query = entityManager.createNamedQuery(GeneroPlanta.GENERO_GET_ALL,
				GeneroPlanta.class);
		return query.getResultList();
	}

	public List<EspeciePlanta> listarEspecies() {

		TypedQuery<EspeciePlanta> query = entityManager.createNamedQuery(EspeciePlanta.ESPECIE_GET_ALL,
				EspeciePlanta.class);
		return query.getResultList();
	}

	public List<EspeciePlanta> listarEspeciesPorFamilia(String nombre) {

		TypedQuery<EspeciePlanta> query = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_FAMILIA_NOMBRE,
				EspeciePlanta.class);
		query.setParameter("fam", nombre);
		return query.getResultList();
	}

	public List<EspeciePlanta> listarEspeciesPorGenero(String nombre) {

		TypedQuery<EspeciePlanta> query = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_GENERO_NOMBRE,
				EspeciePlanta.class);
		query.setParameter("gen", nombre);
		return query.getResultList();
	}

	public List<EspeciePlanta> listarEspeciesPorEstado(Estado estado) {

		TypedQuery<EspeciePlanta> query = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_ESTADO,
				EspeciePlanta.class);
		query.setParameter("est", estado);
		return query.getResultList();
	}

	public List<RegistroEspecie> listarRegsitrosPorEstado(Estado estado) {
		try {
			TypedQuery<RegistroEspecie> query = entityManager.createNamedQuery(RegistroEspecie.REGISTRO_POR_ESTADO,
					RegistroEspecie.class);
			query.setParameter("est", estado);
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}

	}

	public List<RegistroEspecie> listarRegistrosRecolector(String idRecolector) {
		try {
			TypedQuery<RegistroEspecie> query = entityManager.createNamedQuery(RegistroEspecie.REGISTRO_RECOLECTOR_ALL,
					RegistroEspecie.class);
			query.setParameter("id", idRecolector);
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}

	}

	public List<RegistroEspecie> listarRegistrosAcetpadosRecolector(String idRecolector) {
		try {
			TypedQuery<RegistroEspecie> query = entityManager
					.createNamedQuery(RegistroEspecie.REGISTRO_RECOLECTOR_ACEPTADO, RegistroEspecie.class);

			query.setParameter("est", Estado.aprobado);
			query.setParameter("id", idRecolector);
			return query.getResultList();
		} catch (Exception e) {
			return null;
		}

	}

	public List<RegistroEspecie> listarRegistrosRecolectorAG(String idRecolector, String idGen) {
		try {
			TypedQuery<RegistroEspecie> query = entityManager
					.createNamedQuery(RegistroEspecie.REGISTRO_RECOLECTOR_ACEPTADO, RegistroEspecie.class);

			query.setParameter("est", Estado.aprobado);
			query.setParameter("id", idRecolector);
			query.setParameter("idGen", idGen);

			return query.getResultList();
		} catch (Exception e) {
			return null;
		}
	}

	public List<RegistroEspecie> listarRegistrosRecolectorAF(String idRecolector, String idFam) {
		try {
			TypedQuery<RegistroEspecie> query = entityManager
					.createNamedQuery(RegistroEspecie.REGISTRO_RECOLECTOR_ACEPTADO, RegistroEspecie.class);

			query.setParameter("est", Estado.aprobado);
			query.setParameter("id", idRecolector);
			query.setParameter("idFam", idFam);

			return query.getResultList();
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * METODOS BUSCAR(CONSULTA)
	 */
	public Persona buscarPersona(String idPersona) {
		try {
			TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.PERSONA_POR_ID, Persona.class);
			query.setParameter("id", idPersona);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	public Usuario buscarUsuario(String idPersona) {
		try {
			TypedQuery<Usuario> query = entityManager.createNamedQuery(Usuario.USUARIO_POR_ID, Usuario.class);
			query.setParameter("id", idPersona);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	public Empleado buscarEmpleado(String idPersona) {
		try {
			TypedQuery<Empleado> query = entityManager.createNamedQuery(Empleado.EMPLEADO_POR_ID, Empleado.class);
			query.setParameter("id", idPersona);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	public Recolector buscarRecolector(String idPersona) {
		try {
			TypedQuery<Recolector> query = entityManager.createNamedQuery(Recolector.RECOLECTOR_POR_ID,
					Recolector.class);
			query.setParameter("id", idPersona);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	public FamiliaPlanta buscarFamiliaPlanta(String nombre) {
		try {
			TypedQuery<FamiliaPlanta> query = entityManager.createNamedQuery(FamiliaPlanta.FAMILIA_POR_NOMBRE,
					FamiliaPlanta.class);
			query.setParameter("nom", nombre);
			FamiliaPlanta f = query.getSingleResult();
			return f;
		} catch (Exception e) {
			return null;
		}

	}

	public GeneroPlanta buscarGeneroPlanta(String nombre) {
		try {
			TypedQuery<GeneroPlanta> query = entityManager.createNamedQuery(GeneroPlanta.GENERO_POR_NOMBRE,
					GeneroPlanta.class);
			query.setParameter("nom", nombre);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}

	}

	public EspeciePlanta buscarEspeciePlanta(String nombre) {
		try {
			TypedQuery<EspeciePlanta> query = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_POR_NOMBRE,
					EspeciePlanta.class);
			query.setParameter("nomCien", nombre);
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	// OTROS METODOS
	public void validarRegistro(int id, Estado est) {
		try {
			TypedQuery<RegistroEspecie> query = entityManager.createNamedQuery(RegistroEspecie.REGISTRO_POR_ID,
					RegistroEspecie.class);

			query.setParameter("id", id);

			RegistroEspecie x = query.getSingleResult();
			x.setEstado(est);
			actualizarRegistroEspecie(x);

		} catch (Exception e) {

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

	public RegistroEspecie insertarRegistro(RegistroEspecie registro) {

		registro.setEstado(Estado.enviado);

		try {
			// deberia insertar la imagen hasta que se valide el registro
			insertarImagenPlanta(registro.getImagenPlanta());
			entityManager.persist(registro);
			return registro;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

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
		try {
			TypedQuery<EspeciePlanta> query = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_POR_ID,
					EspeciePlanta.class);

			return query.getSingleResult();

		} catch (Exception e) {
			return null;
		}
	}

	public ImagenPlanta insertarImagenPlanta(ImagenPlanta img) {

		try {
			entityManager.persist(img);
			return img;
		} catch (Exception e) {
			return null;
		}
	}

	public ImagenPlanta buscarImagenPlanta(Integer id) {

		try {
			TypedQuery<ImagenPlanta> query = entityManager.createNamedQuery(ImagenPlanta.IMAGEN_POR_ID,
					ImagenPlanta.class);
			query.setParameter("id", id);

			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

}

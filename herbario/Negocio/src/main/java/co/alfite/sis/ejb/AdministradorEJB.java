package co.alfite.sis.ejb;

import java.util.List;

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

		if (entityManager.find(FamiliaPlanta.class, familia.getIdFamilia()) != null) {
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

	/**
	 * METODOS DE MODIFICAR(ACTUALIZAR)
	 */

	public Empleado actualizarEmpleado(Empleado em) {
		Empleado ps = entityManager.find(Empleado.class, em.getIdPersona());
		if (ps != null) {
			ps.setEmail(em.getEmail());
			ps.setEstado(em.getEstado());
			ps.setFechaNacimiento(em.getFechaNacimiento());
			ps.setNombre(em.getNombre());
			ps.setPassword(em.getPassword());
			ps.setTelefono(em.getTelefono());

			try {
				entityManager.merge(ps);
				return ps;
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
			ps.setEmail(rc.getEmail());
			ps.setEstado(rc.getEstado());
			ps.setFechaNacimiento(rc.getFechaNacimiento());
			ps.setNombre(rc.getNombre());
			ps.setPassword(rc.getPassword());
			ps.setTelefono(rc.getTelefono());

			try {
				entityManager.merge(ps);
				return ps;
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
			fam.setGeneros(f.getGeneros());
			fam.setNombre(f.getNombre());
			try {
				entityManager.merge(fam);
				return fam;
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
			gen.setEspecies(g.getEspecies());
			gen.setFamiliaPlanta(g.getFamiliaPlanta());
			gen.setNombre(g.getNombre());
			try {
				entityManager.merge(gen);
				return gen;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}
	}

	public EspeciePlanta actualizarEspeciePlanta(EspeciePlanta esp) {
		EspeciePlanta plant = entityManager.find(EspeciePlanta.class, esp.getIdEspecie());

		if (plant != null) {
			// plant.setCantidad(esp.getCantidad());
			plant.setGeneroPlanta(esp.getGeneroPlanta());
			// plant.setImagenes(esp.getImagenes());
			// plant.setMegustas(esp.get());
			plant.setNombre(esp.getNombre());
			plant.setNombreCientifico(esp.getNombreCientifico());
			plant.setResenias(esp.getResenias());

			try {
				entityManager.merge(plant);
				return plant;
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
			rs.setEspecie(registro.getEspecie());
			rs.setEstado(registro.getEstado());
			rs.setFecha(registro.getFecha());
			rs.setMensaje(registro.getMensaje());

			try {
				entityManager.merge(rs);
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

	public boolean eliminarGenero(GeneroPlanta gen) {

		EspeciePlanta e = entityManager.find(EspeciePlanta.class, gen.getIdGenero());
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

	public boolean eliminarFamilia(FamiliaPlanta fam) {

		EspeciePlanta e = entityManager.find(EspeciePlanta.class, fam.getIdFamilia());
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

	
	//OTROS METODOS
	public void validarRegistro(int id, Estado est) {

		TypedQuery<RegistroEspecie> query = entityManager.createNamedQuery(RegistroEspecie.REGISTRO_POR_ID,
				RegistroEspecie.class);

		query.setParameter("id", id);
		actualizarRegistroEspecie(query.getSingleResult());

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



}

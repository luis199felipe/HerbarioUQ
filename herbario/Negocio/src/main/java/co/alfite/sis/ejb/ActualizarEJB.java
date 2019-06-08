package co.alfite.sis.ejb;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.alfite.sis.entidades.Administrador;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.RegistroEspecie.Estado;
import co.alfite.sis.entidades.Usuario;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;

/**
 * Session Bean implementation class ActualizarEJB
 */
@Stateless
@LocalBean
public class ActualizarEJB implements ActualizarEJBRemote {

	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ActualizarEJB() {
		
	}
	
	
	public EspeciePlanta ActualizarEspeciePlanta(EspeciePlanta esp) {
		EspeciePlanta  plant = entityManager.find(EspeciePlanta.class, esp.getIdEspecie());
		
		if (plant!=null) {
			plant.setCantidad(esp.getCantidad());
			plant.setGeneroPlanta(esp.getGeneroPlanta());
			plant.setImagenes(esp.getImagenes());
			//plant.setMegustas(esp.get());
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
	

	public RegistroEspecie ActualizarRegistroEspecie(RegistroEspecie registro) {
		RegistroEspecie rs = entityManager.find(RegistroEspecie.class, registro.getIdRegistro());
		
		if (rs!=null) {
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
	
	@Override
	public Recolector ActualizarRecolector(Recolector rc){
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


	@Override
	public Administrador ActualizarAdministrador(Administrador ad){
		Administrador ps = entityManager.find(Administrador.class, ad.getIdPersona()); 
		if (ps != null) {
			ps.setEmail(ad.getEmail());
			ps.setEstado(ad.getEstado());
			ps.setFechaNacimiento(ad.getFechaNacimiento());
			ps.setNombre(ad.getNombre());
			ps.setPassword(ad.getPassword());
			ps.setTelefono(ad.getTelefono());
			
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
	
	@Override
	public Empleado ActualizarEmpleado(Empleado em){
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

	@Override
	public Usuario ActualizarUsuario(Usuario us){
		Usuario u = entityManager.find(Usuario.class, us.getIdPersona()); 
		if (u != null) {
			u.setEmail(us.getEmail());
			u.setEstado(us.getEstado());
			u.setFechaNacimiento(us.getFechaNacimiento());
			u.setNombre(us.getNombre());
			u.setPassword(us.getPassword());
			u.setTelefono(us.getTelefono());
			
			try {
				entityManager.merge(u);
				return u;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;

		}
	}




}

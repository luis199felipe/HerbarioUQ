package co.alfite.sis.modelo;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.alfite.sis.ejb.ActualizarEJBRemote;
import co.alfite.sis.ejb.InsertarEJBRemote;
import co.alfite.sis.entidades.Administrador;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.entidades.ImagenPlanta;
import co.alfite.sis.entidades.MeGustaEspeciePlanta;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.Resenia;
import co.alfite.sis.entidades.Usuario;

public class ActualizarDelegado {

	/**
	 * instancia que representa el ejb remoto de administrador
	 */
	private ActualizarEJBRemote ActualizarEJB;
	/**
	 * permite manejar una unica instancia para le manejo de delegados
	 */
	public static ActualizarDelegado ActualizarDelegado = instancia();

	/**
	 * constructor para conectar con la capa de negocio
	 */
	private ActualizarDelegado() {
		try {
			ActualizarEJB = (ActualizarEJBRemote) new InitialContext().lookup(ActualizarEJBRemote.JNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permite devolver una unica instancia de delegado
	 * 
	 * @return instancia unica del delegado
	 */
	private static ActualizarDelegado instancia() {

		if (ActualizarDelegado == null) {
			ActualizarDelegado = new ActualizarDelegado();
			return ActualizarDelegado;
		}
		return ActualizarDelegado;
	}

	
	
	/**
	 * pemite actualizar un empleado
	 * 
	 * @param empleado empleado a agregar
	 * @return devuelve true si el empleado fue actualizado
	 */
	public boolean ActualizarEmpleado(Empleado empleado) {
		try {
			return ActualizarEJB.ActualizarEmpleado(empleado) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * pemite actualizar un administrador
	 * 
	 * @param empleado empleado a agregar
	 * @return devuelve true si el empleado fue actualizado
	 */
	public boolean ActualizarAdministrador(Administrador administrador) {
		try {
			return ActualizarEJB.ActualizarAdministrador(administrador) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * pemite actualizar un Recolector
	 * 
	 * @param empleado empleado a agregar
	 * @return devuelve true si el empleado fue actualizado
	 */
	public boolean ActualizarRecolector(Recolector recolector) {
		try {
			return ActualizarEJB.ActualizarRecolector(recolector) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * pemite actualizar un Usuario
	 * 
	 * @param empleado Usuario  a actualizar
	 * @return devuelve true si el empleado fue actualizado
	 */
	public boolean ActualizarUsuario(Usuario usuario) {
		try {
			return ActualizarEJB.ActualizarUsuario(usuario) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * pemite actualizar una Especie de planta
	 * 
	 * @param EspeciePlanta
	 * @return devuelve true si la EspeciePlanta fue eliminada
	 */
	public boolean ActualizarEspeciePlanta(EspeciePlanta esp) {
		try {
			return ActualizarEJB.ActualizarEspeciePlanta(esp) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Pemite actualizar una Familia planta
	 * 
	 * @param FamiliaPlanta
	 * @return devuelve true si la Familia Planta fue eliminada
	 */
	public boolean ActualizarFamiliaPlanta(FamiliaPlanta esp) {
		try {
			return ActualizarEJB.ActualizarFamiliaPlanta(esp) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Pemite actualizar una Genero deplanta
	 * 
	 * @param GeneroPlanta
	 * @return devuelve true si el Genero Planta fue actualizado
	 */
	public boolean ActualizarGeneroPlanta(GeneroPlanta esp) {
		try {
			return ActualizarEJB.ActualizarGeneroPlanta(esp) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * Pemite actualizar una imagen de planta
	 * 
	 * @param ImagenPlanta
	 * @return devuelve true si la imagen Planta fue actualizado
	 */
	public boolean ActualizarImagenPlanta(ImagenPlanta esp) {
		try {
			return ActualizarEJB.ActualizarImagenPlanta(esp) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	

	/**
	 * Pemite actualizar Resenia de planta
	 * 
	 * @param ImagenPlanta
	 * @return devuelve true si la Resenia fue actualizada
	 */
	public boolean ActualizarResenia(Resenia  res) {
		try {
			return ActualizarEJB.ActualizarResenia(res) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * Pemite actualizar MeGusta de planta
	 * 
	 * @param ImagenPlanta
	 * @return devuelve true el me gusta fue actualizado
	 */
	public boolean ActualizarMeGustaEspeciePlanta (MeGustaEspeciePlanta  mg) {
		try {
			return ActualizarEJB.ActualizarMeGustaEspeciePlanta (mg) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	
}

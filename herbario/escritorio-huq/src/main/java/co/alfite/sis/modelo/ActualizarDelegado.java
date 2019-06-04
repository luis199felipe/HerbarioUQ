package co.alfite.sis.modelo;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.alfite.sis.ejb.ActualizarEJBRemote;
import co.alfite.sis.ejb.InsertarEJBRemote;
import co.alfite.sis.entidades.Administrador;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.Recolector;

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
	 * @return devuelve true si el empleado fue eliminado
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
	 * @return devuelve true si el empleado fue eliminado
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
	 * @return devuelve true si el empleado fue eliminado
	 */
	public boolean ActualizarRecolector(Recolector recolector) {
		try {
			return ActualizarEJB.ActualizarRecolector(recolector) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	
	
}

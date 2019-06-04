package co.alfite.sis.modelo;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import co.alfite.sis.ejb.InsertarEJBRemote;
import co.alfite.sis.entidades.Empleado;


public class InsertarDelegado {

	/**
	 * instancia que representa el ejb remoto de administrador
	 */
	private InsertarEJBRemote insertarEJB;
	/**
	 * permite manejar una unica instancia para le manejo de delegados
	 */
	public static InsertarDelegado insertarDelegado = instancia();

	/**
	 * constructor para conectar con la capa de negocio
	 */
	private InsertarDelegado() {
		try {
			insertarEJB = (InsertarEJBRemote) new InitialContext().lookup(InsertarEJBRemote.JNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permite devolver una unica instancia de delegado
	 * 
	 * @return instancia unica del delegado
	 */
	private static InsertarDelegado instancia() {

		if (insertarDelegado == null) {
			insertarDelegado = new InsertarDelegado();
			return insertarDelegado;
		}
		return insertarDelegado;
	}

	/**
	 * pemite registar un nuevo empleado
	 * 
	 * @param empleado empleado a agregar
	 * @return devuelve true si el empleado fue eliminado
	 */
	public boolean registrarTrabajador(Empleado empleado) {
		try {
			return insertarEJB.insertarEmpleado(empleado) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}

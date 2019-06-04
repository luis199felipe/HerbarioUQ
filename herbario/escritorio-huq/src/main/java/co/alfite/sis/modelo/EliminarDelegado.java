package co.alfite.sis.modelo;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.alfite.sis.ejb.EliminarEJBRemote;
import co.alfite.sis.ejb.InsertarEJBRemote;
import co.alfite.sis.entidades.Empleado;

public class EliminarDelegado {
	/**
	 * instancia que representa el ejb remoto de administrador
	 */
	private EliminarEJBRemote insertarEJB;
	/**
	 * permite manejar una unica instancia para le manejo de delegados
	 */
	public static EliminarDelegado eliminarDelegado = instancia();

	/**
	 * constructor para conectar con la capa de negocio
	 */
	private EliminarDelegado() {
		try {
			insertarEJB = (EliminarEJBRemote) new InitialContext().lookup(EliminarEJBRemote.JNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permite devolver una unica instancia de delegado
	 * 
	 * @return instancia unica del delegado
	 */
	private static EliminarDelegado instancia() {

		if (eliminarDelegado == null) {
			eliminarDelegado = new EliminarDelegado();
			return eliminarDelegado;
		}
		return eliminarDelegado;
	}

	

}

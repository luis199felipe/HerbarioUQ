package co.alfite.sis.modelo;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.alfite.sis.ejb.BuscarEJBRemote;
import co.alfite.sis.entidades.Persona;

public class BuscarDelegado {

	/**
	 */
	private BuscarEJBRemote buscarEJB;
	/**
	 */
	public static BuscarDelegado buscarDelegado = instancia();

	/**
	 */
	private BuscarDelegado() {
		try {
			buscarEJB = (BuscarEJBRemote) new InitialContext().lookup(BuscarEJBRemote.JNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permite devolver una unica instancia de delegado
	 * 
	 * @return instancia unica del delegado
	 */
	private static BuscarDelegado instancia() {

		if (buscarDelegado == null) {
			buscarDelegado = new BuscarDelegado();
			return buscarDelegado;
		}
		return buscarDelegado;
	}
	
	
	public Persona personaPorCredenciales(String correo, String password) {
		
		return buscarEJB.personaPorCredenciales(correo, password);
	}
}

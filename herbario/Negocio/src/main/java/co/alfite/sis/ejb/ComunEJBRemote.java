package co.alfite.sis.ejb;

import javax.ejb.Remote;

import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.RegistroEspecie;

@Remote
public interface ComunEJBRemote {
	/**
	 * 
	 * @param registro
	 * @return
	 */
	RegistroEspecie insertarRegistro(RegistroEspecie registro);

	/**
	 * 
	 * @param correo
	 * @param password
	 * @return
	 */
	Persona personaPorCredenciales(String correo, String password);

	/**
	 * 
	 * @param correo
	 * @return
	 */
	String recuperarContrasenia(String correo);

	/**
	 * 
	 * @param id
	 * @return
	 */
	EspeciePlanta verDetalleEspecie(int id);
}

package co.alfite.sis.ejb;

import co.alfite.sis.entidades.Recolector;

import co.alfite.sis.entidades.RegistroEspecie;
import javax.ejb.Remote;
import co.alfite.sis.entidades.Empleado;


@Remote
public interface EliminarEJBRemote {
	
	String JNDI = "java:global/ear-huq/Negocio/EliminarEJB!co.alfite.sis.ejb.EliminarEJBRemote";
	

	/**
	 * 
	 * @param recolector
	 * @return
	 */
	boolean eliminarRecolector(Recolector recolector);

	/**
	 * 
	 * @param registro
	 * @return
	 */
	boolean  eliminarRegistro(RegistroEspecie registro);
	
	/**
	 * 
	 * @param registro
	 * @return
	 */
	boolean  eliminarEmpleado(Empleado empleado);

}

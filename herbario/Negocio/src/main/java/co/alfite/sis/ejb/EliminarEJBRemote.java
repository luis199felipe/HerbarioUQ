package co.alfite.sis.ejb;

import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;
import javax.ejb.Remote;

import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;

@Remote
public interface EliminarEJBRemote {
	
	String JNDI = "java:global/ear-huq/Negocio/InsertarEJB!co.alfite.sis.ejb.eliminarEJBRemote";
	

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

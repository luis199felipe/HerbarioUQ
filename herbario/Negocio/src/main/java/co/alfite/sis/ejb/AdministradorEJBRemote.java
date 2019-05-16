package co.alfite.sis.ejb;

import javax.ejb.Remote;

import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;

/**
 * 
 */
@Remote
public interface AdministradorEJBRemote {


	/**
	 * permite registrar un elemento en la base de datos
	 * @param empleado
	 * @return
	 * @throws ElementoRepetidoExcepcion
	 */
	Empleado insertarEmpleado(Empleado empleado) throws ElementoRepetidoExcepcion;

}

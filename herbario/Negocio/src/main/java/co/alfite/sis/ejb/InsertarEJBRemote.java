package co.alfite.sis.ejb;

import javax.ejb.Remote;

import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;

@Remote
public interface InsertarEJBRemote {

	String JNDI = "java:global/ear-huq/Negocio/InsertarEJB!co.alfite.sis.ejb.InsertarEJBRemote";

	Empleado insertarEmpleado(Empleado empleado) throws ElementoRepetidoExcepcion;

	/**
	 * 
	 * @param recolector
	 * @return
	 * @throws ElementoRepetidoExcepcion
	 */
	Recolector insertarRecolector(Recolector recolector) throws ElementoRepetidoExcepcion;

	/**
	 * 
	 * @param registro
	 * @return
	 */
	RegistroEspecie insertarRegistro(RegistroEspecie registro);
}

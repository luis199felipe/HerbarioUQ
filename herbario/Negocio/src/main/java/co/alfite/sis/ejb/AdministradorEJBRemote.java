package co.alfite.sis.ejb;

import javax.ejb.Remote;

import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;

/**
 * 
 */
@Remote
public interface AdministradorEJBRemote {

	
	String JNDI = "java:global/ear-huq/Negocio/AdministradorEJB!co.alfite.sis.ejb.AdministradorEJBRemote";
	/**
	 * permite registrar un elemento en la base de datos
	 * 
	 * @param empleado
	 * @return
	 * @throws ElementoRepetidoExcepcion
	 */
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

	/**
	 * 
	 * @param familia
	 * @return
	 * @throws ElementoRepetidoExcepcion
	 */

	FamiliaPlanta insertarFamilia(FamiliaPlanta familia) throws ElementoRepetidoExcepcion;

	/**
	 * 
	 * @param genero
	 * @return
	 * @throws ElementoRepetidoExcepcion
	 */
	GeneroPlanta insertarGenero(GeneroPlanta genero) throws ElementoRepetidoExcepcion;

	/**
	 * 
	 * @param especie
	 * @return
	 * @throws ElementoRepetidoExcepcion
	 */

	EspeciePlanta insertarEspecie(EspeciePlanta especie) throws ElementoRepetidoExcepcion;

}

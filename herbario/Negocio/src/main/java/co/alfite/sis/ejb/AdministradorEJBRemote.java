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
	
	Empleado insertarEmpleado(Empleado empleado) throws ElementoRepetidoExcepcion;

	Recolector insertarRecolector(Recolector recolector) throws ElementoRepetidoExcepcion;

	FamiliaPlanta insertarFamilia(FamiliaPlanta familia) throws ElementoRepetidoExcepcion;

	GeneroPlanta insertarGenero(GeneroPlanta genero) throws ElementoRepetidoExcepcion;

	EspeciePlanta insertarEspecie(EspeciePlanta especie) throws ElementoRepetidoExcepcion;
	
	
	
	Empleado ActualizarEmpleado(Empleado empleado);

	Recolector ActualizarRecolector(Recolector recolector);

	FamiliaPlanta ActualizarFamiliaPlanta(FamiliaPlanta f);

	GeneroPlanta ActualizarGeneroPlanta(GeneroPlanta g);

	EspeciePlanta ActualizarEspeciePlanta(EspeciePlanta esp);
	
	
	


}

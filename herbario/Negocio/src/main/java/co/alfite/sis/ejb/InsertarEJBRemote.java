package co.alfite.sis.ejb;

import javax.ejb.Remote;

import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.entidades.MeGustaEspeciePlanta;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.Resenia;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;

@Remote
public interface InsertarEJBRemote {

	String JNDI = "java:global/ear-huq/Negocio/EliminarEJB!co.alfite.sis.ejb.EliminarEJBRemote";

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
	RegistroEspecie insertarRegistroEspecie(RegistroEspecie registro);

	FamiliaPlanta insertarFamilia(FamiliaPlanta familia) throws ElementoRepetidoExcepcion;

	GeneroPlanta insertarGenero(GeneroPlanta genero) throws ElementoRepetidoExcepcion;

	EspeciePlanta insertarEspecie(EspeciePlanta especie) throws ElementoRepetidoExcepcion;

	MeGustaEspeciePlanta insertarMeGusta(MeGustaEspeciePlanta meGusta) throws ElementoRepetidoExcepcion;

	Resenia insertarResenia(Resenia resenia);
}

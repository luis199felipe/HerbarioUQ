package co.alfite.sis.ejb;

import co.alfite.sis.entidades.Recolector;

import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.Usuario;

import javax.ejb.Remote;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;

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
	boolean eliminarRegistro(RegistroEspecie registro);

	boolean eliminarEmpleado(Empleado empleado);

	boolean eliminarEspecie(EspeciePlanta esp);

	boolean eliminarGenero(GeneroPlanta gen);

	boolean eliminarFamilia(FamiliaPlanta fam);

	boolean inactivarRecolector(Recolector recolector);

	boolean inactivarEmpleado(Empleado empleado);

	boolean inactivarUsuario(Usuario usuario);

}

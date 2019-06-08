package co.alfite.sis.ejb;

import javax.ejb.Remote;

import co.alfite.sis.entidades.Administrador;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.Usuario;

@Remote
public interface ActualizarEJBRemote {

	String JNDI =  "java:global/ear-huq/Negocio/ActualizarEJB!co.alfite.sis.ejb.ActualizarEJBRemote";

	Empleado ActualizarEmpleado(Empleado empleado);

	Recolector ActualizarRecolector(Recolector recolector);

	Object ActualizarAdministrador(Administrador administrador);

	Usuario ActualizarUsuario(Usuario us);

}
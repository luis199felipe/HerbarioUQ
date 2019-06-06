package co.alfite.sis.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;

@Remote
public interface ListarEJBRemote {
	
	String JNDI = "java:global/ear-huq/Negocio/ListarEJB!co.alfite.sis.ejb.ListarEJBRemote";

	List<Empleado> listarEmpleados();

	List<Recolector> listarRecolectores();

	List<RegistroEspecie> listarRegistros();
}

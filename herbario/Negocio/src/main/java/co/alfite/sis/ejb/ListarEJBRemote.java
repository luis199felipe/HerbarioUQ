package co.alfite.sis.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.alfite.sis.entidades.Administrador;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.entidades.MeGustaEspeciePlanta;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.Resenia;
import co.alfite.sis.entidades.Usuario;

@Remote
public interface ListarEJBRemote {

	String JNDI = "java:global/ear-huq/Negocio/ListarEJB!co.alfite.sis.ejb.ListarEJBRemote";

	List<Usuario> listarUsuarios();
	List<Administrador> listarAdministradores();
	List<Empleado> listarEmpleados();
	List<Recolector> listarRecolectores();
	List<RegistroEspecie> listarRegistros();
	List<FamiliaPlanta> listarFamilias();
	List<GeneroPlanta> listarGeneros();
	List<EspeciePlanta> listarEspecies();
	List<MeGustaEspeciePlanta> listarMeGustas();
	List<Resenia> listarResenias();
	
	
}

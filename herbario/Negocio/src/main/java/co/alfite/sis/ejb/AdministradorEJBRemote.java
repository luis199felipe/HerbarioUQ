package co.alfite.sis.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.alfite.sis.entidades.Administrador;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.Usuario;
import co.alfite.sis.entidades.RegistroEspecie.Estado;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;

/**
 * 
 */
@Remote
public interface AdministradorEJBRemote {

	String JNDI = "java:global/ear-huq/Negocio/AdministradorEJB!co.alfite.sis.ejb.AdministradorEJBRemote";
	
	Usuario insertarUsuario(Usuario nuevoUsuario)throws ElementoRepetidoExcepcion;

	Empleado insertarEmpleado(Empleado empleado) throws ElementoRepetidoExcepcion;

	Recolector insertarRecolector(Recolector recolector) throws ElementoRepetidoExcepcion;

	FamiliaPlanta insertarFamilia(FamiliaPlanta familia) throws ElementoRepetidoExcepcion;

	GeneroPlanta insertarGenero(GeneroPlanta genero) throws ElementoRepetidoExcepcion;

	EspeciePlanta insertarEspecie(EspeciePlanta especie) throws ElementoRepetidoExcepcion;
	
	
	Usuario actualizarUsuario(Usuario us);

	Empleado actualizarEmpleado(Empleado empleado);

	Recolector actualizarRecolector(Recolector recolector);

	FamiliaPlanta actualizarFamiliaPlanta(FamiliaPlanta f);

	GeneroPlanta actualizarGeneroPlanta(GeneroPlanta g);

	EspeciePlanta actualizarEspeciePlanta(EspeciePlanta esp);

	
	boolean inactivarUsuario(Usuario usuario);
	
	boolean inactivarEmpleado(Empleado empleado);
	
	boolean inactivarRecolector(Recolector recolector);

	boolean eliminarFamilia(FamiliaPlanta fam);
	
	boolean eliminarGenero(GeneroPlanta gen);

	boolean eliminarEspecie(EspeciePlanta esp);
	
	
	Usuario buscarUsuario(String idPersona);

	Empleado buscarEmpleado(String idPersona);

	Recolector buscarRecolector(String idPersona);

	FamiliaPlanta buscarFamiliaPlanta(String nombre);

	GeneroPlanta buscarGeneroPlanta(String nombre);

	EspeciePlanta buscarEspeciePlanta(String nombreCientifico);

	
	
	List<Usuario> listarUsuarios();
	
	List<Empleado> listarEmpleados();
	
	List<Recolector> listarRecolectores();
	
	List<FamiliaPlanta> listarFamilias();
	
	List<GeneroPlanta> listarGeneros();

	List<EspeciePlanta> listarEspecies();
	
	
	List<RegistroEspecie> listarRegistros();
	
	List<EspeciePlanta> listarEspeciesPorFamilia(String nombre);

	List<EspeciePlanta> listarEspeciesPorGenero(String nombre);

	List<EspeciePlanta> listarEspeciesPorEstado(Estado estado);
	

	void validarRegistro(int id, Estado est);

}

package co.alfite.sis.ejb;

import java.util.List;

import javax.ejb.Remote;

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

	Empleado insertarEmpleado(Empleado empleado) throws ElementoRepetidoExcepcion;

	Recolector insertarRecolector(Recolector recolector) throws ElementoRepetidoExcepcion;

	FamiliaPlanta insertarFamilia(FamiliaPlanta familia) throws ElementoRepetidoExcepcion;

	GeneroPlanta insertarGenero(GeneroPlanta genero) throws ElementoRepetidoExcepcion;

	EspeciePlanta insertarEspecie(EspeciePlanta especie) throws ElementoRepetidoExcepcion;

	Empleado actualizarEmpleado(Empleado empleado);

	Recolector actualizarRecolector(Recolector recolector);

	FamiliaPlanta actualizarFamiliaPlanta(FamiliaPlanta f);

	GeneroPlanta actualizarGeneroPlanta(GeneroPlanta g);

	EspeciePlanta actualizarEspeciePlanta(EspeciePlanta esp);

	boolean eliminarEspecie(EspeciePlanta esp);

	boolean eliminarGenero(GeneroPlanta gen);

	boolean eliminarFamilia(FamiliaPlanta fam);

	boolean inactivarRecolector(Recolector recolector);

	boolean inactivarEmpleado(Empleado empleado);

	boolean inactivarUsuario(Usuario usuario);

	List<EspeciePlanta> listarEspecies();

	List<EspeciePlanta> listarEspeciesPorFamilia(String nombre);

	List<EspeciePlanta> listarEspeciesPorGenero(String nombre);

	List<EspeciePlanta> listarEspeciesPorEstado(Estado estado);

	void validarRegistro(int id, Estado est);

}

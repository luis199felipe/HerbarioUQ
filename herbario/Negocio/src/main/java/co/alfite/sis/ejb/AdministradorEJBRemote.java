package co.alfite.sis.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.alfite.sis.entidades.Administrador;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.entidades.ImagenPlanta;
import co.alfite.sis.entidades.Persona;
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

	Usuario insertarUsuario(Usuario nuevoUsuario) throws ElementoRepetidoExcepcion;

	Empleado insertarEmpleado(Empleado empleado) throws ElementoRepetidoExcepcion;

	Recolector insertarRecolector(Recolector recolector) throws ElementoRepetidoExcepcion;

	FamiliaPlanta insertarFamilia(FamiliaPlanta familia) throws ElementoRepetidoExcepcion;

	GeneroPlanta insertarGenero(GeneroPlanta genero) throws ElementoRepetidoExcepcion;

	// EspeciePlanta insertarEspecie(EspeciePlanta especie) throws
	// ElementoRepetidoExcepcion;

	Usuario actualizarUsuario(Usuario us);

	Empleado actualizarEmpleado(Empleado empleado);

	Recolector actualizarRecolector(Recolector recolector);

	FamiliaPlanta actualizarFamiliaPlanta(FamiliaPlanta f);

	GeneroPlanta actualizarGeneroPlanta(GeneroPlanta g);

	EspeciePlanta actualizarEspeciePlanta(EspeciePlanta esp);

	boolean inactivarUsuario(Usuario usuario);

	boolean inactivarEmpleado(Empleado empleado);

	boolean inactivarRecolector(Recolector recolector);

	boolean actualizarEstadoPersona(String id, co.alfite.sis.entidades.Persona.Estado est);

	boolean eliminarFamilia(String fam);

	boolean eliminarGenero(String gen);

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

	List<RegistroEspecie> listarRegsitrosPorEstado(Estado estado);

	void validarRegistro(int id, Estado est);

	/**
	 * 
	 * @param registro
	 * @return
	 */
	RegistroEspecie insertarRegistro(RegistroEspecie registro);

	/**
	 * 
	 * @param correo
	 * @param password
	 * @return
	 */
	Persona personaPorCredenciales(String correo, String password);

	/**
	 * 
	 * @param correo
	 * @return
	 */
	String recuperarContrasenia(String correo);

	/**
	 * 
	 * @param id
	 * @return
	 */
	EspeciePlanta verDetalleEspecie(int id);

	ImagenPlanta buscarImagenPlanta(Integer id);

	boolean insertarEspecie(EspeciePlanta especie);

	ImagenPlanta actualizarImagenPlanta(ImagenPlanta g);

	ImagenPlanta insertarImagenPlanta(ImagenPlanta img);

	List<RegistroEspecie> listarRegistrosAcetpadosRecolector(String idRecolector);

	List<RegistroEspecie> listarRegistrosRecolectorAG(String idRecolector, String idGen);

	List<RegistroEspecie> listarRegistrosRecolectorAF(String idRecolector, String idFam);

	List<RegistroEspecie> listarRegistrosRecolector(String idRecolector);

}

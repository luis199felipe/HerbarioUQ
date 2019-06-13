/**
 * 
 */
package co.alfite.sis.modelo;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import co.alfite.sis.ejb.AdministradorEJBRemote;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.RegistroEspecie.Estado;
import co.alfite.sis.entidades.Usuario;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;
import co.alfite.sis.modelo.observable.EspecieObservable;
import co.alfite.sis.modelo.observable.FamiliaObservable;
import co.alfite.sis.modelo.observable.GeneroObservable;
import co.alfite.sis.modelo.observable.PersonaObservable;
import co.alfite.sis.modelo.observable.RegistroObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Delegado que permite conectar la capa de negocio con la de presentación
 * 
 * @author EinerZG
 * @version 1.0
 */
public class AdministradorDelegado implements AdministradorEJBRemote {

	/**
	 * instancia que representa el ejb remoto de administrador
	 */
	private AdministradorEJBRemote adminEJB;
	/**
	 * permite manejar una unica instancia para le manejo de delegados
	 */
	public static AdministradorDelegado administradorDelegado = instancia();

	/**
	 * constructor para conectar con la capa de negocio
	 */
	private AdministradorDelegado() {
		try {
			adminEJB = (AdministradorEJBRemote) new InitialContext().lookup(AdministradorEJBRemote.JNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permite devolver una unica instancia de delegado
	 * 
	 * @return instancia unica del delegado
	 */
	private static AdministradorDelegado instancia() {

		if (administradorDelegado == null) {
			administradorDelegado = new AdministradorDelegado();
			return administradorDelegado;
		}
		return administradorDelegado;
	}

	/**
	 * pemite registar un nuevo empleado
	 * 
	 * @param empleado empleado a agregar
	 * @return devuelve true si el empleado fue eliminado
	 * @throws ElementoRepetidoExcepcion
	 */
	public Empleado insertarEmpleado(Empleado empleado) throws ElementoRepetidoExcepcion {

		return adminEJB.insertarEmpleado(empleado);

	}

	public boolean insertarUsusario(Usuario nuevoUsuario) throws ElementoRepetidoExcepcion {
		return adminEJB.insertarUsuario(nuevoUsuario) != null;
	}

	public Recolector insertarRecolector(Recolector recolector) throws ElementoRepetidoExcepcion {
		return adminEJB.insertarRecolector(recolector);

	}

	public RegistroEspecie insertarRegistro(RegistroEspecie registro) {
		return adminEJB.insertarRegistro(registro);
	}

	public Persona personaPorCredenciales(String correo, String password) {

		return adminEJB.personaPorCredenciales(correo, password);
	}

	public List<PersonaObservable> listarUsuariosObservables() {
		List<Usuario> usuarios = listarUsuarios();
		ObservableList<PersonaObservable> usuariosObservables = FXCollections.observableArrayList();

		for (Persona persona : usuarios) {
			usuariosObservables.add(new PersonaObservable(persona));
		}

		return usuariosObservables;

	}

	public List<Usuario> listarUsuarios() {
		return adminEJB.listarUsuarios();
	}

	public List<PersonaObservable> listarEmpleadosObservables() {
		List<Empleado> empleados = listarEmpleados();
		ObservableList<PersonaObservable> empleadosObservables = FXCollections.observableArrayList();

		for (Persona persona : empleados) {
			empleadosObservables.add(new PersonaObservable(persona));
		}
		return empleadosObservables;

	}

	public List<Empleado> listarEmpleados() {
		return adminEJB.listarEmpleados();
	}

	public List<PersonaObservable> listarRecolectoresObservables() {
		List<Recolector> recolectores = listarRecolectores();

		if (recolectores.isEmpty()) {
			System.out.println("esta vacia");
		}
		ObservableList<PersonaObservable> recolectoresObservables = FXCollections.observableArrayList();

		for (Persona persona : recolectores) {
			recolectoresObservables.add(new PersonaObservable(persona));
		}

		return recolectoresObservables;

	}

	public List<Recolector> listarRecolectores() {
		return adminEJB.listarRecolectores();
	}

	public List<RegistroObservable> listarRegistrosObservables() {
		List<RegistroEspecie> registros = listarRegistros();
		ObservableList<RegistroObservable> registrosObservables = FXCollections.observableArrayList();

		for (RegistroEspecie registro : registros) {
			registrosObservables.add(new RegistroObservable(registro));
		}
		return registrosObservables;

	}

	public List<RegistroEspecie> listarRegistros() {
		return adminEJB.listarRegistros();
	}

	public List<FamiliaObservable> listarFamiliasObservables() {
		List<FamiliaPlanta> familias = listarFamilias();
		ObservableList<FamiliaObservable> familiasObservables = FXCollections.observableArrayList();

		for (FamiliaPlanta familia : familias) {
			familiasObservables.add(new FamiliaObservable(familia));
		}
		return familiasObservables;

	}

	public List<FamiliaPlanta> listarFamilias() {
		return adminEJB.listarFamilias();
	}

	public List<GeneroObservable> listarGenerosObservables() {
		List<GeneroPlanta> generos = listarGeneros();
		ObservableList<GeneroObservable> generosObservables = FXCollections.observableArrayList();

		for (GeneroPlanta genero : generos) {
			generosObservables.add(new GeneroObservable(genero));
		}
		return generosObservables;

	}

	public List<GeneroPlanta> listarGeneros() {
		return adminEJB.listarGeneros();
	}

	public List<EspecieObservable> listarEspeciesObservables() {
		List<EspeciePlanta> especies = listarEspecies();
		ObservableList<EspecieObservable> especiesObservables = FXCollections.observableArrayList();

		for (EspeciePlanta especie : especies) {
			especiesObservables.add(new EspecieObservable(especie));
		}
		return especiesObservables;

	}

	public List<EspeciePlanta> listarEspecies() {
		return adminEJB.listarEspecies();
	}

	public List<EspecieObservable> listarEspeciesPorFamiliaObservables(String nombre) {
		List<EspeciePlanta> especies = listarEspeciesPorFamilia(nombre);
		ObservableList<EspecieObservable> especiesObservables = FXCollections.observableArrayList();

		for (EspeciePlanta especie : especies) {
			especiesObservables.add(new EspecieObservable(especie));
		}
		return especiesObservables;

	}

	public List<EspeciePlanta> listarEspeciesPorFamilia(String nombre) {
		return adminEJB.listarEspeciesPorFamilia(nombre);
	}

	public List<EspecieObservable> listarEspeciesPorGeneroObservables(String nombre) {
		List<EspeciePlanta> especies = listarEspeciesPorGenero(nombre);
		ObservableList<EspecieObservable> especiesObservables = FXCollections.observableArrayList();

		for (EspeciePlanta especie : especies) {
			especiesObservables.add(new EspecieObservable(especie));
		}
		return especiesObservables;

	}

	public List<EspeciePlanta> listarEspeciesPorGenero(String nombre) {
		return adminEJB.listarEspeciesPorGenero(nombre);
	}

	public List<EspecieObservable> listarEspeciesPorEstadoObservables(Estado est) {
		List<EspeciePlanta> especies = listarEspeciesPorEstado(est);
		ObservableList<EspecieObservable> especiesObservables = FXCollections.observableArrayList();

		for (EspeciePlanta especie : especies) {
			especiesObservables.add(new EspecieObservable(especie));
		}
		return especiesObservables;

	}

	public List<EspeciePlanta> listarEspeciesPorEstado(Estado est) {
		return adminEJB.listarEspeciesPorEstado(est);
	}

	


	@Override
	public Usuario insertarUsuario(Usuario nuevoUsuario) throws ElementoRepetidoExcepcion {
		// TODO Auto-generated method stub
		return adminEJB.insertarUsuario(nuevoUsuario);
	}

	@Override
	public FamiliaPlanta insertarFamilia(FamiliaPlanta familia) throws ElementoRepetidoExcepcion {
		// TODO Auto-generated method stub
		return adminEJB.insertarFamilia(familia);
	}

	@Override
	public GeneroPlanta insertarGenero(GeneroPlanta genero) throws ElementoRepetidoExcepcion {
		// TODO Auto-generated method stub
		return adminEJB.insertarGenero(genero);
	}

	@Override
	public Usuario actualizarUsuario(Usuario us) {
		// TODO Auto-generated method stub
		return adminEJB.actualizarUsuario(us);
	}

	@Override
	public Empleado actualizarEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		return adminEJB.actualizarEmpleado(empleado);
	}

	@Override
	public Recolector actualizarRecolector(Recolector recolector) {
		// TODO Auto-generated method stub
		return adminEJB.actualizarRecolector(recolector);
	}

	@Override
	public FamiliaPlanta actualizarFamiliaPlanta(FamiliaPlanta f) {
		// TODO Auto-generated method stub
		return adminEJB.actualizarFamiliaPlanta(f);
	}

	@Override
	public GeneroPlanta actualizarGeneroPlanta(GeneroPlanta g) {
		// TODO Auto-generated method stub
		return adminEJB.actualizarGeneroPlanta(g);
	}

	@Override
	public EspeciePlanta actualizarEspeciePlanta(EspeciePlanta esp) {
		// TODO Auto-generated method stub
		return adminEJB.actualizarEspeciePlanta(esp);
	}

	@Override
	public boolean inactivarUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return adminEJB.inactivarUsuario(usuario);
	}

	@Override
	public boolean inactivarEmpleado(Empleado empleado) {
		// TODO Auto-generated method stub
		return adminEJB.inactivarEmpleado(empleado);
	}

	@Override
	public boolean inactivarRecolector(Recolector recolector) {
		// TODO Auto-generated method stub
		return adminEJB.inactivarRecolector(recolector);
	}

	@Override
	public boolean eliminarFamilia(FamiliaPlanta fam) {
		// TODO Auto-generated method stub
		return adminEJB.eliminarFamilia(fam);
	}

	@Override
	public boolean eliminarGenero(GeneroPlanta gen) {
		// TODO Auto-generated method stub
		return adminEJB.eliminarGenero(gen);
	}

	@Override
	public boolean eliminarEspecie(EspeciePlanta esp) {
		// TODO Auto-generated method stub
		return adminEJB.eliminarEspecie(esp);
	}

	@Override
	public Usuario buscarUsuario(String idPersona) {
		// TODO Auto-generated method stub
		return adminEJB.buscarUsuario(idPersona);
	}

	@Override
	public Empleado buscarEmpleado(String idPersona) {
		// TODO Auto-generated method stub
		return adminEJB.buscarEmpleado(idPersona);
	}

	@Override
	public Recolector buscarRecolector(String idPersona) {
		// TODO Auto-generated method stub
		return adminEJB.buscarRecolector(idPersona);
	}

	@Override
	public FamiliaPlanta buscarFamiliaPlanta(String nombre) {
		// TODO Auto-generated method stub
		return  adminEJB.buscarFamiliaPlanta(nombre);
	}

	@Override
	public GeneroPlanta buscarGeneroPlanta(String nombre) {
		// TODO Auto-generated method stub
		return adminEJB.buscarGeneroPlanta(nombre);
	}

	@Override
	public EspeciePlanta buscarEspeciePlanta(String nombreCientifico) {
		// TODO Auto-generated method stub
		return adminEJB.buscarEspeciePlanta(nombreCientifico);
	}

	@Override
	public void validarRegistro(int id, Estado est) {

		adminEJB.validarRegistro(id, est);
	}

	@Override
	public String recuperarContrasenia(String correo) {
		return adminEJB.recuperarContrasenia(correo);

	}

	@Override
	public EspeciePlanta verDetalleEspecie(int id) {
		
		return adminEJB.verDetalleEspecie(id);
	}

}

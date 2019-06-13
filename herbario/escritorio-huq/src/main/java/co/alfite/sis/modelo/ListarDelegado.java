package co.alfite.sis.modelo;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.alfite.sis.ejb.ListarEJBRemote;
import co.alfite.sis.entidades.Administrador;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.entidades.MeGustaEspeciePlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.Resenia;
import co.alfite.sis.entidades.Usuario;
import co.alfite.sis.modelo.observable.EspecieObservable;
import co.alfite.sis.modelo.observable.FamiliaObservable;
import co.alfite.sis.modelo.observable.GeneroObservable;
import co.alfite.sis.modelo.observable.MeGustaObservable;
import co.alfite.sis.modelo.observable.PersonaObservable;
import co.alfite.sis.modelo.observable.RegistroObservable;
import co.alfite.sis.modelo.observable.ReseniaObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ListarDelegado {

	/**
	 * instancia que representa el ejb remoto de listar
	 */
	private ListarEJBRemote listarEJB;
	/**
	 * permite manejar una unica instancia para el manejo de delegados
	 */
	public static ListarDelegado listarDelegado = instancia();

	/**
	 * constructor para conectar con la capa de negocio
	 */
	private ListarDelegado() {
		try {
			listarEJB = (ListarEJBRemote) new InitialContext().lookup(ListarEJBRemote.JNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permite devolver una unica instancia de delegado
	 * 
	 * @return instancia unica del delegado
	 */
	private static ListarDelegado instancia() {

		if (listarDelegado == null) {
			listarDelegado = new ListarDelegado();
			return listarDelegado;
		}
		return listarDelegado;
	}
	
	public List<PersonaObservable> listarUsuariosObservables() {
		List<Usuario> usuarios = listarUsuarios();

		if (usuarios.isEmpty()) {
			System.out.println("esta vacia");
		}
		ObservableList<PersonaObservable> usuariosObservables = FXCollections.observableArrayList();

		for (Persona persona : usuarios) {
			usuariosObservables.add(new PersonaObservable(persona));
		}

		return usuariosObservables;

	}

	public List<Usuario> listarUsuarios() {
		return listarEJB.listarUsuarios();
	}

	public List<PersonaObservable> listarAdministradoresObservables() {
		List<Administrador> administradores = listarAdministradores();
		ObservableList<PersonaObservable> administradoresObservables = FXCollections.observableArrayList();

		for (Persona persona : administradores) {
			administradoresObservables.add(new PersonaObservable(persona));
		}
		return administradoresObservables;

	}
	
	public List<Administrador> listarAdministradores() {
		return listarEJB.listarAdministradores();
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
		return listarEJB.listarEmpleados();
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
		return listarEJB.listarRecolectores();
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
		return listarEJB.listarRegistros();
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
		return listarEJB.listarFamilias();
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
		return listarEJB.listarGeneros();
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
		return listarEJB.listarEspecies();
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
		return listarEJB.listarEspeciesPorFamilia(nombre);
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
		return listarEJB.listarEspeciesPorGenero(nombre);
	}

	public List<EspecieObservable> listarEspeciesAceptadasObservables() {
		List<EspeciePlanta> especies = listarEspeciesAceptadas();
		ObservableList<EspecieObservable> especiesObservables = FXCollections.observableArrayList();

		for (EspeciePlanta especie : especies) {
			especiesObservables.add(new EspecieObservable(especie));
		}
		return especiesObservables;

	}
	
	public List<EspeciePlanta> listarEspeciesAceptadas() {
		return listarEJB.listarEspeciesAceptadas();
	}

	public List<EspecieObservable> listarEspeciesRechazadasObservables() {
		List<EspeciePlanta> especies = listarEspeciesRechazadas();
		ObservableList<EspecieObservable> especiesObservables = FXCollections.observableArrayList();

		for (EspeciePlanta especie : especies) {
			especiesObservables.add(new EspecieObservable(especie));
		}
		return especiesObservables;

	}
	
	public List<EspeciePlanta> listarEspeciesRechazadas() {
		return listarEJB.listarEspeciesRechazadas();
	}

	public List<RegistroObservable> listarRegistrosAceptadasPorUnTrabajadorObservables(String idPersona) {
		List<RegistroEspecie> registros = listarRegistrosAceptadasDeUnTrabajador(idPersona);
		ObservableList<RegistroObservable> registrosObservables = FXCollections.observableArrayList();

		for (RegistroEspecie registro : registros) {
			registrosObservables.add(new RegistroObservable(registro));
		}
		return registrosObservables;

	}
	
	public List<RegistroEspecie> listarRegistrosAceptadasDeUnTrabajador(String idPersona) {
		return listarEJB.listarRegistrosAceptadasDeUnTrabajador(idPersona);
	}

	public List<RegistroObservable> listarRegistrosRechazadasPorUnTrabajadorObservables(String idPersona) {
		List<RegistroEspecie> registros = listarRegistrosRechazadasDeUnTrabajador(idPersona);
		ObservableList<RegistroObservable> registrosObservables = FXCollections.observableArrayList();

		for (RegistroEspecie registro : registros) {
			registrosObservables.add(new RegistroObservable(registro));
		}
		return registrosObservables;

	}
	
	public List<RegistroEspecie> listarRegistrosRechazadasDeUnTrabajador(String idPersona) {
		return listarEJB.listarRegistrosRechazadasDeUnTrabajador(idPersona);
	}

	public List<MeGustaObservable> listarMeGustasObservables() {
		List<MeGustaEspeciePlanta> meGustas = listarMeGustas();
		ObservableList<MeGustaObservable> meGustasObservables = FXCollections.observableArrayList();

		for (MeGustaEspeciePlanta meGusta : meGustas) {
			meGustasObservables.add(new MeGustaObservable(meGusta));
		}
		return meGustasObservables;

	}
	
	public List<MeGustaEspeciePlanta> listarMeGustas() {
		return listarEJB.listarMeGustas();
	}

	public List<MeGustaObservable> listarMeGustasDeUnUsuarioObservables(String idPersona) {
		List<MeGustaEspeciePlanta> meGustas = listarMeGustasDeUnUsuario(idPersona);
		ObservableList<MeGustaObservable> meGustasObservables = FXCollections.observableArrayList();

		for (MeGustaEspeciePlanta meGusta : meGustas) {
			meGustasObservables.add(new MeGustaObservable(meGusta));
		}
		return meGustasObservables;

	}
	
	public List<MeGustaEspeciePlanta> listarMeGustasDeUnUsuario(String idPersona) {
		return listarEJB.listarMeGustasDeUnUsuario( idPersona);
	}

	public List<MeGustaObservable> listarMeGustasDeUnaEspecieObservables(String nombreCientifico) {
		List<MeGustaEspeciePlanta> meGustas = listarMeGustasDeUnaEspecie(nombreCientifico);
		ObservableList<MeGustaObservable> meGustasObservables = FXCollections.observableArrayList();

		for (MeGustaEspeciePlanta meGusta : meGustas) {
			meGustasObservables.add(new MeGustaObservable(meGusta));
		}
		return meGustasObservables;

	}
	
	public List<MeGustaEspeciePlanta> listarMeGustasDeUnaEspecie(String nombreCientifico) {
		return listarEJB.listarMeGustasDeUnaEspecie(nombreCientifico);
	}

	public List<ReseniaObservable> listarReseniasObservables() {
		List<Resenia> resenias = listarResenias();
		ObservableList<ReseniaObservable> reseniasObservables = FXCollections.observableArrayList();

		for (Resenia resenia : resenias) {
			reseniasObservables.add(new ReseniaObservable(resenia));
		}
		return reseniasObservables;

	}
	
	public List<Resenia> listarResenias() {
		return listarEJB.listarResenias();
	}

	public List<ReseniaObservable> listarReseniasDeUnUsuarioObservables(String idPersona) {
		List<Resenia> resenias = listarReseniasDeUnUsuario(idPersona);
		ObservableList<ReseniaObservable> reseniasObservables = FXCollections.observableArrayList();

		for (Resenia resenia : resenias) {
			reseniasObservables.add(new ReseniaObservable(resenia));
		}
		return reseniasObservables;

	}
	
	public List<Resenia> listarReseniasDeUnUsuario(String idPersona) {
		return listarEJB.listarReseniasDeUnUsuario(idPersona);
	}

	public List<ReseniaObservable> listarReseniasDeUnaEspecieObservables(String nombreCientifico) {
		List<Resenia> resenias = listarReseniasDeUnaEspecie(nombreCientifico);
		ObservableList<ReseniaObservable> reseniasObservables = FXCollections.observableArrayList();

		for (Resenia resenia : resenias) {
			reseniasObservables.add(new ReseniaObservable(resenia));
		}
		return reseniasObservables;

	}
	
	public List<Resenia> listarReseniasDeUnaEspecie(String nombreCientifico) {
		return listarEJB.listarReseniasDeUnaEspecie(nombreCientifico);
	}
}

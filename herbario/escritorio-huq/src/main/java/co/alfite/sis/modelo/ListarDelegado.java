package co.alfite.sis.modelo;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import co.alfite.sis.ejb.ListarEJBRemote;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.modelo.observable.PersonaObservable;
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

	public List<PersonaObservable> listarPersonasObservables() {
		List<Empleado> empleados = listarEmpleado();
		ObservableList<PersonaObservable> empleadosObservables = FXCollections.observableArrayList();

		for (Persona persona : empleados) {
			empleadosObservables.add(new PersonaObservable(persona));
		}
		return empleadosObservables;

	}

	private List<Empleado> listarEmpleado() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Recolector> listarRecolector() {
		return listarEJB.listarRecolectores();
	}

	public List<PersonaObservable> listarRecolectoresObservables() {
		List<Recolector> recolectores = listarRecolector();

		if (recolectores.isEmpty()) {
			System.out.println("esta vacia");
		}
		ObservableList<PersonaObservable> empleadosObservables = FXCollections.observableArrayList();

		for (Persona persona : recolectores) {
			empleadosObservables.add(new PersonaObservable(persona));
		}

		return empleadosObservables;

	}

	public List<PersonaObservable> listarEmpleadosObservables() {
		// TODO Auto-generated method stub
		return null;
	}
}

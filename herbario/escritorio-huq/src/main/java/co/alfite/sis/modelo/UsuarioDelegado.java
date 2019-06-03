package co.alfite.sis.modelo;

import java.util.List;

import co.alfite.sis.ejb.UsuarioEJBRemote;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.MeGustaEspeciePlanta;
import co.alfite.sis.entidades.Resenia;
import co.alfite.sis.entidades.Resenia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UsuarioDelegado {

	/**
	 * instancia que representa el ejb remoto de usuario
	 */
	private UsuarioEJBRemote usuEJB;

	/**
	 * permite manejar una unica instancia para le manejo de delegados
	 */
	public static UsuarioDelegado UsuarioDelegado = instancia();

	/**
	 * Permite devolver una unica instancia de delegado
	 * 
	 * @return instancia unica del delegado
	 */
	private static UsuarioDelegado instancia() {

		if (UsuarioDelegado == null) {
			UsuarioDelegado = new UsuarioDelegado();
			return UsuarioDelegado;
		}
		return UsuarioDelegado;
	}

	/**
	 * pemite registar un nuevo me gusta hacia una planta
	 * 
	 * @param meGusta
	 * @return
	 */
	/*
	 * Debe de tener gestion de excepciones?
	 */
	public boolean registrarMeGusta(MeGustaEspeciePlanta meGusta) {
		return usuEJB.insertarMegusta(meGusta) != null;
	}

	/**
	 * pemite registar un nuevo resenia hacia una planta
	 * 
	 * @param resenia
	 * @return
	 */
	/*
	 * Debe de tener gestion de excepciones?
	 */
	public boolean registrarResenia(Resenia resenia) {
		return usuEJB.insertarResenia(resenia) != null;
	}

	/**
	 * Genera una lista de me gusta observables
	 * @return todos los megustas observables
	 */
	public ObservableList<MeGustaObservable> listarMegustasObservables() {
		List<MeGustaEspeciePlanta> meGusta = listarMeGusta();
		ObservableList<MeGustaObservable> meGustaObservables = FXCollections.observableArrayList();
		for (MeGustaEspeciePlanta like : meGusta) {
			meGustaObservables.add(new MeGustaObservable(like));
		}
		return meGustaObservables;
	}
	
	/**
	 * devuelve la lista de me gustas que estan en la base de datos
	 * 
	 * @return todos los me gustas
	 */
	public List<MeGustaEspeciePlanta> listarMeGusta() {
		//QUERY a la base de datos 
		return null;
	}

	/**
	 * Genera una lista de resenias observables
	 * @return todas las resenias observables
	 */
	public ObservableList<ReseniaObservable> listarReseniasObservables() {
		List<Resenia> resenia = listarResenias();
		ObservableList<ReseniaObservable> reseniaObservables = FXCollections.observableArrayList();
		for (Resenia comentario : resenia) {
			reseniaObservables.add(new ReseniaObservable(comentario));
		}
		return reseniaObservables;
	}
	
	/**
	 * devuelve la lista de resenias que estan en la base de datos
	 * 
	 * @return todas las resenias
	 */
	public List<Resenia> listarResenias() {
		//QUERY a la base de datos
		return null;
	}
}

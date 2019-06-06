package co.alfite.sis.modelo.observable;

import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.Resenia;
import co.alfite.sis.entidades.Resenia.Estado;
import co.alfite.sis.entidades.Usuario;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ReseniaObservable {

	private ObjectProperty<EspeciePlanta> especie;

	private ObjectProperty<Usuario> usuario;

	private IntegerProperty id;

	private StringProperty texto;

	private ObjectProperty<Estado> estado;

	private Resenia resenia;

	public ReseniaObservable(Resenia resenia) {
		this.resenia = resenia;
		this.id = new SimpleIntegerProperty(resenia.getIdResenia());
		this.texto = new SimpleStringProperty(resenia.getTexto());
		this.estado = new SimpleObjectProperty<>(resenia.getEstado());
		this.especie = new SimpleObjectProperty<>(resenia.getEspecie());
		this.usuario = new SimpleObjectProperty<>(resenia.getUsuario());
	}

	public ObjectProperty<EspeciePlanta> getEspecie() {
		return especie;
	}

	public void setEspecie(ObjectProperty<EspeciePlanta> especie) {
		this.especie = especie;
	}

	public ObjectProperty<Usuario> getUsuario() {
		return usuario;
	}

	public void setUsuario(ObjectProperty<Usuario> usuario) {
		this.usuario = usuario;
	}

	public IntegerProperty getId() {
		return id;
	}

	public void setId(IntegerProperty id) {
		this.id = id;
	}

	public StringProperty getTexto() {
		return texto;
	}

	public void setTexto(StringProperty texto) {
		this.texto = texto;
	}

	public ObjectProperty<Estado> getEstado() {
		return estado;
	}

	public void setEstado(ObjectProperty<Estado> estado) {
		this.estado = estado;
	}

	public Resenia getResenia() {
		return resenia;
	}

	public void setResenia(Resenia resenia) {
		this.resenia = resenia;
	}

}

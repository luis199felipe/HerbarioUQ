package co.alfite.sis.modelo.observable;

import co.alfite.sis.entidades.Persona;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PersonaObservable {

	private StringProperty nombre;
	private StringProperty id;

	public PersonaObservable(Persona p) {
		this.nombre=new SimpleStringProperty(p.getNombre());
		this.id=new SimpleStringProperty(p.getIdPersona());

	}

	public StringProperty getId() {
		return id;
	}

	public void setId(StringProperty id) {
		this.id = id;
	}

	public PersonaObservable() {
		this(null, null);
	}

	public PersonaObservable(String nombre, String id) {
		this.nombre = new SimpleStringProperty(nombre);
		this.id = new SimpleStringProperty(id);

	}

	public StringProperty getNombre() {
		return nombre;
	}

	public void setNombre(StringProperty nombre) {
		this.nombre = nombre;
	}

}

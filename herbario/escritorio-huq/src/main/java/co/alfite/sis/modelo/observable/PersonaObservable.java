package co.alfite.sis.modelo.observable;

import java.util.Date;

import com.fasterxml.jackson.annotation.SimpleObjectIdResolver;

import co.alfite.sis.entidades.Persona;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class PersonaObservable {

	private StringProperty idPersona;
	private StringProperty nombre;
	private StringProperty email;
	private StringProperty password;
	private StringProperty estado;
	private StringProperty telefono;
	private ObjectProperty<Date> fechaNacimiento;
	
	public PersonaObservable(Persona p) {
		this.idPersona = new SimpleStringProperty(p.getIdPersona());
		this.nombre = new SimpleStringProperty(p.getIdPersona());
		this.email = new SimpleStringProperty(p.getIdPersona());
		this.password = new SimpleStringProperty(p.getIdPersona());
		this.estado = new SimpleStringProperty(p.getIdPersona());
		this.telefono = new SimpleStringProperty(p.getIdPersona());
		this.fechaNacimiento = new SimpleObjectProperty<>(p.getFechaNacimiento());
	}

	public PersonaObservable(StringProperty idPersona, StringProperty nombre, StringProperty email,
			StringProperty password, StringProperty estado, StringProperty telefono,
			ObjectProperty<Date> fechaNacimiento) {
		
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.email = email;
		this.password = password;
		this.estado = estado;
		this.telefono = telefono;
		this.fechaNacimiento = fechaNacimiento;
	}
	
	

}

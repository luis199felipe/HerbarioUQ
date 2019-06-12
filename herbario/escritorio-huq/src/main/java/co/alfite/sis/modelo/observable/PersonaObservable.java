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
		this.nombre = new SimpleStringProperty(p.getNombre());
		this.email = new SimpleStringProperty(p.getEmail());
		this.password = new SimpleStringProperty(p.getPassword());
		this.estado = new SimpleStringProperty(String.valueOf(p.getEstado()));
		this.telefono = new SimpleStringProperty(p.getTelefono());
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

	public StringProperty getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(StringProperty idPersona) {
		this.idPersona = idPersona;
	}

	public StringProperty getNombre() {
		return nombre;
	}

	public void setNombre(StringProperty nombre) {
		this.nombre = nombre;
	}

	public StringProperty getEmail() {
		return email;
	}

	public void setEmail(StringProperty email) {
		this.email = email;
	}

	public StringProperty getPassword() {
		return password;
	}

	public void setPassword(StringProperty password) {
		this.password = password;
	}

	public StringProperty getEstado() {
		return estado;
	}

	public void setEstado(StringProperty estado) {
		this.estado = estado;
	}

	public StringProperty getTelefono() {
		return telefono;
	}

	public void setTelefono(StringProperty telefono) {
		this.telefono = telefono;
	}

	public ObjectProperty<Date> getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(ObjectProperty<Date> fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}

package co.alfite.sis.modelo.observable;

import co.alfite.sis.entidades.GeneroPlanta;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GeneroObservable {
	private StringProperty idGenero;
	private StringProperty nombre;
	
	public GeneroObservable(GeneroPlanta genero) {
		this.idGenero = new SimpleStringProperty(String.valueOf(genero.getIdGenero()));
		this.nombre = new SimpleStringProperty(genero.getNombre());
	}
	
	public GeneroObservable(StringProperty idGenero, StringProperty nombre) {
		this.idGenero = idGenero;
		this.nombre = nombre;
	}


	public StringProperty getIdGenero() {
		return idGenero;
	}


	public void setIdGenero(StringProperty idGenero) {
		this.idGenero = idGenero;
	}


	public StringProperty getNombre() {
		return nombre;
	}


	public void setNombre(StringProperty nombre) {
		this.nombre = nombre;
	}
	
	
}

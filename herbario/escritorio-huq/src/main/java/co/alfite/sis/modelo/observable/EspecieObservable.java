package co.alfite.sis.modelo.observable;

import co.alfite.sis.entidades.EspeciePlanta;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EspecieObservable {
	private IntegerProperty idEspecie;
	private IntegerProperty cantidad;
	private StringProperty nombre;
	private StringProperty nombreCientifico;
	
	public EspecieObservable(EspeciePlanta especie) {
		this.idEspecie = new SimpleIntegerProperty(especie.getIdEspecie());
		//this.cantidad = new SimpleIntegerProperty(especie.getCantidad());
		this.nombre = new SimpleStringProperty(especie.getNombre());
		this.nombreCientifico = new SimpleStringProperty(especie.getNombreCientifico());
	}
	
	public EspecieObservable(IntegerProperty idEspecie, IntegerProperty cantidad, StringProperty nombre,
			StringProperty nombreCientifico) {
		this.idEspecie = idEspecie;
		this.cantidad = cantidad;
		this.nombre = nombre;
		this.nombreCientifico = nombreCientifico;
	}


	public IntegerProperty getIdEspecie() {
		return idEspecie;
	}


	public void setIdEspecie(IntegerProperty idEspecie) {
		this.idEspecie = idEspecie;
	}


	public IntegerProperty getCantidad() {
		return cantidad;
	}


	public void setCantidad(IntegerProperty cantidad) {
		this.cantidad = cantidad;
	}


	public StringProperty getNombre() {
		return nombre;
	}


	public void setNombre(StringProperty nombre) {
		this.nombre = nombre;
	}


	public StringProperty getNombreCientifico() {
		return nombreCientifico;
	}


	public void setNombreCientifico(StringProperty nombreCientifico) {
		this.nombreCientifico = nombreCientifico;
	}
	
	
}

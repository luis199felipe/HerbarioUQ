package co.alfite.sis.modelo.observable;

import co.alfite.sis.entidades.EspeciePlanta;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EspecieObservable {
	private StringProperty idEspecie;
	private StringProperty cantidad;
	private StringProperty nombre;
	private StringProperty nombreCientifico;
	private SimpleStringProperty nombreGenero;
	private SimpleStringProperty nombreFamilia;

	public EspecieObservable(EspeciePlanta especie) {
		this.idEspecie = new SimpleStringProperty(String.valueOf(especie.getIdEspecie()));
		this.nombre = new SimpleStringProperty(especie.getNombre());
		this.nombreCientifico = new SimpleStringProperty(especie.getNombreCientifico());
		this.nombreGenero = new SimpleStringProperty(especie.getGeneroPlanta().getNombre());
		this.nombreFamilia = new SimpleStringProperty(especie.getGeneroPlanta().getFamiliaPlanta().getNombre());

	}

	public StringProperty getIdEspecie() {
		return idEspecie;
	}

	public void setIdEspecie(StringProperty idEspecie) {
		this.idEspecie = idEspecie;
	}

	public StringProperty getCantidad() {
		return cantidad;
	}

	public void setCantidad(StringProperty cantidad) {
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

	public SimpleStringProperty getNombreGenero() {
		return nombreGenero;
	}

	public void setNombreGenero(SimpleStringProperty nombreGenero) {
		this.nombreGenero = nombreGenero;
	}

	public SimpleStringProperty getNombreFamilia() {
		return nombreFamilia;
	}

	public void setNombreFamilia(SimpleStringProperty nombreFamilia) {
		this.nombreFamilia = nombreFamilia;
	}

}

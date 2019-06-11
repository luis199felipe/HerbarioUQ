package co.alfite.sis.modelo.observable;

import co.alfite.sis.entidades.FamiliaPlanta;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FamiliaObservable {
	private IntegerProperty idFamilia;
	private StringProperty nombre;

	public FamiliaObservable(FamiliaPlanta familia) {
		this.idFamilia = new SimpleIntegerProperty(familia.getIdFamilia());
		this.nombre = new SimpleStringProperty(familia.getNombre());
	}

	public FamiliaObservable(IntegerProperty idFamilia, StringProperty nombre) {
		this.idFamilia = idFamilia;
		this.nombre = nombre;
	}

	public IntegerProperty getIdFamilia() {
		return idFamilia;
	}

	public void setIdFamilia(IntegerProperty idFamilia) {
		this.idFamilia = idFamilia;
	}

	public StringProperty getNombre() {
		return nombre;
	}

	public void setNombre(StringProperty nombre) {
		this.nombre = nombre;
	}

}

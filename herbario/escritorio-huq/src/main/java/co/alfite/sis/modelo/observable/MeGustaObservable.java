package co.alfite.sis.modelo.observable;

import java.util.Date;

import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.MeGustaEspeciePlanta;
import co.alfite.sis.entidades.Usuario;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class MeGustaObservable {

	private ObjectProperty<Date> fecha;

	private IntegerProperty idMeGusta;

	private MeGustaEspeciePlanta meGusta;

	public MeGustaObservable(MeGustaEspeciePlanta meGusta) {
		this.meGusta = meGusta;
		this.idMeGusta = new SimpleIntegerProperty(meGusta.getIdMegusta());
		this.fecha = new SimpleObjectProperty<>(new Date());
	}

	public ObjectProperty<Date> getFecha() {
		return fecha;
	}

	public void setFecha(ObjectProperty<Date> fecha) {
		this.fecha = fecha;
	}

	public IntegerProperty getIdMeGusta() {
		return idMeGusta;
	}

	public void setIdMeGusta(IntegerProperty id) {
		this.idMeGusta = id;
	}

	public MeGustaEspeciePlanta getMeGusta() {
		return meGusta;
	}

	public void setMeGusta(MeGustaEspeciePlanta meGusta) {
		this.meGusta = meGusta;
	}

}

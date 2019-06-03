package co.alfite.sis.modelo;

import java.util.Date;

import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.MeGustaEspeciePlanta;
import co.alfite.sis.entidades.Usuario;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class MeGustaObservable {

	private ObjectProperty<EspeciePlanta> especie;

	private ObjectProperty<Usuario> usuario;

	private ObjectProperty<Date> fecha;

	private IntegerProperty id;

	private MeGustaEspeciePlanta meGusta;

	public MeGustaObservable(MeGustaEspeciePlanta meGusta) {
		this.meGusta = meGusta;
		this.id = new SimpleIntegerProperty(meGusta.getIdMegusta());
		this.especie = new SimpleObjectProperty<>(meGusta.getEspecie());
		this.fecha = new SimpleObjectProperty<>(new Date());
		this.usuario = new SimpleObjectProperty<>(meGusta.getUsuario());
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

	public ObjectProperty<Date> getFecha() {
		return fecha;
	}

	public void setFecha(ObjectProperty<Date> fecha) {
		this.fecha = fecha;
	}

	public IntegerProperty getId() {
		return id;
	}

	public void setId(IntegerProperty id) {
		this.id = id;
	}

	public MeGustaEspeciePlanta getMeGusta() {
		return meGusta;
	}

	public void setMeGusta(MeGustaEspeciePlanta meGusta) {
		this.meGusta = meGusta;
	}

}

package co.alfite.sis.modelo.observable;

import java.util.Date;

import com.sun.prism.RectShadowGraphics;

import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.RegistroEspecie.Estado;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RegistroObservable {
	private IntegerProperty idRegistro;
	private ObjectProperty<Estado> estado;
	private ObjectProperty<Date> fecha;
	private StringProperty mensaje;

	public RegistroObservable(RegistroEspecie registro) {
		this.idRegistro = new SimpleIntegerProperty(registro.getIdRegistro());
		this.estado = new SimpleObjectProperty<>(registro.getEstado());
		this.fecha = new SimpleObjectProperty<>(registro.getFecha());
		this.mensaje = new SimpleStringProperty(registro.getMensaje());
	}
	
	public RegistroObservable(IntegerProperty idRegistro, ObjectProperty<Estado> estado, ObjectProperty<Date> fecha,
			StringProperty mensaje) {
		this.idRegistro = idRegistro;
		this.estado = estado;
		this.fecha = fecha;
		this.mensaje = mensaje;
	}

	public IntegerProperty getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(IntegerProperty idRegistro) {
		this.idRegistro = idRegistro;
	}

	public ObjectProperty<Estado> getEstado() {
		return estado;
	}

	public void setEstado(ObjectProperty<Estado> estado) {
		this.estado = estado;
	}

	public ObjectProperty<Date> getFecha() {
		return fecha;
	}

	public void setFecha(ObjectProperty<Date> fecha) {
		this.fecha = fecha;
	}

	public StringProperty getMensaje() {
		return mensaje;
	}

	public void setMensaje(StringProperty mensaje) {
		this.mensaje = mensaje;
	}

}

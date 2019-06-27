package co.alfite.sis.modelo.observable;




import co.alfite.sis.entidades.ImagenPlanta;
import co.alfite.sis.entidades.RegistroEspecie;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class RegistroObservable {
	private StringProperty idRegistro;
	private StringProperty estado;
	private StringProperty fecha;
	private StringProperty mensaje;
	private ImagenPlanta imagen;
	private SimpleStringProperty nombreEspecie;
	private SimpleStringProperty nombreGenero;
	private SimpleStringProperty nombreFamilia;
	

	public RegistroObservable(RegistroEspecie registro) {
		this.idRegistro = new SimpleStringProperty(String.valueOf(registro.getIdRegistro()));
		this.estado = new SimpleStringProperty(String.valueOf(registro.getEstado()));
		this.fecha = new SimpleStringProperty(String.valueOf(registro.getFecha()));
		this.mensaje = new SimpleStringProperty(registro.getMensaje());
		this.imagen=registro.getImagen();
		this.nombreEspecie=new SimpleStringProperty(registro.getNombreEspecie());
		this.nombreGenero=new SimpleStringProperty(registro.getNombreGenero());
		this.nombreFamilia=new SimpleStringProperty(registro.getNombreFamilia());
	}

	

	public StringProperty getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(StringProperty idRegistro) {
		this.idRegistro = idRegistro;
	}

	public StringProperty getEstado() {
		return estado;
	}

	public void setEstado(StringProperty estado) {
		this.estado = estado;
	}

	public StringProperty getFecha() {
		return fecha;
	}

	public void setFecha(StringProperty fecha) {
		this.fecha = fecha;
	}

	public StringProperty getMensaje() {
		return mensaje;
	}

	public void setMensaje(StringProperty mensaje) {
		this.mensaje = mensaje;
	}



	public ImagenPlanta getImagen() {
		return imagen;
	}



	public void setImagen(ImagenPlanta imagen) {
		this.imagen = imagen;
	}



	public SimpleStringProperty getNombreEspecie() {
		return nombreEspecie;
	}



	public void setNombreEspecie(SimpleStringProperty nombreEspecie) {
		this.nombreEspecie = nombreEspecie;
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

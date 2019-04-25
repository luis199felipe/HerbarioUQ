package co.alfite.sis.entidades;

import java.io.Serializable;
import java.lang.String;
import java.sql.Blob;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ImagenPlanta
 *
 */
@Entity

public class ImagenPlanta implements Serializable {

	   
	@Id
	private String idImagen;
	private Blob imagen;
	private static final long serialVersionUID = 1L;

	public ImagenPlanta() {
		super();
	}   
	public String getIdImagen() {
		return this.idImagen;
	}

	public void setIdImagen(String idImagen) {
		this.idImagen = idImagen;
	}   
	public Blob getImagen() {
		return this.imagen;
	}

	public void setImagen(Blob imagen) {
		this.imagen = imagen;
	}
   
}

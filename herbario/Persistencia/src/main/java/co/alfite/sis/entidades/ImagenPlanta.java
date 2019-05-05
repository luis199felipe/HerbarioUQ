package co.alfite.sis.entidades;

import java.io.Serializable;
import java.lang.String;
import java.sql.Blob;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ImagenPlanta
 * 
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * @version 1.0
 */
@Entity

public class ImagenPlanta implements Serializable {

	/**
	 * Muchas ImagenesPlanta pertenecen a una EspecePlanta
	 */
	@ManyToOne
	private EspeciePlanta especie;

	/**
	 * Identificacion unica de una ImagenPlanta
	 */
	@Id
	private String idImagen;

	/**
	 * Imagen de una ImagenPlanta
	 */
	private Blob imagen;

	private static final long serialVersionUID = 1L;

	public ImagenPlanta() {
		super();
	}

	public EspeciePlanta getEspecie() {
		return especie;
	}

	public void setEspecie(EspeciePlanta especie) {
		this.especie = especie;
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

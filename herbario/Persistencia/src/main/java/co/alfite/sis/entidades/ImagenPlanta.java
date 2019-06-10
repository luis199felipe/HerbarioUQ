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
@NamedQueries({ @NamedQuery(name = ImagenPlanta.IMAGEN_GET_ALL, query = "select imagen from ImagenPlanta imagen") })
public class ImagenPlanta implements Serializable {

	public static final String IMAGEN_GET_ALL = "ImagenGetAll";

	/**
	 * Muchas ImagenesPlanta pertenecen a una EspecePlanta
	 */
//	@ManyToOne
//	private EspeciePlanta especie;

	/**
	 * Identificacion unica de una ImagenPlanta
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idImagen;

	/**
	 * Imagen de una ImagenPlanta
	 */
	@Lob
	@Column()
	private byte[] imagen;

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}

	private static final long serialVersionUID = 1L;

	public ImagenPlanta() {
		super();
	}

//	public EspeciePlanta getEspecie() {
//		return especie;
//	}
//
//	public void setEspecie(EspeciePlanta especie) {
//		this.especie = especie;
//	}

	public Integer getIdImagen() {
		return this.idImagen;
	}

	public void setIdImagen(Integer idImagen) {
		this.idImagen = idImagen;
	}

}

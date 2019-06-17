package co.alfite.sis.entidades;

import java.io.Serializable;
import java.lang.String;
import java.sql.Blob;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ImagenPlanta
 * 
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * @version 1.0
 */
@Entity
@NamedQueries({ @NamedQuery(name = ImagenPlanta.IMAGEN_GET_ALL, query = "select imagen from ImagenPlanta imagen"),
		@NamedQuery(name = ImagenPlanta.ESPECIES_POR_LIKES_DES, query = "SELECT img FROM ImagenPlanta img order by img.numeroLikes DESC"),
		@NamedQuery(name = ImagenPlanta.IMAGEN_POR_ID, query = "select imagen from ImagenPlanta imagen where imagen.idImagen=:id") })
public class ImagenPlanta implements Serializable {

	public static final String IMAGEN_GET_ALL = "ImagenGetAll";
	public static final String ESPECIES_POR_LIKES_DES = "EspeciesNumeroLikeDes";
	public static final String IMAGEN_POR_ID = "EspeciesPorId";


	/**
	 * Muchas ImagenesPlanta pertenecen a una EspecePlanta
	 */

	@OneToMany(mappedBy = "imagen", orphanRemoval = true)
	private List<Resenia> resenias;

	/**
	 * Una EspeciePlanta tiene muchas MeGusta de Usuarios
	 */
	@OneToMany(mappedBy = "imagen", orphanRemoval = true)
	private List<MeGustaEspeciePlanta> megustas;

	@ManyToOne
	private EspeciePlanta especie;

	@OneToOne
	private RegistroEspecie registro;

	private Long numeroLikes;
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

	public EspeciePlanta getEspecie() {
		return especie;
	}

	public void setEspecie(EspeciePlanta especie) {
		this.especie = especie;
	}

	public Integer getIdImagen() {
		return this.idImagen;
	}

	public void setIdImagen(Integer idImagen) {
		this.idImagen = idImagen;
	}

	public RegistroEspecie getRegistro() {
		return registro;
	}

	public void setRegistro(RegistroEspecie registro) {
		this.registro = registro;
	}

	public Long getNumeroLikes() {
		return numeroLikes;
	}

	public void setNumeroLikes(Long numeroLikes) {
		this.numeroLikes = numeroLikes;
	}

	public List<Resenia> getResenias() {
		return resenias;
	}

	public void setResenias(List<Resenia> resenias) {
		this.resenias = resenias;
	}

	public List<MeGustaEspeciePlanta> getMegustas() {
		return megustas;
	}

	public void setMegustas(List<MeGustaEspeciePlanta> megustas) {
		this.megustas = megustas;
	}

}

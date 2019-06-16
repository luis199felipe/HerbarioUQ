package co.alfite.sis.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Resenia
 * 
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * @version 1.0
 */
@Entity
@NamedQueries({ @NamedQuery(name = Resenia.RESENIA_GET_ALL, query = "select resenia from Resenia resenia"),
		@NamedQuery(name = Resenia.RESENIA_ESPECIE, query = "select resenia from Resenia resenia where resenia.imagen.especie.idEspecie = :esp"),
		@NamedQuery(name = Resenia.RESENIA_ESPECIE_NOMBRECIENTIFICO, query = "select resenia from Resenia resenia where resenia.imagen.especie.nombreCientifico = :nom"),
		@NamedQuery(name = Resenia.RESENIA_USUARIO, query = "select resenia from Resenia resenia where resenia.usuario.idPersona = :per") })

public class Resenia implements Serializable {

	public static final String RESENIA_GET_ALL = "ReseniaGetAll";
	public static final String RESENIA_ESPECIE = "ReseniaDeUnaEspecieEspecifica";
	public static final String RESENIA_ESPECIE_NOMBRECIENTIFICO = "ReseniaDeUnaEspecieEspecificaPorNombreCientifico";
	public static final String RESENIA_USUARIO = "ReseniasDeUnUsuarioEspecifico";

	/**
	 * Muchos Resenia pertenecen a un Usuario
	 */
	@ManyToOne
	private Usuario usuario;

	/**
	 * Muchos Resenia pertenecen a una EspeciePlanta
	 */
	@ManyToOne
	private ImagenPlanta imagen;

	/**
	 * identificacion unica de una Resenia
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idResenia;

	/**
	 * texto de la resenia
	 */
	private String texto;

	/**
	 * estado de la Resenia (enviado, aprobado, rechazado)
	 */
	public enum Estado {
		enviado, aprobado, rechazado
	}

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private Estado estado;

	private static final long serialVersionUID = 1L;

	public Resenia() {
		super();
	}

	public Integer getIdResenia() {
		return idResenia;
	}

	public void setIdResenia(Integer idResenia) {
		this.idResenia = idResenia;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public ImagenPlanta getImagen() {
		return imagen;
	}

	public void setImagen(ImagenPlanta imagen) {
		this.imagen = imagen;
	}

}

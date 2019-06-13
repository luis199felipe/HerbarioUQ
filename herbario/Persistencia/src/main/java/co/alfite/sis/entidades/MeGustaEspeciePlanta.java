package co.alfite.sis.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import co.alfite.sis.entidades.Resenia.Estado;

/**
 * Entity implementation class for Entity: MeGustaEspeciePlanta
 * 
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * @version 1.0
 */
@Entity
@NamedQueries({
		@NamedQuery(name = MeGustaEspeciePlanta.MEGUSTAESPECIE_GET_ALL, query = "select megustaEspecie from MeGustaEspeciePlanta megustaEspecie"),
		@NamedQuery(name = MeGustaEspeciePlanta.MEGUSTAESPECIE_ESPECIE, query = "select megusta from MeGustaEspeciePlanta megusta where megusta.especie.idEspecie = :esp "),
		@NamedQuery(name = MeGustaEspeciePlanta.MEGUSTAESPECIE_ESPECIE_NOMBRECIENTIFICO, query = "select megusta from MeGustaEspeciePlanta megusta where megusta.especie.nombreCientifico = :nom "),
		@NamedQuery(name = MeGustaEspeciePlanta.MEGUSTAESPECIE_USUARIO, query = "select megusta from MeGustaEspeciePlanta megusta where megusta.usuario.idPersona = :per "),
		@NamedQuery(name = MeGustaEspeciePlanta.MEGUSTAESPECIE_ESPECIEMASMEGUSTAS, query = "select count(megusta.especie.idEspecie) as n from MeGustaEspeciePlanta megusta group by megusta.especie order by n desc") })
public class MeGustaEspeciePlanta implements Serializable {

	public static final String MEGUSTAESPECIE_GET_ALL = "MeGustaEspecieGetAll";
	public static final String MEGUSTAESPECIE_ESPECIE = "MeGustaDeUnaEspecieEspecifica";
	public static final String MEGUSTAESPECIE_ESPECIE_NOMBRECIENTIFICO = "MeGustaDeUnaEspecieEspecificaPorNombreCientifico";
	public static final String MEGUSTAESPECIE_USUARIO = "MeGustaDeUnUsuarioEspecifico";
	public static final String MEGUSTAESPECIE_ESPECIEMASMEGUSTAS = "EspecieConMasMegustas";

	/**
	 * Muchos MeGustaEspeciePlanta pertenecen a un Usuario
	 */

	@ManyToOne
	private Usuario usuario;

	/**
	 * Muchos MeGustaEspeciePlanta pertenecen a una EspeciePlanta
	 */
	@ManyToOne
	private EspeciePlanta especie;

	/**
	 * estado del meGusta (meGusta, noMeGusta)
	 */
	public enum Estado {
		MeGusta, NoMeGusta
	}

	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private Estado estado;
	
	/**
	 * Identificacion unica de MegustaEspeciePlanta
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idMegusta;

	/**
	 * fecha de publicacion del MeGustaEspeciePlanta
	 */
	private Date fecha;

	private static final long serialVersionUID = 1L;

	public MeGustaEspeciePlanta() {
		super();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public EspeciePlanta getEspecie() {
		return especie;
	}

	public void setEspecie(EspeciePlanta especie) {
		this.especie = especie;
	}

	public Integer getIdMegusta() {
		return idMegusta;
	}

	public void setIdMegusta(Integer idMegusta) {
		this.idMegusta = idMegusta;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

}

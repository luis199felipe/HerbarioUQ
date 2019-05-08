package co.alfite.sis.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Resenia
 * 
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * @version 1.0
 */

@NamedQueries({ @NamedQuery(name = Resenia.RESENIA_GET_ALL, query = "select resenia from Resenia resenia") })
@Entity
public class Resenia implements Serializable {

	public static final String RESENIA_GET_ALL = "ReseniaGetAll";

	/**
	 * Muchos Resenia pertenecen a un Usuario
	 */
	@ManyToOne
	private Usuario usuario;

	/**
	 * Muchos Resenia pertenecen a una EspeciePlanta
	 */
	@ManyToOne
	private EspeciePlanta especie;

	/**
	 * identificacion unica de una Resenia
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int idResenia;

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

	public int getIdResenia() {
		return idResenia;
	}

	public void setIdResenia(int idResenia) {
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

	public EspeciePlanta getEspecie() {
		return especie;
	}

	public void setEspecie(EspeciePlanta especie) {
		this.especie = especie;
	}

}

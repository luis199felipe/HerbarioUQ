package co.alfite.sis.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Recolector
 * 
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * @version 1.0
 */

@NamedQueries({ @NamedQuery(name = Usuario.USUARIO_GET_ALL, query = "select u from Usuario u"),
		@NamedQuery(name = Usuario.USUARIO_POR_ID, query = "select u from Usuario u where u.idPersona=:id"),
		@NamedQuery(name = Usuario.USUARIO_GET_NUMBER, query = "select count(u) from Usuario u") })

@Entity
public class Usuario extends Persona implements Serializable {

	/**
	 * Entidad que hereda de Persona
	 */

	/**
	 * Un Usuario tiene muchas Resenias de una EspeciePlanta(0..*)
	 */
	@OneToMany(mappedBy = "usuario")
	private List<Resenia> resenias;

	public static final String USUARIO_GET_ALL = "UsuarioGetAll";
	public static final String USUARIO_POR_ID = "UsuarioID";
	public static final String USUARIO_GET_NUMBER = "UsuarioNumber";


	/**
	 * Un Usuario tiene muchas MeGusta de una EspeciePlanta (0..*)
	 */
	@OneToMany(mappedBy = "usuario")
	private List<MeGustaEspeciePlanta> megustas;

	private static final long serialVersionUID = 1L;

	public Usuario() {
		super();
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

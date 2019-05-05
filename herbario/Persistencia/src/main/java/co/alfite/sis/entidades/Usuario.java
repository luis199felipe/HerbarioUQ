package co.alfite.sis.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Recolector
 * 
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * @version 1.0
 */
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

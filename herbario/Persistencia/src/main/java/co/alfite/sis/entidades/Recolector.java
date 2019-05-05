package co.alfite.sis.entidades;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Recolector
 * 
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * @version 1.0
 */
@Entity
public class Recolector extends Trabajador implements Serializable {

	/**
	 * Entidad que hereda de Trabajador
	 */

	private static final long serialVersionUID = 1L;

	public Recolector() {
		super();
	}

}

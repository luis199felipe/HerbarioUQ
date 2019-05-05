package co.alfite.sis.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Empleado
 * 
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * @version 1.0
 */
@Entity

public class Empleado extends Trabajador implements Serializable {

	/**
	 * Entidad que hereda de Persona, no tiene campos propios
	 */

	private static final long serialVersionUID = 1L;

	public Empleado() {
		super();
	}

}

package co.alfite.sis.entidades;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Recolector
 *
 */
@Entity

public class Recolector extends Trabajador implements Serializable {

	/**
	 * Entidad que hereda de Trabajador, no tiene campos propios
	 */

	private static final long serialVersionUID = 1L;

	public Recolector() {
		super();
	}

}

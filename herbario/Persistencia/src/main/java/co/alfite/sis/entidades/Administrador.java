package co.alfite.sis.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Administrador
 *
 */
@Entity

public class Administrador  extends Persona implements Serializable {

	/**
	 * Entidad que hereda de Persona , no tiene campos propios
	 */

	private static final long serialVersionUID = 1L;

	public Administrador() {
		super();
	}
   
}

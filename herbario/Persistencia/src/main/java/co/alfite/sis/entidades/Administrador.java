package co.alfite.sis.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Administrador
 * 
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * @version 1.0
 */
@Entity

public class Administrador extends Trabajador implements Serializable {

	/**
	 * Entidad que hereda de Trabajador
	 */

	private static final long serialVersionUID = 1L;

	public Administrador() {
		super();
	}

}

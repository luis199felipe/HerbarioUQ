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
@NamedQueries({
	@NamedQuery(name=Administrador.ADMINISTRADOR_GET_ALL ,query="select administrador from Administrador administrador")
})

public class Administrador extends Trabajador implements Serializable {

	
	public static final String ADMINISTRADOR_GET_ALL = "AdministradorGetAll";

	
	/**
	 * Entidad que hereda de Trabajador
	 */

	private static final long serialVersionUID = 1L;

	public Administrador() {
		super();
	}

}

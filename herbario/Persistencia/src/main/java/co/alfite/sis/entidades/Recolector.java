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

@NamedQueries({
	@NamedQuery(name=Recolector.RECOLECTOR_GET_ALL ,query="select recolector from Recolector recolector")
})

@Entity
public class Recolector extends Trabajador implements Serializable {

	public static final String RECOLECTOR_GET_ALL = "RecolectorGetAll";

	
	/**
	 * Entidad que hereda de Trabajador
	 */

	private static final long serialVersionUID = 1L;

	public Recolector() {
		super();
	}

}

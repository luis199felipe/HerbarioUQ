package co.alfite.sis.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: HerbarioUQ
 *
 */
@Entity

public class HerbarioUQ implements Serializable {

	@OneToMany(mappedBy="herbario")
	private List<FamiliaPlanta>familias;
//	@OneToMany(mappedBy="herbario")
//	private List<Persona> personas;
	private static final long serialVersionUID = 1L;
	
	@Id
	private String idHerbario;

	public HerbarioUQ() {
		super();
	}
   
}

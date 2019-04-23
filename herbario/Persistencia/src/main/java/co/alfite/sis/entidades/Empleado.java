package co.alfite.sis.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Empleado
 *
 */
@Entity

public class Empleado extends Trabajador implements Serializable {

	
	private static final long serialVersionUID = 1L;

	public Empleado() {
		super();
	}
   
}

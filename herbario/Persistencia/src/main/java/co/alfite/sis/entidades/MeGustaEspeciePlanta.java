package co.alfite.sis.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: MeGustaEspeciePlanta
 *
 */
@Entity

public class MeGustaEspeciePlanta implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idMegusta;
	
	private Date fecha;

	public MeGustaEspeciePlanta() {
		super();
	}
   
}

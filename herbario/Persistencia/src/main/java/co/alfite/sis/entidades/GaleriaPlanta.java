package co.alfite.sis.entidades;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: GaleriaPlanta
 *@author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 *@version 1.0
 */
@Entity

public class GaleriaPlanta implements Serializable {

	@Id
	@Column(length = 10)
	private String idGaleria;
	
	private static final long serialVersionUID = 1L;

	public GaleriaPlanta() {
		super();
	}

	public String getIdGaleria() {
		return idGaleria;
	}

	public void setIdGaleria(String idGaleria) {
		this.idGaleria = idGaleria;
	}
   
}

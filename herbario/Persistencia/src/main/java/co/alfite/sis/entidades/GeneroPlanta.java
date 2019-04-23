package co.alfite.sis.entidades;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: EspeciePlanta
 *@author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 *@version 1.0
 */
@Entity

public class GeneroPlanta implements Serializable {

	/**
	 * identificacion unica de un genero
	 */
	@Id
	@Column(length = 10)
	private String idGenero;
	/**
	 * llave foranea: conecta genero con familia
	 */
	@Column(length = 10, nullable = false, unique = true)
	private String idFamilia;
	/**
	 * nombre del generoPlanta
	 */
	@Column(length = 50)
	private String nombre;
	
	private static final long serialVersionUID = 1L;

	public GeneroPlanta() {
		super();
	}   
	public String getIdGenero() {
		return this.idGenero;
	}

	public void setIdGenero(String idGenero) {
		this.idGenero = idGenero;
	}   
	public String getIdFamilia() {
		return this.idFamilia;
	}

	public void setIdFamilia(String idFamilia) {
		this.idFamilia = idFamilia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
   
}

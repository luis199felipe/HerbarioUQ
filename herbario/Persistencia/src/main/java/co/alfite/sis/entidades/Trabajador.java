package co.alfite.sis.entidades;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: FamiliaPlanta
 *@author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 *@version 1.0
 */
@Entity

public class Trabajador implements Serializable {
	
	/**
	 * identificacion unica de un trabajador
	 */
	@Id
	@Column(length = 10)
	private String idTrabajador;
	/**
	 * ¿Herencia? trabajador hereda de persona
	 */
	@Column(length = 10, nullable = false, unique = true)
	private String idPersona;
	/**
	 * password para ingresar al software
	 */
	@Column(length = 12, nullable = false, unique = true)
	private String password;
	/**
	 * email para ingresar al software
	 */
	@Column(length = 50, nullable = false, unique = true)
	private String email;
	
	private static final long serialVersionUID = 1L;

	public Trabajador() {
		super();
	}   
	public String getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}   
	public String getIdTrabajador() {
		return this.idTrabajador;
	}

	public void setIdTrabajador(String idTrabajador) {
		this.idTrabajador = idTrabajador;
	}   
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
   
}

package co.alfite.sis.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: FamiliaPlanta
 * 
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * @version 1.0
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Trabajador extends Persona implements Serializable {

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
	/**
	 * Un Trabajador tiene muchos RegistrosEspecie
	 */
	@OneToMany(mappedBy = "trabajador")
	private List<RegistroEspecie> registros;
	
	private static final long serialVersionUID = 1L;

	public Trabajador() {
		super();
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

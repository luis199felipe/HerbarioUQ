package co.alfite.sis.entidades;

import java.io.Serializable;
import java.util.Date;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Persona
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({@NamedQuery(name=Persona.LISTAR_TODOS,query="select p from Persona p"),
@NamedQuery(name=Persona.PERSONA_POR_CREDENCIALES,query="select p from Persona p where p.email= :email and p.password")})

public class Persona implements Serializable {

	
	public static final String PERSONA_POR_CREDENCIALES="PersonaPorCredenciales";
	
	public static final String LISTAR_TODOS="ListarClientes";
	/**
	 * identificacion unica de una persona
	 */
	@Id
	@Column(length = 10)
	private String idPersona;
	/**
	 * nombre de una persona
	 */
	@Column(length = 50)
	private String nombre;
	/**
	 * telefono de una persona
	 */
	@Column(length = 15)
	private String telefono;
	/**
	 * fecha de nacimiento de una persona
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaNacimiento;
//	/**
//	 * Muchas Persona pertenece aun Herbario
//	 */
//	@ManyToOne
//	private HerbarioUQ herbario;
	
	private static final long serialVersionUID = 1L;

	public Persona() {
		super();
	}

	public String getIdPersona() {
		return this.idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaNacimiento() {
		return this.fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

}

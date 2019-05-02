package co.alfite.sis.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Persona
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({@NamedQuery(name=Persona.LISTAR_TODOS,query="select p from Persona p")})
@NamedQuery(name=Persona.PERSONA_POR_CREDENCIALES,query="select p from Persona p where p.email= :email and p.password=:password")

public class Persona implements Serializable {
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
	
	public static final String PERSONA_POR_CREDENCIALES="PersonaPorCredenciales";
	/**
	 * password para ingresar al software
	 */
	@Column(length = 12, nullable = false)
	private String password;
	/**
	 * email para ingresar al software
	 */
	@Column(length = 50, nullable = false, unique = true)
	private String email;
	/**
	 * Un Trabajador tiene muchos RegistrosEspecie
	 */
	@OneToMany(mappedBy = "persona")
	private List<RegistroEspecie> registros;
	
	public Persona() {
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

	public static String getListarTodos() {
		return LISTAR_TODOS;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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

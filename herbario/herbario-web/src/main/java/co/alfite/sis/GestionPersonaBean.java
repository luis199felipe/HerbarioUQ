package co.alfite.sis;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.ManagedProperty;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Inject;
import javax.inject.Named;

import co.alfite.sis.ejb.AdministradorEJB;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.Usuario;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;
import co.alfite.sis.util.Util;

@FacesConfig(version = Version.JSF_2_3)
@Named("gestionPersonaBean")
@ApplicationScoped
public class GestionPersonaBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String idPersona;

	/**
	 * nombre de una persona
	 */
	private String nombre;

	/**
	 * telefono de una persona
	 */
	private String telefono;

	/**
	 * fecha de nacimiento de una persona
	 */
	private Date fechaNacimiento;

	/**
	 * password para ingresar al software
	 */

	private String password;
	/**
	 * email para ingresar al software
	 */
	private String email;

	private boolean flag;
	private boolean flagEditar;

	private String tipo;

	private Persona personaEditar;
	@EJB
	private AdministradorEJB trabajadorEJB;

	@Inject
	@ManagedProperty(value = "#{seguridadBean.persona}")
	private Persona personaEnSesionEdit;

	/**
	 * 
	 * @param tipo es para identificar si se va a insertar una empleado o un
	 *             recolector
	 */

	@PostConstruct
	public void init() {
		flag = false;
		flagEditar = false;
		personaEditar = new Persona();
	}

	public String registrarPersona() {

		flagEditar=false;
		
		System.out.println("Registra");

		try {
			switch (tipo) {
			case "empleado":
				Empleado e = new Empleado();
				e.setNombre(personaEditar.getNombre());
				e.setEmail(personaEditar.getEmail());
				e.setPassword(personaEditar.getPassword());
				e.setTelefono(personaEditar.getTelefono());
				e.setEstado(co.alfite.sis.entidades.Persona.Estado.activo);
				e.setFechaNacimiento(personaEditar.getFechaNacimiento());
				e.setIdPersona(personaEditar.getIdPersona());

				trabajadorEJB.insertarEmpleado(e);

				break;
			case "recolector":

				Recolector r = new Recolector();
				r.setNombre(personaEditar.getNombre());
				r.setEmail(personaEditar.getEmail());
				r.setPassword(personaEditar.getPassword());
				r.setTelefono(personaEditar.getTelefono());
				r.setEstado(co.alfite.sis.entidades.Persona.Estado.activo);
				r.setFechaNacimiento(personaEditar.getFechaNacimiento());
				r.setIdPersona(personaEditar.getIdPersona());

				trabajadorEJB.insertarRecolector(r);

				break;
				
			case "usuario":

				Usuario u = new Usuario();
				u.setNombre(personaEditar.getNombre());
				u.setEmail(personaEditar.getEmail());
				u.setPassword(personaEditar.getPassword());
				u.setTelefono(personaEditar.getTelefono());
				u.setEstado(co.alfite.sis.entidades.Persona.Estado.activo);
				u.setFechaNacimiento(personaEditar.getFechaNacimiento());
				u.setIdPersona(personaEditar.getIdPersona());

				trabajadorEJB.insertarUsuario(u);

				break;

			default:
				break;
			}

		} catch (Exception e1) {
			e1.printStackTrace();
			
		}
		Util.mostrarMensaje("Bienvenido al herbario de la universidad del Quindio", "Exito");

		return "/index?faces-redirect=true";

	}

	public String editar() {
		flagEditar = true;
		nombre = personaEditar.getNombre();
		idPersona = personaEditar.getIdPersona();
		email = personaEditar.getEmail();
		password = personaEditar.getPassword();
		fechaNacimiento = personaEditar.getFechaNacimiento();
		this.tipo = personaEditar.getClass().getSimpleName().toLowerCase();
		telefono = personaEditar.getTelefono();

		return "/admin/persona/registrar_persona";
	}

	public String editarPersonaEnSesion() {
		personaEditar = personaEnSesionEdit;

		return editar();
	}



	public String actualizarDatos() {

		try {

			Persona p = trabajadorEJB.buscarPersona(idPersona);
			System.out.println(p.getClass().getSimpleName().toLowerCase() + "$#$#$#$");
			switch (p.getClass().getSimpleName().toLowerCase()) {
			case "empleado":
				Empleado e = new Empleado();
				e.setNombre(personaEditar.getNombre());
				e.setEmail(personaEditar.getEmail());
				e.setPassword(personaEditar.getPassword());
				e.setTelefono(personaEditar.getTelefono());
				e.setEstado(co.alfite.sis.entidades.Persona.Estado.activo);
				e.setFechaNacimiento(personaEditar.getFechaNacimiento());
				e.setIdPersona(personaEditar.getIdPersona());

				trabajadorEJB.actualizarEmpleado(e);

				break;
			case "recolector":

				Recolector r = new Recolector();
				r.setNombre(nombre);
				r.setEmail(email);
				r.setPassword(password);
				r.setTelefono(telefono);
				r.setEstado(co.alfite.sis.entidades.Persona.Estado.activo);
				r.setFechaNacimiento(fechaNacimiento);
				r.setIdPersona(idPersona);

				System.out.println(trabajadorEJB.actualizarRecolector(r));

				break;

			default:
				break;
			}

		} catch (Exception e1) {
			e1.printStackTrace();
			Util.mostrarMensaje("No se pudo hacer el registro", "Error");
		}

		return "/index?faces-redirect=true";

	}

	/**
	 * @return the idPersona
	 */
	public String getIdPersona() {
		return idPersona;
	}

	/**
	 * @param idPersona the idPersona to set
	 */
	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Persona getPersonaEditar() {
		return personaEditar;
	}

	public void setPersonaEditar(Persona personaEditar) {
		this.personaEditar = personaEditar;
	}

	public boolean isFlagEditar() {
		return flagEditar;
	}

	public void setFlagEditar(boolean flagEditar) {
		this.flagEditar = flagEditar;
	}

	/**
	 * @return the personaEnSesionEdit
	 */
	public Persona getPersonaEnSesionEdit() {
		return personaEnSesionEdit;
	}

	/**
	 * @param personaEnSesionEdit the personaEnSesionEdit to set
	 */
	public void setPersonaEnSesionEdit(Persona personaEnSesionEdit) {
		this.personaEnSesionEdit = personaEnSesionEdit;
	}
}

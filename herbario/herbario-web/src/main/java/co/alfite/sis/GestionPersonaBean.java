package co.alfite.sis;

import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.alfite.sis.ejb.AdministradorEJB;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.util.Util;

@FacesConfig(version = Version.JSF_2_3)
@Named("registroPersonaBean")
@ApplicationScoped
public class GestionPersonaBean {

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

	@EJB
	private AdministradorEJB trabajadorEJB;

	/**
	 * 
	 * @param tipo es para identificar si se va a insertar una empleado o un
	 *             recolector
	 */
	public void registrarPersona(int tipo) {

		Persona r = new Persona();
		r.setNombre(nombre);
		r.setEmail(email);
		r.setPassword(password);
		r.setTelefono(telefono);
		r.setEstado(co.alfite.sis.entidades.Persona.Estado.activo);
		r.setFechaNacimiento(fechaNacimiento);

		try {
			switch (tipo) {
			case 0:

				Empleado e = (Empleado) r;

				trabajadorEJB.insertarEmpleado(e);
				break;
			case 1:

				Recolector s = (Recolector) r;

				trabajadorEJB.insertarRecolector(s);
				break;

			default:
				break;
			}
			Util.mostrarMensaje("Bienvenido al herbario de la universidad del Quindio", "Exito");

		} catch (Exception e) {
			Util.mostrarMensaje("No se pudo hacer el registro", "Error");
		}

	}

	public void eliminarPersona(int tipo) {

		// se deben limpiar todos los atributos de la clase antas de esto
		try {
			switch (tipo) {
			case 0:

				// porbando la teoria d que solo compara por llave primaria para eliminar
				Empleado x = new Empleado();
				x.setIdPersona(idPersona);
				trabajadorEJB.inactivarEmpleado(x);
				break;
			case 1:

				Recolector r = new Recolector();
				r.setIdPersona(idPersona);
				trabajadorEJB.inactivarRecolector(r);
				break;

			default:
				break;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

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
}

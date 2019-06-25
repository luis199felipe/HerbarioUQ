package co.alfite.sis;

import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import com.sun.faces.util.Util;

import co.alfite.sis.ejb.AdministradorEJB;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;

@FacesConfig(version = Version.JSF_2_3)
@Named("empleadoBean")
@ApplicationScoped
public class EmpleadoBean {

	//@Size(min = 10, max = 15, message = "El tamaño no corresponde")
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
	private AdministradorEJB adminEJB;

	public String registrar() {
		try {
			Empleado e = new Empleado();
			e.setNombre(nombre);
			e.setEmail(email);
			e.setPassword(password);
			e.setIdPersona(idPersona);
			e.setFechaNacimiento(fechaNacimiento);

			adminEJB.insertarEmpleado(e);
		co.alfite.sis.util.Util.mostrarMensaje("exitoso", "exitoso");
		} catch (ElementoRepetidoExcepcion e1) {
			e1.printStackTrace();
		}
		return "listo";
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

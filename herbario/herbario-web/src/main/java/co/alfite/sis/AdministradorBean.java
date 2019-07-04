package co.alfite.sis;

import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.alfite.sis.ejb.AdministradorEJB;
import co.alfite.sis.entidades.Persona.Estado;

@FacesConfig(version = Version.JSF_2_3)
@Named("administradorBean")
@ApplicationScoped
public class AdministradorBean {

	private String idPersona;
	/**
	 * nombre de una persona
	 */
	private String nombre;
	/**
	 * Estado de una persona
	 */
	private Estado estado;
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

	public AdministradorBean() {

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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
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

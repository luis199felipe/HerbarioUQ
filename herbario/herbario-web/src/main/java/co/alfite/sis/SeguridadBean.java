package co.alfite.sis;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.alfite.sis.ejb.AdministradorEJB;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.util.Util;

/**
 * permite manejar la sesion en la pagina web
 * 
 * @author figue
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named("seguridadBean")
@SessionScoped
public class SeguridadBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Persona persona;
	/**
	 * dice si la persona inicio sesion o no
	 */
	private boolean autenticado;
	@EJB
	private AdministradorEJB adminEJB;

	/**
	 * inicializa la informacion base
	 */
	@PostConstruct
	private void init() {
		persona = new Persona();
		autenticado = false;

	}

	public String iniciarSesion() {

		Persona u = adminEJB.personaPorCredenciales(persona.getEmail(), persona.getPassword());
		if (u != null) {
			persona = u;
			System.out.println(persona.getNombre());
			autenticado = true;

			String user = persona.getClass().getSimpleName();

			switch (user) {
			case "Administrador":
				return "admin/contenido_administrador";
			case "Empleado":
				return "admin/contenido_empleado";
			case "Recolector":
				return "admin/contenido_recolector";

			default:
			}

		} else {
			Util.mostrarMensaje("Verifique sus credenciales", "Verifique sus credenciales");
		}

		return "";

	}

	public String olvide() {

		return "admin/registrar_persona";

	}
	public String prueba() {

		System.out.println("puedo redireccionar");
		return "admin/registrar_empleado";
	}

	public String cerrarSesion() {

		persona = new Persona();
		autenticado = false;
		System.out.println("sale");
		return "index?faces-redirect=true";

	}
	
	public String redirect() {

	
		return "admin/contenido_recolector";

	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public boolean isAutenticado() {
		return autenticado;
	}

	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}

}

package co.alfite.sis;

import java.util.Date;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.ManagedProperty;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Inject;
import javax.inject.Named;

import co.alfite.sis.ejb.AdministradorEJB;
import co.alfite.sis.entidades.Administrador;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.RegistroEspecie.Estado;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;
import co.alfite.sis.util.Util;

@FacesConfig(version = Version.JSF_2_3)
@Named("recolectorBean")
@ApplicationScoped
public class RecolectorBean {

	@Inject
	@ManagedProperty(value = "#{seguridadBean.persona}")
	private Administrador recolector;


	//duda con el registro, siemre que la instancia cambie en registro bean cambiara aaqui tambien?

	@Inject
	@ManagedProperty(value = "#{RegistroBean.registroEspecie}")
	private RegistroEspecie registroEspecie;

	@EJB
	private AdministradorEJB trabajadorEJB;

	
	public void hola(int a) {

		System.out.println(a);
	}

	public void actualizarDatos() {

	}

	public void realizarNuevoRegistro() {

		registroEspecie.setTrabajador(recolector);

		trabajadorEJB.insertarRegistro(registroEspecie);

	}

	/**
	 * @return the usuario
	 */
	public Administrador getUsuario() {
		return recolector;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Administrador usuario) {
		this.recolector = usuario;
	}


}

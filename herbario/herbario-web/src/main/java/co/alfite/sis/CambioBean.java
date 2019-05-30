package co.alfite.sis;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

/**
 * Permite realizar cambios de informacion entre dos atributos
 * 
 * @author melis
 *
 */
@FacesConfig(version=Version.JSF_2_3)
@Named(value = "cambioBean")
@ApplicationScoped
public class CambioBean {

	private String atributo1;
	private String atributo2;

	public CambioBean() {
	}

	/**
	 * cambia la info de atributos
	 */
	public void cambiar() {
		String temporal = atributo1;
		atributo1 = atributo2;
		atributo2 = temporal;
	}
	
	public String getAtributo1() {
		return atributo1;
	}

	public void setAtributo1(String atributo1) {
		this.atributo1 = atributo1;
	}

	public String getAtributo2() {
		return atributo2;
	}

	public void setAtributo2(String atributo2) {
		this.atributo2 = atributo2;
	}

}

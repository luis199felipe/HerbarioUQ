package co.alfite.sis;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.alfite.sis.ejb.AdministradorEJB;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;

@FacesConfig(version = Version.JSF_2_3)
@Named("familiaBean")
@ApplicationScoped
public class FamiliaBean {

	private String nombre;
	@EJB
	private AdministradorEJB trabajadorEJB;

	private List<FamiliaPlanta> familias;

	public void registrarFamilia() {

		FamiliaPlanta f = new FamiliaPlanta();
		f.setNombre(nombre);

		try {
			trabajadorEJB.insertarFamilia(f);
		} catch (ElementoRepetidoExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	 * @return the familias
	 */
	public List<FamiliaPlanta> getFamilias() {
		return familias;
	}

	/**
	 * @param familias the familias to set
	 */
	public void setFamilias(List<FamiliaPlanta> familias) {
		this.familias = familias;
	}

}

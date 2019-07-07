package co.alfite.sis;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.alfite.sis.entidades.EspeciePlanta;

@FacesConfig(version=Version.JSF_2_3)
@Named("especieBean")
@ApplicationScoped
public class EspecieBean {
	
	private List<EspeciePlanta> especies;
	private EspeciePlanta especie;
	/**
	 * @return the especies
	 */
	public List<EspeciePlanta> getEspecies() {
		return especies;
	}
	/**
	 * @param especies the especies to set
	 */
	public void setEspecies(List<EspeciePlanta> especies) {
		this.especies = especies;
	}
	/**
	 * @return the especie
	 */
	public EspeciePlanta getEspecie() {
		return especie;
	}
	/**
	 * @param especie the especie to set
	 */
	public void setEspecie(EspeciePlanta especie) {
		this.especie = especie;
	}

}

package co.alfite.sis;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.alfite.sis.ejb.InsertarEJB;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;


@FacesConfig(version = Version.JSF_2_3)
@Named("famliaBean")
@ApplicationScoped
public class FamiliaBean {

	private String nombreFamilia;
	
	/*
	 * Instancia de la capa de negocio
	 */
	@EJB
	private  InsertarEJB insertarEJB;

	
	public String getNombreFamilia() {
		return nombreFamilia;
	}

	public void setNombreFamilia(String nombreFamilia) {
		this.nombreFamilia = nombreFamilia;
	}

	/*
	 * Permite recolectar la info 
	 */
	public String registrar() throws ElementoRepetidoExcepcion {
		
		FamiliaPlanta fam = new FamiliaPlanta();
		fam.setNombre(nombreFamilia);
		insertarEJB.insertarFamilia(fam);
		return "/detalle_familia"; // Hay una pagina web que se llama detalle familia en la raiz de webApp
	}
	
	public String getFammilia() {
		return nombreFamilia;
	}
	
	
	
}

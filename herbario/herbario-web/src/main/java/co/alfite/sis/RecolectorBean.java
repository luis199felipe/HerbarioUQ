package co.alfite.sis;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.GeneroPlanta;

@FacesConfig(version=Version.JSF_2_3)
@Named("recolectorBean")
@ApplicationScoped
public class RecolectorBean {
	
	private List<EspeciePlanta> especies;


}

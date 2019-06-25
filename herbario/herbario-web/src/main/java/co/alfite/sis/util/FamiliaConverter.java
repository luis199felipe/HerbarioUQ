package co.alfite.sis.util;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

import co.alfite.sis.ejb.AdministradorEJB;
import co.alfite.sis.entidades.FamiliaPlanta;


/*
 * Convertidos especfico para una familia de plantas
 */
@FacesConfig(version = Version.JSF_2_3)
@Named(value = "familiaConverter")
@ApplicationScoped
public class FamiliaConverter implements Converter<FamiliaPlanta> {
	
	/*
	 * referencia a la capa de negocio 
	 */
	@EJB
	private AdministradorEJB adminEJB;
	
	@Override
	public FamiliaPlanta getAsObject(FacesContext context, UIComponent component, String value) {

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, FamiliaPlanta value) {
		return value != null ? String.format("%s", value.getIdFamilia()) : "";
	}
}
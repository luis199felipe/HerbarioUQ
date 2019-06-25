package co.alfite.sis;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.alfite.sis.ejb.AdministradorEJB;
import co.alfite.sis.entidades.FamiliaPlanta;

@FacesConfig(version=Version.JSF_2_3)
@Named
@ApplicationScoped
public class GeneroBean {

	private String nombre;
	private FamiliaPlanta familia;
	private List<FamiliaPlanta> familias;
	
	@EJB
	private AdministradorEJB admiEJB;
	
	/**
	 * Constructor
	 */
	public GeneroBean() {
		
	}
	
	/**
	 * inicializa la lista de familias
	 */
	@PostConstruct
	private void init() {
		familias = admiEJB.listarFamilias();
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public FamiliaPlanta getFamilia() {
		return familia;
	}
	public void setFamilia(FamiliaPlanta familia) {
		this.familia = familia;
	}
	public List<FamiliaPlanta> getFamilias() {
		return familias;
	}
	public void setFamilias(List<FamiliaPlanta> familias) {
		this.familias = familias;
	}

	@Override
	public String toString() {
		return "GeneroBean [nombre=" + nombre + "]";
	}
	
	
	
}

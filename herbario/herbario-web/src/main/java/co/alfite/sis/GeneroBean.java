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
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;

@FacesConfig(version=Version.JSF_2_3)
@Named("generoBean")
@ApplicationScoped
public class GeneroBean {

	private String nombre;
	private FamiliaPlanta familia;
	private List<FamiliaPlanta> familias;
	private List<GeneroPlanta> generos;

	
	
	@EJB
	private AdministradorEJB admiEJB;
	
	@PostConstruct
	private void init() {
		familias = admiEJB.listarFamilias();
	}
	
	public String registrar() {
		GeneroPlanta genero = new GeneroPlanta();
		genero.setNombre(nombre);
		genero.setFamiliaPlanta(familia);
		System.out.println("registro");
		try {
			admiEJB.insertarGenero(genero);
		} catch (ElementoRepetidoExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.generos=admiEJB.listarGeneros();
		return "/generos";
	}
	
	/**
	 * inicializa la lista de familias
	 */
	
	
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

	public List<GeneroPlanta> getGeneros() {
		return generos;
	}

	public void setGeneros(List<GeneroPlanta> generos) {
		this.generos = generos;
	}
	
	
	
}

package co.alfite.sis.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: EspeciePlanta
 *@author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 *@version 1.0
 */
@Entity
@NamedQueries({
	@NamedQuery(name=GeneroPlanta.GENERO_GET_ALL ,query="select genero from GeneroPlanta genero"),
	
	@NamedQuery(name=GeneroPlanta.GENERO_GET_ESPECIES ,query="select esp from GeneroPlanta genero, IN(genero.especies) esp where genero.idGenero=:var")
})
public class GeneroPlanta implements Serializable {

	
	public static final String GENERO_GET_ALL = "GeneroGetAll";
	public static final String GENERO_GET_ESPECIES = "GeneroGeteSPECIES";

	
	
	@OneToMany(mappedBy="generoPlanta")
	private List<EspeciePlanta> especies;
	

@ManyToOne
private FamiliaPlanta familiaPlanta;
	/**
	 * identificacion unica de un genero
	 */
	@Id
	@Column(length = 10)
	private String idGenero;
	
	/**
	 * nombre del generoPlanta
	 */
	@Column(length = 50)
	private String nombre;
	
	private static final long serialVersionUID = 1L;

	public GeneroPlanta() {
		super();
	}   
	
		
	
	public List<EspeciePlanta> getEspecies() {
		return especies;
	}



	public void setEspecies(List<EspeciePlanta> especies) {
		this.especies = especies;
	}



	public FamiliaPlanta getFamiliaPlanta() {
		return familiaPlanta;
	}



	public void setFamiliaPlanta(FamiliaPlanta familiaPlanta) {
		this.familiaPlanta = familiaPlanta;
	}



	public String getIdGenero() {
		return this.idGenero;
	}

	public void setIdGenero(String idGenero) {
		this.idGenero = idGenero;
	}   

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
   
}

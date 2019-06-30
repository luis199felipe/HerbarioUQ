package co.alfite.sis.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: EspeciePlanta
 * 
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * @version 1.0
 */
@Entity
@NamedQueries({ @NamedQuery(name = GeneroPlanta.GENERO_GET_ALL, query = "select genero from GeneroPlanta genero"),
		@NamedQuery(name = GeneroPlanta.GENERO_GET_ESPECIES, query = "select esp from GeneroPlanta genero, IN(genero.especies) esp where genero.idGenero=:var"),
		@NamedQuery(name = GeneroPlanta.FAMILIA_MAX, query = "select f, MAX(select COUNT(genero.especies) from GeneroPlanta genero where genero.familiaPlanta.idFamilia=f.idFamilia) from FamiliaPlanta f"),
		@NamedQuery(name = GeneroPlanta.GENERO_GET_NUMBER, query = "SELECT COUNT(p) from GeneroPlanta p "),
		@NamedQuery(name = GeneroPlanta.GENERO_POR_NOMBRE, query = "SELECT p from GeneroPlanta p where p.nombre=:nom") 
})

public class GeneroPlanta implements Serializable {

	public static final String GENERO_GET_ALL = "GeneroGetAll";
	public static final String GENERO_GET_NUMBER = "GeneroCantidad";
	public static final String GENERO_GET_ESPECIES = "GeneroGeteSPECIES";
	public static final String FAMILIA_MAX = "familiamas Especies";
	public static final String FAMILIA_MAX_TWO = "familiamas Especies 2";
	public static final String GENERO_POR_NOMBRE = "GeneroNombre";

	
	@OneToOne
	private RegistroEspecie registro;
	
	@OneToMany(mappedBy = "generoPlanta", orphanRemoval=true)
	private List<EspeciePlanta> especies;

	@ManyToOne
	private FamiliaPlanta familiaPlanta;
	/**
	 * identificacion unica de un genero
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 10)
	private Integer idGenero;

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

	public Integer getIdGenero() {
		return this.idGenero;
	}

	public void setIdGenero(Integer idGenero) {
		this.idGenero = idGenero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public RegistroEspecie getRegistro() {
		return registro;
	}

	public void setRegistro(RegistroEspecie registro) {
		this.registro = registro;
	}

	@Override
	public String toString() {
		return  nombre ;
	}

}

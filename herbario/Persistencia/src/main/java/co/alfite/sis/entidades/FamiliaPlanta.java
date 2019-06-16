package co.alfite.sis.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: FamiliaPlanta
 * 
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * @version 1.0
 */
@Entity
@NamedQueries({ @NamedQuery(name = FamiliaPlanta.FAMILIA_GET_ALL, query = "select familia from FamiliaPlanta familia"),
		@NamedQuery(name = FamiliaPlanta.FAMILIA_GET_NUMBER, query = "SELECT COUNT(p) from FamiliaPlanta p "),
		@NamedQuery(name = FamiliaPlanta.FAMILIA_GET_NUMBER_GENDER, query = "SELECT COUNT(g) from GeneroPlanta g where g.familiaPlanta = :fam "),
		@NamedQuery(name = FamiliaPlanta.FAMILIA_GET_MAX, query = "SELECT  p from FamiliaPlanta p "),
		@NamedQuery(name = FamiliaPlanta.FAMILIA_POR_NOMBRE, query = "SELECT  p from FamiliaPlanta p where p.nombre=:nom") })
public class FamiliaPlanta implements Serializable {

	public static final String FAMILIA_GET_ALL = "FamiliaGetAll";
	public static final String FAMILIA_GET_NUMBER = "FamiliaGetNumber";
	public static final String FAMILIA_GET_NUMBER_GENDER = "FamiliaGetNumberGender";
	public static final String FAMILIA_GET_MAX = "FamiliaGetmax";
	public static final String FAMILIA_MAX_ESP = "familiaMasEspecies";
	public static final String FAMILIA_POR_NOMBRE = "familiaNombre";

	@OneToOne
	private RegistroEspecie registro;

	@ManyToOne
	private HerbarioUQ herbario;

	@OneToMany(mappedBy = "familiaPlanta")
	private List<GeneroPlanta> generos;

	/**
	 * identificacion unica de una familia
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idFamilia;
	/**
	 * nombre de la familiaPlanta
	 */
	@Column(length = 50)
	private String nombre;

	private static final long serialVersionUID = 1L;

	public FamiliaPlanta() {
		super();
	}

	public HerbarioUQ getHerbario() {
		return herbario;
	}

	public void setHerbario(HerbarioUQ herbario) {
		this.herbario = herbario;
	}

	public Integer getIdFamilia() {
		return this.idFamilia;
	}

	public void setIdFamilia(Integer idFamilia) {
		this.idFamilia = idFamilia;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<GeneroPlanta> getGeneros() {
		return generos;
	}

	public void setGeneros(List<GeneroPlanta> generos) {
		this.generos = generos;
	}

	public RegistroEspecie getRegistro() {
		return registro;
	}

	public void setRegistro(RegistroEspecie registro) {
		this.registro = registro;
	}

}

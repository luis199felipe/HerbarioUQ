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
		@NamedQuery(name = FamiliaPlanta.FAMILIA_GET_NUMBER, query = "SELECT COUNT(p) from FamiliaPlanta p ") })
public class FamiliaPlanta implements Serializable {

	public static final String FAMILIA_GET_ALL = "FamiliaGetAll";
	public static final String FAMILIA_GET_NUMBER = "FamiliaGetNumber";
	public static final String FAMILIA_MAX_ESP= "familiaMasEspecies";

	@ManyToOne
	private HerbarioUQ herbario;

	@OneToMany(mappedBy = "familiaPlanta")
	private List<GeneroPlanta> generos;

	/**
	 * identificacion unica de una familia
	 */
	@Id
	@Column(length = 10)
	private String idFamilia;
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

	public String getIdFamilia() {
		return this.idFamilia;
	}

	public void setIdFamilia(String idFamilia) {
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

}

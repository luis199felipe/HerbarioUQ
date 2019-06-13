package co.alfite.sis.entidades;

import java.io.Serializable;
import java.lang.Integer;
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
@NamedQueries({ @NamedQuery(name = EspeciePlanta.ESPECIE_GET_ALL, query = "select especie from EspeciePlanta especie"),
		@NamedQuery(name = EspeciePlanta.ESPECIES_ESTADO, query = "SELECT especie FROM EspeciePlanta especie where especie.registro.estado = :est "),
		@NamedQuery(name = EspeciePlanta.ESPECIES_GENERO, query = "SELECT especie FROM EspeciePlanta especie where especie.generoPlanta.idGenero= :gen "),
		@NamedQuery(name = EspeciePlanta.ESPECIES_FAMILIA, query = "SELECT especie FROM EspeciePlanta especie where especie.generoPlanta.familiaPlanta.idFamilia = :fam "),
		@NamedQuery(name = EspeciePlanta.FAMILIA_MAX_ESPECIE, query = "select max(select esp from GeneroPlanta genero, IN(genero.especies) esp) from FamiliaPlanta f"),
		@NamedQuery(name = EspeciePlanta.ESPECIES_FAMILIA_ID, query = "SELECT especie.generoPlanta.familiaPlanta FROM EspeciePlanta especie where especie.idEspecie = :idEspecie "),
		@NamedQuery(name = EspeciePlanta.ESPECIES_GET_NUMBER, query = "SELECT count(especie) FROM EspeciePlanta especie "),
		@NamedQuery(name = EspeciePlanta.ESPECIES_POR_NOMBRE_FAMILIA, query = "SELECT especie FROM EspeciePlanta especie where especie.generoPlanta.familiaPlanta.nombre = :nombreFam "),

		@NamedQuery(name = EspeciePlanta.ESPECIES_GENERO_NOMBRE, query = "SELECT especie FROM EspeciePlanta especie where especie.generoPlanta.nombre= :gen "),
		@NamedQuery(name = EspeciePlanta.ESPECIES_FAMILIA_NOMBRE, query = "SELECT especie FROM EspeciePlanta especie where especie.generoPlanta.familiaPlanta.nombre = :fam "),
		@NamedQuery(name = EspeciePlanta.ESPECIES_POR_ID, query = "SELECT especie FROM EspeciePlanta especie where especie.idEspecie= :id "),
		@NamedQuery(name = EspeciePlanta.ESPECIES_POR_NOMBRECIENTIFICO, query = "SELECT especie FROM EspeciePlanta especie where especie.nombreCientifico= :nomCien ")

})

public class EspeciePlanta implements Serializable {

	public static final String ESPECIE_GET_ALL = "EspecieGetAll";
	public static final String ESPECIES_ESTADO = "EspeciesEstado";
	public static final String ESPECIES_GENERO = "EspeciesGenero";
	public static final String ESPECIES_FAMILIA = "EspeciesFamilia";
	public static final String ESPECIES_FAMILIA_ID = "EspeciesFamiliaPrID";
	public static final String FAMILIA_MAX_ESPECIE = "familiaMasEspecies";
	public static final String FAMILIA_MAX = "familiaMas";
	public static final String ESPECIES_GET_NUMBER = "familiaMas";
	public static final String ESPECIES_POR_NOMBRE_FAMILIA = "especiesPorNombreFamilia";
	public static final String ESPECIES_POR_ID = "especiesPorID";
	public static final String ESPECIES_POR_NOMBRECIENTIFICO = "especiesPorNombreCientifico";
	public static final String ESPECIES_GENERO_NOMBRE = "EspeciesGeneroListarPorNombreFamilia";
	public static final String ESPECIES_FAMILIA_NOMBRE = "EspeciesFamiliaListarPorNombreGenero";

	/**
	 * Una EspeciePlanta tiene muchas resenias de Usuarios
	 */
	@OneToMany(mappedBy = "especie")
	private List<Resenia> resenias;

	/**
	 * Una EspeciePlanta tiene muchas MeGusta de Usuarios
	 */
	@OneToMany(mappedBy = "especie")
	private List<MeGustaEspeciePlanta> megustas;

	/**
	 * Una EspeciePlanta tiene un RegistroEspecie
	 */
	@OneToOne
	private RegistroEspecie registro;

	/**
	 * Muchas EspeciePlanta pertenece a un GeneroPlanta
	 */
	@ManyToOne
	private GeneroPlanta generoPlanta;

	/**
	 * Una EspeciePlanta tiene muchas ImagenesPlanta (1..*)
	 */
//	@OneToMany(mappedBy = "especie")
//	private List<ImagenPlanta> imagenes;

	/**
	 * identificacion unica de una EspeciePlanta
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(length = 10)
	private int idEspecie;

	/**
	 * nombre cientifico de una EspeciePlanta
	 */
	@Column(length = 50)
	private String nombreCientifico;
	/**
	 * nombre de una EspeciePlanta
	 */
	@Column(length = 50)
	private String nombre;
	/**
	 * cantidad de una EspeciePlanta en el herbario
	 */

	private static final long serialVersionUID = 1L;

	public EspeciePlanta() {
		super();
	}

	public RegistroEspecie getRegistro() {
		return registro;
	}

	public void setRegistro(RegistroEspecie registro) {
		this.registro = registro;
	}

	public GeneroPlanta getGeneroPlanta() {
		return generoPlanta;
	}

	public void setGeneroPlanta(GeneroPlanta generoPlanta) {
		this.generoPlanta = generoPlanta;
	}

//	public List<ImagenPlanta> getImagenes() {
//		return imagenes;
//	}
//
//	public void setImagenes(List<ImagenPlanta> imagenes) {
//		this.imagenes = imagenes;
//	}

	public List<Resenia> getResenias() {
		return resenias;
	}

	public void setResenias(List<Resenia> resenias) {
		this.resenias = resenias;
	}

	public List<MeGustaEspeciePlanta> getMegustas() {
		return megustas;
	}

	public void setMegustas(List<MeGustaEspeciePlanta> megustas) {
		this.megustas = megustas;
	}

	public int getIdEspecie() {
		return this.idEspecie;
	}

	public void setIdEspecie(int idEspecie) {
		this.idEspecie = idEspecie;
	}

	public String getNombreCientifico() {
		return this.nombreCientifico;
	}

	public void setNombreCientifico(String nombreCientifico) {
		this.nombreCientifico = nombreCientifico;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}

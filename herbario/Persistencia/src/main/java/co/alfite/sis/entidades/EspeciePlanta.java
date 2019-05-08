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
@NamedQueries({
	@NamedQuery(name=EspeciePlanta.ESPECIE_GET_ALL ,query="select especie from EspeciePlanta especie"),
	@NamedQuery(name=EspeciePlanta.ESPECIES_ACEPTADAS ,query="SELECT especie FROM EspeciePlanta especie where especie.registro.estado = :est "),
	@NamedQuery(name=EspeciePlanta.ESPECIES_GENERO ,query="SELECT especie FROM EspeciePlanta especie where especie.generoPlanta.idGenero=:gen ")
})
public class EspeciePlanta implements Serializable {
	
	public static final String ESPECIE_GET_ALL = "EspecieGetAll";
	public static final String ESPECIES_ACEPTADAS = "EspeciesAceptadas";
	public static final String ESPECIES_GENERO= "EspeciesGenero";

	
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
	@OneToMany(mappedBy = "especie")
	private List<ImagenPlanta> imagenes;

	/**
	 * identificacion unica de una EspeciePlanta
	 */
	@Id
	@Column(length = 10)
	private String idEspecie;

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
	private Integer cantidad;

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

	public List<ImagenPlanta> getImagenes() {
		return imagenes;
	}

	public void setImagenes(List<ImagenPlanta> imagenes) {
		this.imagenes = imagenes;
	}

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

	public String getIdEspecie() {
		return this.idEspecie;
	}

	public void setIdEspecie(String idEspecie) {
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

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

}

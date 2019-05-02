package co.alfite.sis.entidades;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: EspeciePlanta
 *@author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 *@version 1.0
 */
@Entity

public class EspeciePlanta implements Serializable {

	@OneToOne
	private RegistroEspecie registro;
	
	@ManyToOne
	private GeneroPlanta generoPlanta;
	/**
	 * identificacion unica de una especie
	 */
	@Id
	@Column(length = 10)
	private String idEspecie;

	/**
	 * nombre cientifico de una especie
	 */
	@Column(length = 50)
	private String nombreCientifico;
	/**
	 * nombre de una especie
	 */
	@Column(length = 50)
	private String nombre;
	/**
	 * cantidad de una especie en el herbario
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

package co.alfite.sis.entidades;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: FamiliaPlanta
 *@author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 *@version 1.0
 */
@Entity

public class RegistroEspecie implements Serializable {
	
	
	
	@ManyToOne
	private Trabajador trabajador;
	
	@OneToOne(mappedBy="registro")
	private EspeciePlanta especie;
	
	/**
	 * identificacion unica de un registro
	 */
	@Id
	//@Column(auto_increment)
	private Integer idRegistro;
	/**
	 * llave foranea: conecta registro con familiaPlanta
	 */
	@Column(length = 10, nullable = false, unique = true)
	private String idFamilia;
	/**
	 * llave foranea: conecta registro con generoPlanta
	 */
	@Column(length = 10, nullable = false, unique = true)
	private String idGenero;
	/**
	 * llave foranea: conecta registro con especiePlanta
	 */
	@Column(length = 10, nullable = false, unique = true)
	private String idEspecie;   
	/**
	 * estado del registro (enviado, aprobado, rechazado)
	 */
	private enum Estado{
		enviado,aprovado,rechazado
	}
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private Estado estado;
	/**
	 * mensaje sobre el registro
	 */
	@Column(length = 100)
	private String mensaje;
	
	private static final long serialVersionUID = 1L;

	public RegistroEspecie() {
		super();
	}   
	public String getIdFamilia() {
		return this.idFamilia;
	}

	public void setIdFamilia(String idFamilia) {
		this.idFamilia = idFamilia;
	}   
	public String getIdGenero() {
		return this.idGenero;
	}

	public void setIdGenero(String idGenero) {
		this.idGenero = idGenero;
	}   
	public String getIdEspecie() {
		return this.idEspecie;
	}

	public void setIdEspecie(String idEspecie) {
		this.idEspecie = idEspecie;
	}   
	public Integer getIdRegistro() {
		return this.idRegistro;
	}

	public void setIdRegistro(Integer idRegistro) {
		this.idRegistro = idRegistro;
	}   
	public Estado getEstado() {
		return this.estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}   
	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
   
}

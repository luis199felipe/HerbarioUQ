package co.alfite.sis.entidades;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: FamiliaPlanta
 * 
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * @version 1.0
 */
@Entity
@NamedQueries({
	@NamedQuery(name=RegistroEspecie.REGISTRO_GET_ALL ,query="select registro from RegistroEspecie registro")
})
public class RegistroEspecie implements Serializable {

	public static final String REGISTRO_GET_ALL = "RegistroGetAll";

	/**
	 * Muchos Registros pertenecen a un Trabajador
	 */
	@ManyToOne
	private Trabajador trabajador;

	/**
	 * Un registro tiene una Especie
	 */
	@OneToOne(mappedBy = "registro")
	private EspeciePlanta especie;

	/**
	 * identificacion unica de un registro
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idRegistro;

	/**
	 * estado del registro (enviado, aprobado, rechazado)
	 */
	public enum Estado {
		enviado, aprobado, rechazado
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

	public Trabajador getTrabajador() {
		return trabajador;
	}

	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
	}

	public EspeciePlanta getEspecie() {
		return especie;
	}

	public void setEspecie(EspeciePlanta especie) {
		this.especie = especie;
	}

}

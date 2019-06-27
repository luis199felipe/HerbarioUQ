package co.alfite.sis.entidades;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: FamiliaPlanta
 * 
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * @version 1.0
 */
@Entity
@NamedQueries({
		@NamedQuery(name = RegistroEspecie.REGISTRO_GET_ALL, query = "select registro from RegistroEspecie registro"),
		@NamedQuery(name = RegistroEspecie.TRABAJADOR_GET_ENVIOS, query = "select count(registro) from RegistroEspecie registro where (registro.estado=:est) group by registro.fecha  "),
		@NamedQuery(name = RegistroEspecie.REGISTRO_MAX, query = "select max(registro.fecha) from RegistroEspecie registro "),
		@NamedQuery(name = RegistroEspecie.TRABAJADOR_WITH_REGISTERS, query = "select distinct registro.trabajador from RegistroEspecie registro "),
		@NamedQuery(name = RegistroEspecie.REGISTRO_FECHA, query = "select registro.idRegistro,registro.especie.nombre, registro.especie.generoPlanta.nombre, registro.trabajador.email, registro.trabajador.idPersona from RegistroEspecie registro where registro.fecha=:fecha"),
		@NamedQuery(name = RegistroEspecie.REGISTRO_ESTADO_TRABAJADOR, query = "select registro.trabajador from RegistroEspecie registro where (registro.estado=:est and registro.trabajador.idPersona=:per)"),
		@NamedQuery(name = RegistroEspecie.REGISTRO_POR_ID, query = "select registro from RegistroEspecie registro where registro.idRegistro=:id"),
		@NamedQuery(name = RegistroEspecie.REGISTRO_POR_ESTADO, query = "select registro from RegistroEspecie registro where registro.estado=:est")

})
public class RegistroEspecie implements Serializable {

	public static final String REGISTRO_GET_ALL = "RegistroGetAll";
	public static final String REGISTRO_ESTADO_TRABAJADOR = "registrosTrabajadorPorEstados";
	public static final String TRABAJADOR_GET_REGISTERS = "registrosTrabajador";
	public static final String TRABAJADOR_GET_ENVIOS = "cantidadRegistrosDia";
	public static final String REGISTRO_MAX = "PRUEBA AL MAX";
	public static final String TRABAJADOR_WITH_REGISTERS = "TrabajadorConRegistros";
	public static final String REGISTRO_FECHA = "RegistroPorFecha";
	public static final String REGISTRO_FECHA_DTO = "RegistroPorFechaDTO";
	public static final String REGISTRO_POR_ID = "RegistroPorID";
	public static final String REGISTRO_POR_ESTADO = "RegistroPorEstado";

	/**
	 * Muchos Registros pertenecen a un Trabajador
	 */
	@ManyToOne
	private Trabajador trabajador;

	@Column(length = 10)
	private String nombreEspecie;

	@Column(length = 10)
	private String nombreGenero;

	@Column(length = 10)
	private String nombreFamilia;
	/**
	 * Un registro tiene una Especie
	 */
	@OneToOne(mappedBy = "registro")
	private EspeciePlanta especie;

	@OneToOne(mappedBy = "registro")
	private ImagenPlanta imagen;
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

	/**
	 * fecha del registro
	 */
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public ImagenPlanta getImagenPlanta() {
		// TODO Auto-generated method stub
		return imagen;
	}

	public ImagenPlanta getImagen() {
		return imagen;
	}

	public void setImagen(ImagenPlanta imagen) {
		this.imagen = imagen;
	}

	public String getNombreFamilia() {
		return nombreFamilia;
	}

	public void setNombreFamilia(String nombreFamilia) {
		this.nombreFamilia = nombreFamilia;
	}

	public String getNombreEspecie() {
		return nombreEspecie;
	}

	public void setNombreEspecie(String nombreEspecie) {
		this.nombreEspecie = nombreEspecie;
	}

	public String getNombreGenero() {
		return nombreGenero;
	}

	public void setNombreGenero(String nombreGenero) {
		this.nombreGenero = nombreGenero;
	}

}

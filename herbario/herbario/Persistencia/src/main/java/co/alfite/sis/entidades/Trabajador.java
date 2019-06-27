package co.alfite.sis.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Trabajador
 * 
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * @version 1.0
 */

@NamedQueries({
		@NamedQuery(name = Trabajador.TRABAJADOR_GET_ALL, query = "select trabajador from Trabajador trabajador"),
		@NamedQuery(name = Trabajador.TRABAJADOR_GET_EMPTY_REGISTERS, query = "select trabajador from Trabajador trabajador where trabajador.registros IS EMPTY or (NOT NUll=false)"),
		@NamedQuery(name = Trabajador.TRABAJADOR_GET_REGISTERS, query = "select registro from Trabajador trabajador INNER JOIN trabajador.registros registro where trabajador.idPersona=:var"),
		@NamedQuery(name = Trabajador.ALL_TRABAJADOR_GET_REGISTERS, query = "select registro, registro.trabajador.idPersona from Trabajador trabajador LEFT JOIN trabajador.registros registro") })

@Entity
public class Trabajador extends Persona implements Serializable {

	/**
	 * Entidad que hereda de Persona
	 */

	/**
	 * Un Trabajador tiene muchos RegistrosEspecie
	 */

	@OneToMany(mappedBy = "trabajador")
	private List<RegistroEspecie> registros;

	private static final long serialVersionUID = 1L;

	public static final String TRABAJADOR_GET_ALL = "TrabajadorGetAll";
	public static final String TRABAJADOR_GET_EMPTY_REGISTERS = "TrabajadorSinRegistros";
	public static final String TRABAJADOR_GET_REGISTERS = "RegistrosTrabajador";
	public static final String ALL_TRABAJADOR_GET_REGISTERS = "RegistrosTrabajadorAsociados";

	public Trabajador() {
		super();
	}

	public List<RegistroEspecie> getRegistros() {
		return registros;
	}

	public void setRegistros(List<RegistroEspecie> registros) {
		this.registros = registros;
	}

}

package co.alfite.sis.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Empleado
 *
 */
@Entity
@NamedQueries({
	@NamedQuery(name=Empleado.EMPLEADO_GET_ALL ,query="select empleado from Empleado empleado")
})
public class Empleado extends Persona  implements Serializable {

	/**
	 * Entidad que hereda de Persona , no tiene campos propios
	 */
	private static final long serialVersionUID = 1L;
	
	/**Constantequeidentificalaconsultaquepermitebuscaruna
	 * {@link Persona}porsunui (Numero Unico de Identificación)
	 * {@code select personafrom Personapersonawhere persona.nui= :nui}
	 */
	public static final String EMPLEADO_GET_ALL = "EmpleadoGetAll";
	
	public Empleado() {
		super();
	}
	
	public Empleado(String idPersona, String nombre, String telefono, Date fechaNacimiento, String password,
			String email) {
		super(idPersona, nombre, telefono, fechaNacimiento, password, email);
	}
}

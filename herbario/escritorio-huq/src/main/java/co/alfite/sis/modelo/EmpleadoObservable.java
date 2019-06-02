package co.alfite.sis.modelo;

import java.util.Date;

import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.Persona;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Permite transformar una persona en formato observable
 * 
 * @author EinerZG
 * @version 1.0
 */
public class EmpleadoObservable {

	/**
	 * cedula observable de un empleado
	 */
	private StringProperty cedula;
	/**
	 * nombre observable de una persona
	 */
	private StringProperty nombre;
	/**
	 * apellido observable de un empleado
	 */
	private StringProperty apellido;
	/**
	 * email observable de un empleado
	 */
	private StringProperty email;
	/**
	 * clave observable de un empleado
	 */
	private StringProperty clave;
	/**
	 * fecha de nacimiento observable de un empleado
	 */
	private ObjectProperty<Date> fechaNacimiento;
	/**
	 * empleado asociado
	 */
	private Empleado empleado;

	/**
	 * 
	 * @param cedula
	 * @param nombre
	 */
	public EmpleadoObservable(String cedula, String nombre) {

		this.cedula = new SimpleStringProperty(cedula);
		this.nombre = new SimpleStringProperty(nombre);

		this.apellido = new SimpleStringProperty("Algo");
		this.email = new SimpleStringProperty("algo@mail.com");
		this.clave = new SimpleStringProperty("12345");
		this.fechaNacimiento = new SimpleObjectProperty<>(new Date());

	}

	/**
	 * constructor que genera con empleado observable con base a un empleado
	 * 
	 * @param empleado que se quiere volver observable
	 */
	public EmpleadoObservable(Persona empleado) {

//		this.empleado = (Empleado) empleado;
//		this.cedula = new SimpleStringProperty(empleado.getCedula());
//		this.nombre = new SimpleStringProperty(empleado.getNombre());
//		this.apellido = new SimpleStringProperty(empleado.getApellido());
//		this.email = new SimpleStringProperty(empleado.getEmail());
//		this.clave = new SimpleStringProperty(empleado.getClave());
//		this.fechaNacimiento = new SimpleObjectProperty<>(empleado.getFechaNacimiento());

	}

	/**
	 * permite generar una instanci usando todos los empleados
	 * 
	 * @param cedula
	 * @param nombre
	 * @param apellido
	 * @param email
	 * @param clave
	 * @param fecha
	 */
	public EmpleadoObservable(String cedula, String nombre, String apellido, String email, String clave, Date fecha) {

		this.cedula = new SimpleStringProperty(cedula);
		this.nombre = new SimpleStringProperty(nombre);
		this.apellido = new SimpleStringProperty(apellido);
		this.email = new SimpleStringProperty(email);
		this.clave = new SimpleStringProperty(clave);
		this.fechaNacimiento = new SimpleObjectProperty<>(fecha);

	}

	/**
	 * @return the cedula
	 */
	public StringProperty getCedula() {
		return cedula;
	}

	/**
	 * @param cedula the cedula to set
	 */
	public void setCedula(StringProperty cedula) {
		this.cedula = cedula;
	}

	/**
	 * @return the nombre
	 */
	public StringProperty getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(StringProperty nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the apellido
	 */
	public StringProperty getApellido() {
		return apellido;
	}

	/**
	 * @param apellido the apellido to set
	 */
	public void setApellido(StringProperty apellido) {
		this.apellido = apellido;
	}

	/**
	 * @return the email
	 */
	public StringProperty getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(StringProperty email) {
		this.email = email;
	}

	/**
	 * @return the clave
	 */
	public StringProperty getClave() {
		return clave;
	}

	/**
	 * @param clave the clave to set
	 */
	public void setClave(StringProperty clave) {
		this.clave = clave;
	}

	/**
	 * @return the fechaNacimiento
	 */
	public ObjectProperty<Date> getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @param fechaNacimiento the fechaNacimiento to set
	 */
	public void setFechaNacimiento(ObjectProperty<Date> fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	/**
	 * @return the empleado
	 */
	public Empleado getEmpleado() {
		return empleado;
	}

	/**
	 * @param empleado the empleado to set
	 */
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

}

package co.alfite.sis.controlador;

import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.modelo.EmpleadoObservable;
import co.alfite.sis.util.Utilidades;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Permite controlar la vista editar_empleado
 * 
 * @author EinerZG version 1.0
 */
public class EdicionEmpleadoControlador {

	/**
	 * campo para la cedula
	 */
	@FXML
	private TextField cmpCedula;
	/**
	 * campo para el nombre
	 */
	@FXML
	private TextField cmpNombre;
	/**
	 * campo para el apellido
	 */
	@FXML
	private TextField cmpApellido;
	/**
	 * campo para el email
	 */
	@FXML
	private TextField cmpEmail;
	/**
	 * campo para la calve
	 */
	@FXML
	private TextField cmpClave;
	/**
	 * campo para la fecha de nacimiento
	 */
	@FXML
	private DatePicker cmpFechaNacimiento;
	/**
	 * representa el escenario en donde se agrega la vista
	 */
	private Stage escenarioEditar;
	/**
	 * instancia del manejador de los escenario
	 */
	private ManejadorEscenarios manejador;

	/**
	 * 
	 */
	@FXML
	private void initialize() {

	}

	/**
	 * permite cargar la informacion de un persona para realizar una edicion
	 * 
	 * @param empleado empleado a editar
	 */
	public void cargarPersona(EmpleadoObservable empleado) {

		cmpCedula.setText(empleado.getCedula().getValue());
		cmpNombre.setText(empleado.getNombre().getValue());
		cmpApellido.setText(empleado.getApellido().getValue());
		cmpEmail.setText(empleado.getEmail().getValue());
		cmpClave.setText(empleado.getClave().getValue());
		cmpFechaNacimiento.setValue(Utilidades.pasarALocalDate(empleado.getFechaNacimiento().getValue()));

	}

	/**
	 * permite registrar una persona en la base de datos
	 */
	@FXML
	public void registrarPersona() {

//		Empleado persona = new Empleado();
//		persona.setCedula(cmpCedula.getText());
//		persona.setNombre(cmpNombre.getText());
//		persona.setApellido(cmpApellido.getText());
//		persona.setClave(cmpClave.getText());
//		persona.setEmail(cmpEmail.getText());
//		persona.setFechaNacimiento(Utilidades.pasarADate(cmpFechaNacimiento.getValue()));
//
//		if (manejador.registrarEmpleado(persona)) {
//			manejador.agregarALista(persona);
//			Utilidades.mostrarMensaje("Registro", "Registro exitoso!!");
//			escenarioEditar.close();
//		} else {
//			Utilidades.mostrarMensaje("Registro", "Error en registro!!");
//		}
	}

	/**
	 * permite editar la informacion de una persona
	 */
	@FXML
	private void modificar() {
		// TODO terminar modificar empleado
		escenarioEditar.close();
	}

	/**
	 * permite cerrar la ventana de editar y crear
	 */
	@FXML
	private void cancelar() {
		escenarioEditar.close();
	}

	/**
	 * permite cargar el manejador de escenarios
	 * 
	 * @param manejador
	 */
	public void setManejador(ManejadorEscenarios manejador) {
		this.manejador = manejador;
	}

	/**
	 * permite modificar el escenario
	 * 
	 * @param escenarioEditar
	 */
	public void setEscenarioEditar(Stage escenarioEditar) {
		this.escenarioEditar = escenarioEditar;
	}

}

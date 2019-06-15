package co.alfite.sis.controlador;

import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class VistaTrabajadorMenuControlador {

	@FXML
	private Button botonGestionarEmpleado;
	@FXML
	private Button botonGestionarRecolector;
	@FXML
	private Button botonGestionarFamilia;
	@FXML
	private Button botonGestionarGenero;
	@FXML
	private Button botonGestionarEspecie;
	@FXML
	private Button botonGestionaRegistro;
	@FXML
	private Button botonBuscar;
	@FXML
	private TextField campoBuscar;
	private ManejadorEscenarios miEscenario;
	private BorderPane pane;

	@FXML
	private void initialize() {

	}

	/**
	 * lanza la vista para gestionar Familias
	 */
	@FXML
	private void gestionarFamilias() {
		miEscenario.iniciarVistaGestionarFamilia(pane);
	}

	/**
	 * lanza la vista para gestionar Generos
	 */
	@FXML
	private void gestionarGeneros() {
		miEscenario.iniciarVistaGestionarGenero(pane);
	}

	/**
	 * lanza la vista para gestionar Especies
	 */

	@FXML
	private void gestionarEspecies() {
		miEscenario.iniciarVistaGestionarEspecie(pane);
	}

	/**
	 * lanza la vista para gestionar Recolectores
	 */

	@FXML
	private void gestionarRecolectores() {

		miEscenario.iniciarVistaGestionar(pane, "recolector");

	}

	/**
	 * lanza la vista para gestionar Empleados
	 */
	@FXML
	private void gestionarEmpleados() {
		miEscenario.iniciarVistaGestionar(pane, "empleado");
	}

	/**
	 * lanza la vista para gestionar Registros
	 */
	@FXML
	private void gestionarRegistros() {

		miEscenario.iniciarVistaValidarRegistros(pane);
	}

	@FXML
	private void listarEspecies() {

	}

	/**
	 * define el manejador de escenarios para esta clase
	 */
	public void setManejador(ManejadorEscenarios manejadorEscenarios) {
		this.miEscenario = manejadorEscenarios;

	}

	/**
	 * transporta la vista Raiz sesion hasta esta clase para tener control sobre la
	 * misma
	 */
	public void setPanePrincipal(BorderPane pane) {

		this.pane = pane;

	}

}

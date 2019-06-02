package co.alfite.sis.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VistaGestionarPersonaControlador {

	@FXML
	private TextField campoNombre;
	@FXML
	private TextField campoFechaNacimiento;
	@FXML
	private TextField campoCorreo;
	@FXML
	private TextField campoCedula;
	@FXML
	private TextField campoTelefono;
	@FXML
	private Button botonInvalidar;
	@FXML

	private TextField campoCargo;
	private ManejadorEscenarios manejador;
	private Stage miEscenario;

	@FXML
	private void initialize() {

	}

	@FXML
	public void invalidarPersona() {

	}

	public void setManejador(ManejadorEscenarios m) {
		this.manejador = m;
	}

	public void setStage(Stage stage) {
		this.miEscenario = stage;

	}

}

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

	@FXML
	private void gestionarFamilias() {
		miEscenario.iniciarVistaGestionarGFE(pane,"familia");
	}

	@FXML
	private void gestionarGeneros() {
		miEscenario.iniciarVistaGestionarGFE(pane,"genero");
	}

	@FXML
	private void gestionarEspecies() {
		miEscenario.iniciarVistaGestionarGFE(pane,"especie");
	}

	@FXML
	private void gestionarRecolectores() {

		miEscenario.iniciarVistaGestionar(pane);

	}

	@FXML
	private void gestionarEmpleados() {
		miEscenario.iniciarVistaGestionar(pane);
	}

	@FXML
	private void gestionarRegistros() {

		miEscenario.iniciarVistaValidarRegistros(pane);
	}

	@FXML
	private void listarEspecies() {

	}

	public void setManejador(ManejadorEscenarios manejadorEscenarios) {
		this.miEscenario = manejadorEscenarios;

	}

	public void setStage(Stage escenario) {

	}

	public void setPanePrincipal(BorderPane pane) {

		this.pane = pane;

	}


}

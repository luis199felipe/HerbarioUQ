package co.alfite.sis.controlador;

import co.alfite.sis.entidades.Persona;
import co.alfite.sis.util.Utilidades;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class VistaSesionRaizControlador {
	@FXML
	private SplitMenuButton control;
	@FXML
	private MenuItem mnCerrarSesion;
	@FXML
	private MenuItem mnActualizarInf;

	@FXML
	private Button botonHome;

	private ManejadorEscenarios manejador;
	private Stage miEscenario;
	private BorderPane pane;
	private Persona personaEnSesion;

	@FXML
	private void initialize() {

	}

	@FXML
	private void cerrarSesion() {

		// Utilidades.mostrarMensaje("HerbarioUQ", "esta seguro de
		// salir",AlertType.CONFIRMATION);
		manejador.escenarioInicial();
		miEscenario.close();
	}

	@FXML
	private void actualizarInformacion() {

		manejador.cargarEscenarioRegistro();
	}

	@FXML
	private void irAHome() {
	
		manejador.iniciarVistaMenuTrabajador(pane);
	}

	public void cargarMenu() {
		manejador.iniciarVistaMenuTrabajador(pane);
	}

	public void setManejador(ManejadorEscenarios manejadorEscenarios) {
		this.manejador = manejadorEscenarios;

	}

	public void setStage(BorderPane pane) {
		this.pane = pane;

	}

	public void setStage(Stage stage) {
		this.miEscenario = stage;

	}

	public void setPersonaEnSesion(Persona p) {
		this.personaEnSesion=p;
		
		control.setText(p.getNombre());
	}
}

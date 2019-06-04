package co.alfite.sis.controlador;

import co.alfite.sis.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class VistaLoginControlador {

	@FXML
	private TextField campoCorreo;
	@FXML
	private PasswordField campoContrasenia;
	@FXML
	private ProgressIndicator spinerCarga;
	@FXML
	private Button botonIniciar;
	@FXML
	private ImageView logoHerbario;
	@FXML
	private ComboBox<String> comboBoxCargo;
	@FXML
	private ObservableList<String> listaItems;

	private ManejadorEscenarios manejador;
	private Stage miEscenario;

	public VistaLoginControlador() {
		
		
	}

	@FXML
	private void initialize() {

		listaItems = FXCollections.observableArrayList();
		listaItems.add("Administrador");
		listaItems.add("Usuario");
		comboBoxCargo.setItems(listaItems);

	}

	@FXML
	private void iniciarSesion() {
		manejador.cargarEscenarioTrabajador();
		miEscenario.close();
	}

	@FXML
	private void registrarTrabajador() {

		manejador.cargarEscenarioRegistro();
		miEscenario.close();

	}

	@FXML
	private void recuperarContrasenia() {


	}

	public void setManejador(ManejadorEscenarios m) {

		this.manejador = m;

	}

	public void setStage(Stage escenario) {
		this.miEscenario = escenario;

	}
}

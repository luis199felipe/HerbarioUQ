package co.alfite.sis.controlador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

public class LoginVistaControlador {

	@FXML
	private TextField campoCorreo;
	@FXML
	private PasswordField campoContrasenia;
	@FXML
	private ProgressIndicator spinerCarga;
	@FXML
	private Button botonIniciar;
	@FXML
	private ComboBox<String> comboBoxCargo;
	@FXML
	private ObservableList<String> listaItems;

	private ManejadorEscenarios creador;

	public LoginVistaControlador() {

	}

	@FXML
	private void initialize() {

		// botonIniciar.backgroundProperty().set(ColorPicker);
		listaItems = FXCollections.observableArrayList();
		listaItems.add("Administrador");
		listaItems.add("Empleado");
		listaItems.add("Recolector");
		listaItems.add("Usuario");
		comboBoxCargo.setItems(listaItems);

	}

	@FXML
	private boolean iniciarSesion() {

		System.out.println(campoContrasenia.getText() + "," + campoCorreo.getText());
		// spinerCarga.setVisible(true);

		creador.iniciarVistaTrabajador();

		// spinerCarga.setVisible(false);

		return false;

	}

	@FXML
	private boolean registrarTrabajador() {

		// spinerCarga.setVisible(true);
		creador.iniciarVistaRegistro();

		// spinerCarga.setVisible(false);

		return false;

	}

	public void asignarMain(ManejadorEscenarios creador) {

		this.creador = creador;

	}
}

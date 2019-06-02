package co.alfite.sis.controlador;

import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.Trabajador;
import co.alfite.sis.entidades.Usuario;
import co.alfite.sis.util.Utilidades;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VistaRegistroControlador {

	@FXML
	private TextField campoNombre;
	@FXML
	private TextField campoFechaNacimiento;
	@FXML
	private TextField campoCorreo;
	@FXML
	private TextField campoContrasenia;
	@FXML
	private TextField campoCedula;
	@FXML
	private TextField campoTelefono;
	@FXML
	private ComboBox<String> comboboxCargo;
	@FXML
	private Button botonRealizarRegistro;
	@FXML
	private Button botonRegresar;
	@FXML
	private DatePicker fechaNacimiento;

	@FXML
	private ObservableList<String> listaItems;
	private ManejadorEscenarios manejador;
	private Stage miEscenario;

	@FXML
	private void initialize() {
		listaItems = FXCollections.observableArrayList();
		listaItems.add("Recolector");
		listaItems.add("Usuario");
		comboboxCargo.setItems(listaItems);
	}

	@FXML
	public void registrarPersona() {

		Persona persona = null;

		if (validarCampos()) {
			String cargoPersona = comboboxCargo.getSelectionModel().getSelectedItem();

			if (cargoPersona.equals("Trabajador")) {
				persona = new Trabajador();
			} else if (cargoPersona.equals("Empleado")) {
				persona = new Empleado();
				;
			} else if (cargoPersona.equals("Recolector")) {
				persona = new Recolector();
			} else if (cargoPersona.equals("Usuario")) {
				persona = new Usuario();
			}
			persona.setIdPersona(campoCedula.getText());
			persona.setNombre(campoNombre.getText());
			persona.setPassword(campoContrasenia.getText());
			persona.setEmail(campoContrasenia.getText());
			persona.setFechaNacimiento(Utilidades.pasarADate(fechaNacimiento.getValue()));
		}
		

//		if (manejador.registrarEmpleado(persona)) {
//			Utilidades.mostrarMensaje("Registro", "Registro exitoso!!");
//		} else {
//			Utilidades.mostrarMensaje("Registro", "Error en registro!!");
//		}
	}

	@FXML
	public void regresar() {
		manejador.escenarioInicial();
		miEscenario.close();
	}

	private boolean validarCampos() {

		boolean valido = true;
		if (comboboxCargo.getSelectionModel().getSelectedItem() == null) {
			valido = false;
		}
		return valido;
	}

	public void setManejador(ManejadorEscenarios m) {
		this.manejador = m;
	}

	public void setStage(Stage stage) {
		this.miEscenario = stage;

	}

}

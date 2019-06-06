package co.alfite.sis.controlador;

import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Persona.Estado;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.Trabajador;
import co.alfite.sis.entidades.Usuario;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;
import co.alfite.sis.util.Utilidades;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
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

		try {

			boolean registroValido = false;
			if (validarCampos()) {
				String cargoPersona = comboboxCargo.getSelectionModel().getSelectedItem();

				if (cargoPersona.equals("Empleado")) {
					Empleado persona = new Empleado();

					persona.setIdPersona(campoCedula.getText());
					persona.setNombre(campoNombre.getText());
					persona.setPassword(campoContrasenia.getText());
					persona.setEmail(campoCorreo.getText());
					persona.setTelefono(campoTelefono.getText());
					persona.setFechaNacimiento(Utilidades.pasarADate(fechaNacimiento.getValue()));
					persona.setEstado(Estado.activo);
					registroValido = manejador.insertarEmpleado(persona);

				} else if (cargoPersona.equals("Recolector")) {
					Recolector persona = new Recolector();

					persona.setIdPersona(campoCedula.getText());
					persona.setNombre(campoNombre.getText());
					persona.setPassword(campoContrasenia.getText());
					persona.setEmail(campoCorreo.getText());
					persona.setTelefono(campoTelefono.getText());
					persona.setEstado(Estado.activo);
					persona.setFechaNacimiento(Utilidades.pasarADate(fechaNacimiento.getValue()));

					registroValido = manejador.insertarRecolector(persona);

				} else if (cargoPersona.equals("Usuario")) {
					Usuario nuevoUsuario = new Usuario();
					nuevoUsuario.setIdPersona(campoCedula.getText());
					nuevoUsuario.setNombre(campoNombre.getText());
					nuevoUsuario.setEstado(Estado.activo);
					nuevoUsuario.setPassword(campoContrasenia.getText());
					nuevoUsuario.setEmail(campoCorreo.getText());
					nuevoUsuario.setTelefono(campoTelefono.getText());
					nuevoUsuario.setFechaNacimiento(Utilidades.pasarADate(fechaNacimiento.getValue()));

					registroValido = manejador.insertarUsuario(nuevoUsuario);
				}

			} else {
				Utilidades.mostrarMensaje("Error", "Debe llenar todos los campos", AlertType.ERROR);
			}
			if (registroValido) {
				Utilidades.mostrarMensaje("Bienvenida", "Bienvenido al Herbario de la universidad del quindio",
						AlertType.INFORMATION);

			}
		} catch (ElementoRepetidoExcepcion e) {
			Utilidades.mostrarMensaje("Error", e.getMessage(), AlertType.ERROR);
		}

	}

	@FXML
	public void regresar() {
		manejador.escenarioInicial();
		miEscenario.close();
	}

	private boolean validarCampos() {

		boolean valido = true;
		if (comboboxCargo.getSelectionModel().getSelectedItem() == null || campoCedula.getText().isEmpty()
				|| campoNombre.getText().isEmpty() || campoContrasenia.getText().isEmpty()
				|| campoCorreo.getText().isEmpty() || campoTelefono.getText().isEmpty()
				|| Utilidades.pasarADate(fechaNacimiento.getValue()) == null) {
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

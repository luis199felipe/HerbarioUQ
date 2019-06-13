package co.alfite.sis.controlador;

import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Persona.Estado;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.Trabajador;
import co.alfite.sis.entidades.Usuario;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;
import co.alfite.sis.modelo.AdministradorDelegado;
import co.alfite.sis.util.Utilidades;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VistaRegistroControlador {

	@FXML
	private TextField campoNombre;
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
	private Label labelTitulo;

	@FXML
	private ObservableList<String> listaItems;
	private ManejadorEscenarios manejador;
	private Stage miEscenario;
	private String tipoVista;
	private String origen;
	private AdministradorDelegado adminDelegado;

	public VistaRegistroControlador() {
		adminDelegado = adminDelegado.administradorDelegado;
	}

	@FXML
	private void initialize() {
		listaItems = FXCollections.observableArrayList();
		listaItems.add("Recolector");
		listaItems.add("Usuario");
		listaItems.add("Empleado");
		listaItems.add("Administrador");
		comboboxCargo.setItems(listaItems);
	}

	@FXML
	public void registrarPersona() {

		if (tipoVista.equals("registrar")) {
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
						registroValido = (adminDelegado.insertarEmpleado(persona) == null);

					} else if (cargoPersona.equals("Recolector")) {
						Recolector persona = new Recolector();

						persona.setIdPersona(campoCedula.getText());
						persona.setNombre(campoNombre.getText());
						persona.setPassword(campoContrasenia.getText());
						persona.setEmail(campoCorreo.getText());
						persona.setTelefono(campoTelefono.getText());
						persona.setEstado(Estado.activo);
						persona.setFechaNacimiento(Utilidades.pasarADate(fechaNacimiento.getValue()));

						registroValido = (adminDelegado.insertarRecolector(persona)==null);

					} else if (cargoPersona.equals("Usuario")) {
						Usuario nuevoUsuario = new Usuario();
						nuevoUsuario.setIdPersona(campoCedula.getText());
						nuevoUsuario.setNombre(campoNombre.getText());
						nuevoUsuario.setEstado(Estado.activo);
						nuevoUsuario.setPassword(campoContrasenia.getText());
						nuevoUsuario.setEmail(campoCorreo.getText());
						nuevoUsuario.setTelefono(campoTelefono.getText());
						nuevoUsuario.setFechaNacimiento(Utilidades.pasarADate(fechaNacimiento.getValue()));

						registroValido = adminDelegado.insertarUsusario(nuevoUsuario);
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

		} else {
			actualizarDatosPersona();
		}
	}

	private void actualizarDatosPersona() {

	}

	@FXML
	public void regresar() {
		if (!tipoVista.equals("actualizar") && !origen.equals("vistaAdministradorRecolector")
				&& !origen.equals("vistaAdministradorEmpleado")) {
			manejador.escenarioInicial();
		}
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

	public void adaptarVista(String tipo, String origen, Persona p) {
		this.tipoVista = tipo;
		this.origen = origen;
		if (tipo.equals("actualizar")) {
			labelTitulo.setText("Actualizacion de datos");

			comboboxCargo.setPromptText(p.getClass().getSimpleName());

			campoNombre.setText(p.getNombre());
			campoCedula.setText(p.getIdPersona());
			campoCedula.setEditable(false);
			campoCorreo.setText(p.getEmail());
			fechaNacimiento.setPromptText(p.getFechaNacimiento().toString());
			campoTelefono.setText(p.getTelefono());
			campoContrasenia.setText(p.getPassword());
			botonRealizarRegistro.setText("Actualizar Datos");
			listaItems.clear();

		}

		if (origen.equals("vistaLogin")) {

			listaItems.remove(3);
			listaItems.remove(2);
		}
		if (origen.equals("vistaAdministradorRecolector")) {
			listaItems.clear();
			listaItems.add("Recolector");
		} else if (origen.equals("vistaAdministradorEmpleado")) {
			listaItems.clear();
			listaItems.add("Empleado");
		}

	}

}

package co.alfite.sis.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.xml.ws.api.FeatureConstructor;

import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Persona.Estado;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.modelo.AdministradorDelegado;
import co.alfite.sis.modelo.observable.PersonaObservable;
import co.alfite.sis.util.Utilidades;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class VistaGestionarPersonaControlador {

	@FXML
	private TextField campoNombre;
	@FXML
	private DatePicker fechaNacimiento;
	@FXML
	private TextField campoCorreo;
	@FXML
	private TextField campoCedula;
	@FXML
	private TextField campoTelefono;
	@FXML
	private Button botonActualizarDatosPersona;

	@FXML
	private TableColumn<PersonaObservable, String> columnaNombre;
	@FXML
	private TableColumn<PersonaObservable, String> columnaId;
	@FXML
	private TableView<PersonaObservable> tablaPersonas;

	@FXML
	private TextField campoBuscar;
	@FXML
	private TextField campoContrasenia;

	@FXML
	private TextField campoEstado;

	@FXML
	private Button botonBuscar;
	@FXML
	private Button botonCambiarEstadoPersona;

	@FXML
	private ComboBox<String> botonFiltraPor;

	@FXML
	private Button botonAgregarTrabajador;

	@FXML
	private Button botonActualizarLista;

	private ManejadorEscenarios manejador;
	private String persona;
	private AdministradorDelegado adminDelegado;
	

	public VistaGestionarPersonaControlador() {
		adminDelegado = adminDelegado.administradorDelegado;
	}

	@FXML
	private void initialize() {

		columnaNombre.setCellValueFactory(cellData -> cellData.getValue().getNombre());
		columnaId.setCellValueFactory(cellData -> cellData.getValue().getIdPersona());

		tablaPersonas.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> verPersonaDetalle(newValue));

	}

	private void verPersonaDetalle(PersonaObservable persona) {
		if (persona != null) {
			
			campoNombre.setText(persona.getNombre().getValue());
			campoCedula.setText(persona.getIdPersona().getValue());
			campoCorreo.setText(persona.getEmail().getValue());
			System.out.println(persona.toString());
			System.out.println(persona.getFechaNacimiento().getValue().toString());
			//fechaNacimiento.getEditor().setText("12/12/2000");
			//fechaNacimiento.valueProperty().set((Utilidades.pasarALocalDate(p.getFechaNacimiento())));
			campoTelefono.setText(persona.getTelefono().getValue());
			campoContrasenia.setText(persona.getPassword().getValue());
			campoEstado.setText(persona.getEstado().getValue());
			if (campoEstado.getText().equals("inactivo")) {
				botonCambiarEstadoPersona.setText("Activar Persona");
			} else {
				botonCambiarEstadoPersona.setText("Inactivar Persona");

			}

		}
	}
	
	

	public void setManejador(ManejadorEscenarios m) {
		this.manejador = m;
		llenarTabla();

	}

	private void llenarTabla() {
		if (this.persona.equals("recolector")) {
			tablaPersonas.setItems((ObservableList<PersonaObservable>) adminDelegado.listarRecolectoresObservables());
		} else {
			tablaPersonas.setItems((ObservableList<PersonaObservable>) adminDelegado.listarEmpleadosObservables());

		}
	}

	/**
	 * Adapta las etiquetas de los componentes a la persona que llega por parametro
	 * 
	 * @param personaGestionar tipo de persona (empleado, recolector)
	 */
	public void adaptarVista(String personaGestionar) {
		this.persona = personaGestionar;

		if (personaGestionar.equals("recolector")) {
			botonAgregarTrabajador.setText("Agregar nuevo Recolector");
			campoBuscar.setPromptText("Busca un Recolector");

		} else {
			botonAgregarTrabajador.setText("Agregar nuevo Empleado");
			campoBuscar.setPromptText("Busca un Empleado");
		}
	}

	@FXML
	void actualizarDatosPersona() {

		if (persona.equals("empleado")) {

			Empleado e = adminDelegado.buscarEmpleado(campoCedula.getText());
			manejador.cargarEscenarioRegistro("actualizar", "vistaGestionarPersona", e);

		} else {

			Recolector r = adminDelegado.buscarRecolector(campoCedula.getText());
			manejador.cargarEscenarioRegistro("actualizar", "vistaGestionarPersona", r);

		}

	}

	@FXML
	void buscarTrabajadorPor() {

	}

	@FXML
	private void cambiarEstadoPersona() {
		if (campoEstado.getText().equals("inactivo")) {
			adminDelegado.actualizarEstadoPersona(campoCedula.getText(), Estado.activo);
			Utilidades.mostrarMensaje("OK","Se a inactivado la persona", AlertType.INFORMATION);
			actualizarLista();
		} else {
			adminDelegado.actualizarEstadoPersona(campoCedula.getText(), Estado.inactivo);
			Utilidades.mostrarMensaje("OK","Se a activado la persona", AlertType.INFORMATION);
			actualizarLista();
		}
	}

	@FXML
	void actualizarLista() {
		int ant = tablaPersonas.getSelectionModel().getSelectedIndex();
		llenarTabla();
		tablaPersonas.getSelectionModel().clearSelection();
		tablaPersonas.getSelectionModel().select(ant);
	}

	@FXML
	void agregarNuevoTrabajador() {
		if (persona.equals("recolector")) {
			manejador.cargarEscenarioRegistro("registrar", "vistaAdministradorRecolector", null);
		} else {
			manejador.cargarEscenarioRegistro("registrar", "vistaAdministradorEmpleado", null);

		}

	}

}

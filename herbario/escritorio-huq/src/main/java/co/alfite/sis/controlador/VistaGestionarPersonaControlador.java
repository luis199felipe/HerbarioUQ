package co.alfite.sis.controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import co.alfite.sis.entidades.Persona;
import co.alfite.sis.modelo.AdministradorDelegado;
import co.alfite.sis.modelo.observable.PersonaObservable;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
	private Button botonCambiarEstadoPersona;
	@FXML
	private TextField campoCargo;
	@FXML
	private TableColumn<PersonaObservable, String> columnaNombre;
	@FXML
	private TableColumn<PersonaObservable, String> columnaId;
	@FXML
	private TableView<PersonaObservable> tablaPersonas;

	@FXML
	private TextField campoBuscar;

	@FXML
	private TextField campoEstado;

	@FXML
	private Button botonBuscar;

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

			try {
				Date fecha = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse("1999-10-14 00:00:00.0");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			campoNombre.setText(persona.getNombre().getValue());
			campoCedula.setText(persona.getIdPersona().getValue());
			campoCorreo.setText(persona.getEmail().getValue());
			campoFechaNacimiento.setText(persona.getFechaNacimiento().getValue().toString());
			campoTelefono.setText(persona.getTelefono().getValue());
			campoCargo.setText(this.persona);
			campoEstado.setText(String.valueOf(persona.getEstado().getValue()));

		}
	}

	public void setManejador(ManejadorEscenarios m) {
		this.manejador = m;

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
	void cambiarEstadoPersona() {

	}

	@FXML
	void buscarTrabajadorPor() {

	}

	@FXML
	void actualizarLista() {

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

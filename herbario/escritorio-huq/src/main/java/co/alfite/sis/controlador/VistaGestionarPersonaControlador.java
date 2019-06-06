package co.alfite.sis.controlador;

import co.alfite.sis.entidades.Persona;
import co.alfite.sis.modelo.observable.PersonaObservable;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	private Button botonInactivar;
	@FXML
	private TextField campoCargo;
	@FXML
	private TableColumn<PersonaObservable, String>columnaNombre;
	@FXML
	private TableColumn<PersonaObservable, String>columnaId;
	@FXML
	private TableView<PersonaObservable>tablaPersonas;
	
	private ManejadorEscenarios manejador;
	private Stage miEscenario;

	@FXML
	private void initialize() {
		
		
		columnaNombre.setCellValueFactory(cellData -> cellData.getValue().getNombre());
		columnaId.setCellValueFactory(cellData -> cellData.getValue().getId());

		

		tablaPersonas.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> verPersonaDetalle(newValue));
	}

	
	private void verPersonaDetalle(PersonaObservable persona) {
		if (persona != null) {
//
//			labelNombre.setText(persona.getNombreToString());
//			labelApellido.setText(persona.getApellidoToString());

		} else {

//			labelNombre.setText("");
//			labelApellido.setText("");
////			streetLabel.setText("");
////			postalCodeLabel.setText("");
////			cityLabel.setText("");
////			birthdayLabel.setText("");
		}
	}
	@FXML
	public void invalidarPersona() {

	}

	
	
	public void setManejador(ManejadorEscenarios m) {
		this.manejador = m;
		tablaPersonas.setItems((ObservableList<PersonaObservable>) manejador.ListaRecolectoresObservables());
	}

	public void setStage(Stage stage) {
		this.miEscenario = stage;

	}

}

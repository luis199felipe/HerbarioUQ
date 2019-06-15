package co.alfite.sis.controlador;

import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.modelo.AdministradorDelegado;
import co.alfite.sis.modelo.observable.FamiliaObservable;
import co.alfite.sis.modelo.observable.GeneroObservable;
import co.alfite.sis.util.Utilidades;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class VistaGestionarGeneroControlador {

	@FXML
	private TextField campoNumeroEspecies;

	@FXML
	private TextField campoNombreFamilia;

	@FXML
	private TableColumn<GeneroObservable, String> columnaId;

	@FXML
	private Button botonActualizarDatos;

	@FXML
	private Button botonEliminar;

	@FXML
	private Button botonAgregarGenero;

	@FXML
	private TextField campoID;

	@FXML
	private Button botonBuscar;

	@FXML
	private TextField campoBuscar;

	@FXML
	private ComboBox<?> cmoboBoxFiltrar;

	@FXML
	private TableView<GeneroObservable> tabla;

	@FXML
	private Button botonActualizarLista;

	@FXML
	private TableColumn<GeneroObservable, String> columnaNombre;

	@FXML
	private TextField campoNombre;

	private AdministradorDelegado adminDelegado;

	private ManejadorEscenarios miEscenario;

	public VistaGestionarGeneroControlador() {
		adminDelegado = adminDelegado.administradorDelegado;
	}

	@FXML
	private void initialize() {
		tabla.setItems((ObservableList<GeneroObservable>) adminDelegado.listarGenerosObservables());

		columnaNombre.setCellValueFactory(cellData -> cellData.getValue().getNombre());
		columnaId.setCellValueFactory(cellData -> cellData.getValue().getIdGenero());

		tabla.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> verGeneroDetalle(newValue));
	}

	private void verGeneroDetalle(GeneroObservable gen) {
		// TODO Auto-generated method stub
	}

	@FXML
	private void actualizarDatos() {
		if (botonActualizarDatos.getText().equals("Actualizar los Datos")) {
			Utilidades.mostrarMensaje("Editar Datos",
					"se hablitara el campo  nombre familia para que pueda modificar el dato, depues de esto debe volver a presionar el boton actualizar para guardar los cambiios",
					AlertType.INFORMATION);
			botonActualizarDatos.setText("Guardar Cambios");
			campoNombre.setEditable(true);
		} else {
			GeneroPlanta ac = new GeneroPlanta();

			ac.setIdGenero(Integer.parseInt(campoID.getText()));
			ac.setNombre(campoNombre.getText());
			ac.setFamiliaPlanta(adminDelegado.buscarFamiliaPlanta(campoNombreFamilia.getText()));
			adminDelegado.actualizarGeneroPlanta(ac);
			botonActualizarDatos.setText("Actualizar los Datos");
			campoNombre.setEditable(false);

			Utilidades.mostrarMensaje("Editar Datos", "se ha actualizado correctamente", AlertType.INFORMATION);
		}
	}

	@FXML
	private void eliminar() {
		boolean  elim=adminDelegado.eliminarGenero(campoNombre.getText());
		if(elim) {
			Utilidades.mostrarMensaje("Eliminar", "El genero se ha eliminado correctamente", AlertType.INFORMATION);

		}
	}

	@FXML
	private void buscar() {

	}
	@FXML
	void agregarGenero() {
		miEscenario.cargarEscenarioAgregarGF("genero");
	}
	
	@FXML
	void actualizarLista() {
		int ant = tabla.getSelectionModel().getSelectedIndex();
		tabla.setItems((ObservableList<GeneroObservable>) adminDelegado.listarGenerosObservables());
		tabla.getSelectionModel().clearSelection();
		tabla.getSelectionModel().select(ant);
	}

	public void setManejador(ManejadorEscenarios manejadorEscenarios) {
		this.miEscenario=manejadorEscenarios;
		
	}

}

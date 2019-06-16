package co.alfite.sis.controlador;

import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.modelo.AdministradorDelegado;
import co.alfite.sis.modelo.observable.FamiliaObservable;
import co.alfite.sis.modelo.observable.PersonaObservable;
import co.alfite.sis.util.Utilidades;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class VistaGestionarFamiliaControlador {

	@FXML
	private Label campoNumeroEspecies;

	@FXML
	private Button botonActualizarDatos;

	@FXML
	private Button botonEliminar;

	@FXML
	private TextField campoBuscar;

	@FXML
	private Button botonBuscar;

	@FXML
	private Label campoID;

	@FXML
	private ComboBox<?> comboBoxFiltrar;

	@FXML
	private TableColumn<FamiliaObservable, String> columnaId;

	@FXML
	private Button botonAgregar;

	@FXML
	private Label campoNumeroGeneros;

	@FXML
	private TableView<FamiliaObservable> tabla;

	@FXML
	private Button botonActualizarLista;

	@FXML
	private TableColumn<FamiliaObservable, String> columnaNombre;

	@FXML
	private TextField campoNombre;

	private ManejadorEscenarios miEscenario;
	private String elementoAGestionar;

	private AdministradorDelegado adminDelegado;

	public VistaGestionarFamiliaControlador() {
		adminDelegado = adminDelegado.administradorDelegado;

	}

	@FXML
	private void initialize() {

		tabla.setItems((ObservableList<FamiliaObservable>) adminDelegado.listarFamiliasObservables());

		columnaNombre.setCellValueFactory(cellData -> cellData.getValue().getNombre());
		columnaId.setCellValueFactory(cellData -> cellData.getValue().getIdFamilia());

		tabla.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> verFamiliaDetalle(newValue));

	}

	public void setManejador(ManejadorEscenarios manejadorEscenarios) {
		this.miEscenario = manejadorEscenarios;

	}

	private void verFamiliaDetalle(FamiliaObservable fam) {
		campoID.setText(fam.getIdFamilia().getValue());
		campoNombre.setText(fam.getNombre().getValue());
		campoNumeroGeneros.setText(fam.getNumeroGeneros());
	}

	/**
	 * Adapta las etiquetas de la vista a genero, familia o especie
	 * 
	 * @param elementoAGestionar puede ser un genero ,familia,especie
	 */

	@FXML
	void agregarFamilia() {
		miEscenario.cargarEscenarioAgregarGF("familia");
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
			FamiliaPlanta ac = new FamiliaPlanta();

			ac.setIdFamilia(Integer.parseInt(campoID.getText()));
			ac.setNombre(campoNombre.getText());
			adminDelegado.actualizarFamiliaPlanta(ac);
			botonActualizarDatos.setText("Actualizar los Datos");
			campoNombre.setEditable(false);

			Utilidades.mostrarMensaje("Editar Datos", "se ha actualizado correctamente", AlertType.INFORMATION);
		}
	}

	@FXML
	private void eliminar() {

		boolean  elim=adminDelegado.eliminarFamilia(campoNombre.getText());
		if(elim) {
			Utilidades.mostrarMensaje("Eliminar", "la familia se ha eliminado correctamente", AlertType.INFORMATION);
			actualizarLista();
		}else {
			Utilidades.mostrarMensaje("ERROR", "la familia NO se ha podido eliminar", AlertType.ERROR);
		}

	}

	@FXML
	void buscar() {

	}

	@FXML
	void actualizarLista() {

		int ant = tabla.getSelectionModel().getSelectedIndex();
		tabla.setItems((ObservableList<FamiliaObservable>) adminDelegado.listarFamiliasObservables());
		tabla.getSelectionModel().clearSelection();
		tabla.getSelectionModel().select(ant);

	}

}

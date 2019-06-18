package co.alfite.sis.controlador;

import co.alfite.sis.entidades.RegistroEspecie.Estado;
import co.alfite.sis.modelo.AdministradorDelegado;
import co.alfite.sis.modelo.observable.EspecieObservable;
import co.alfite.sis.modelo.observable.FamiliaObservable;
import co.alfite.sis.modelo.observable.GeneroObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class VistaGestionarEspecieControlador {

	@FXML
	private Button botonIrAregistro;

	@FXML
	private TextField campoNombreFamilia;

	@FXML
	private Button botonActualizarDatos;

	@FXML
	private Button botonEliminar;

	@FXML
	private TextField campoBuscar;

	@FXML
	private Button botonBuscar;

	@FXML
	private TextField campoNombreGenero;

	@FXML
	private TextField campoID;

	@FXML
	private ComboBox<String> comboBoxFiltrar;

	@FXML
	private TableColumn<EspecieObservable, String> columnaId;

	@FXML
	private TextField campoNombreCientifico;

	@FXML
	private TextField campoNombreEspecie;

	@FXML
	private TableView<EspecieObservable> tabla;

	@FXML
	private Button botonActualizarLista;

	@FXML
	private TableColumn<EspecieObservable, String> columnaNombre;

	@FXML
	private ObservableList<String> listaItems;
	private AdministradorDelegado adminDelegado;

	private ManejadorEscenarios miEscenario;

	private BorderPane pane;

	public VistaGestionarEspecieControlador() {
		adminDelegado = adminDelegado.administradorDelegado;
	}

	@FXML
	private void initialize() {
		tabla.setItems((ObservableList<EspecieObservable>) adminDelegado.listarEspeciesObservables());

		columnaNombre.setCellValueFactory(cellData -> cellData.getValue().getNombre());
		columnaId.setCellValueFactory(cellData -> cellData.getValue().getIdEspecie());

		tabla.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> verEspecieDetalle(newValue));

		listaItems = FXCollections.observableArrayList();
		listaItems.add("Filtrar por familia");
		listaItems.add("Filtrar por genero");
		listaItems.add("Filtrar por esp aceptadas");
		listaItems.add("Filtrar por esp rechazadas");
		comboBoxFiltrar.setItems(listaItems);

	}

	private void verEspecieDetalle(EspecieObservable esp) {

		campoNombreCientifico.setText(esp.getNombreCientifico().getValue());
		campoID.setText(esp.getIdEspecie().getValue());
		campoNombreEspecie.setText(esp.getNombre().getValue());
		campoNombreFamilia.setText(esp.getNombreFamilia().getValue());
		campoNombreGenero.setText(esp.getNombreGenero().getValue());
	}

	@FXML
	void actualizarDatos() {
		
	}

	@FXML
	void eliminar() {
			adminDelegado.eliminarEspecie(adminDelegado.buscarEspeciePlanta(campoNombreEspecie.getText()));
	}

	@FXML
	void buscar() {
		String filtro = campoBuscar.getText();
		String opcion = comboBoxFiltrar.getSelectionModel().getSelectedItem();
		if (opcion.equals("Filtrar por familia")) {
			tabla.setItems((ObservableList<EspecieObservable>) adminDelegado.listarEspeciesPorFamiliaObservables(filtro));
		}else if (opcion.equals("Filtrar por genero")) {
			tabla.setItems((ObservableList<EspecieObservable>) adminDelegado.listarEspeciesPorGeneroObservables(filtro));
		}
	}

	@FXML
	void actualizarLista() {
		tabla.setItems((ObservableList<EspecieObservable>) adminDelegado.listarEspeciesObservables());
	}

	@FXML
	void irAregistro() {
		miEscenario.iniciarVistaValidarRegistros(pane);
	}

	public void setManejador(ManejadorEscenarios manejadorEscenarios) {
		this.miEscenario = manejadorEscenarios;

	}

	public void setPane(BorderPane pane) {
		this.pane = pane;

	}
}

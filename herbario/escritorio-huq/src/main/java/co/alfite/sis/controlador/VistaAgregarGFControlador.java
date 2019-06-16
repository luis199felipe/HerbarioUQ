package co.alfite.sis.controlador;

import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;
import co.alfite.sis.modelo.AdministradorDelegado;
import co.alfite.sis.modelo.observable.FamiliaObservable;
import co.alfite.sis.modelo.observable.GeneroObservable;
import co.alfite.sis.util.Utilidades;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class VistaAgregarGFControlador {

	@FXML
	private TextField campoNombreFamilia;

	@FXML
	private Label labelNombreGenero;

	@FXML
	private Label labelTitulo;

	@FXML
	private Button botonAgregar;

	@FXML
	private ComboBox<String> comboBoxNombreFamilia;

	@FXML
	private Button botonDescartar;

	@FXML
	private TextField campoNombreGenero;

	private AdministradorDelegado adminDelegado;

	private String tipoObjeto;

	private Stage stage;
	private ObservableList<String> listaItems;

	public VistaAgregarGFControlador() {
		adminDelegado = adminDelegado.administradorDelegado;
	}

	@FXML
	private void initialize() {

		cargarListaFamilias();
	}

	private void cargarListaFamilias() {

		listaItems = FXCollections.observableArrayList();

		ObservableList<FamiliaObservable> x = (ObservableList<FamiliaObservable>) adminDelegado
				.listarFamiliasObservables();

		for (int i = 0; i < x.size(); i++) {

			listaItems.add(x.get(i).getNombre().getValue());

		}
		comboBoxNombreFamilia.setItems(listaItems);
	}

	@FXML
	void agregar() {

		if (tipoObjeto.equals("familia")) {

			try {

				FamiliaPlanta nuevaFam = new FamiliaPlanta();
				nuevaFam.setNombre(campoNombreFamilia.getText());
				adminDelegado.insertarFamilia(nuevaFam);
				Utilidades.mostrarMensaje("OK", "Se agregó correctamente el genero, por favor actualice la lista.", AlertType.INFORMATION);
				stage.close();
			} catch (ElementoRepetidoExcepcion e) {
				e.printStackTrace();
				Utilidades.mostrarMensaje("Error", "No se pudo agregar el genero", AlertType.ERROR);
			}
		} else {

			try {

				GeneroPlanta nuevoGen = new GeneroPlanta();
				nuevoGen.setFamiliaPlanta(adminDelegado.buscarFamiliaPlanta(
						comboBoxNombreFamilia.getSelectionModel().getSelectedItem()));
				nuevoGen.setNombre(campoNombreGenero.getText());
				adminDelegado.insertarGenero(nuevoGen);
				
				Utilidades.mostrarMensaje("OK", "Se agregó correctamente el genero, por favor actualice la lista.", AlertType.INFORMATION);
				
				stage.close();
				
			} catch (ElementoRepetidoExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Utilidades.mostrarMensaje("Error", "No se pudo agregar el genero", AlertType.ERROR);
			}
		}

	}

	@FXML
	void descartar() {
		stage.close();
	}

	public void adaptarVista(String tipoObjeto) {

		this.tipoObjeto = tipoObjeto;
		if (tipoObjeto.equals("familia")) {

			labelTitulo.setText("Agregar Familia");
			labelNombreGenero.setVisible(false);
			campoNombreGenero.setVisible(false);
			comboBoxNombreFamilia.setVisible(false);
		} else {
			labelTitulo.setText("Agregar Genero");

			campoNombreFamilia.setVisible(false);
		}
	}

	public void setStage(Stage stage) {
		this.stage = stage;

	}

}

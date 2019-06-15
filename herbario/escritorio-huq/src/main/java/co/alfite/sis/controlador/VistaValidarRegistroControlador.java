package co.alfite.sis.controlador;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import co.alfite.sis.entidades.ImagenPlanta;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.RegistroEspecie.Estado;
import co.alfite.sis.modelo.AdministradorDelegado;
import co.alfite.sis.modelo.observable.FamiliaObservable;
import co.alfite.sis.modelo.observable.RegistroObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class VistaValidarRegistroControlador {

	private ManejadorEscenarios miEscenario;
	@FXML
	private Label nombrePlanta;

	@FXML
	private Label generoPlanta;
	@FXML
	private Label familiaPlanta;
	@FXML
	private ComboBox<String> comoBoxEstado;
	@FXML
	private ComboBox<String> filtrarPor;
	@FXML
	private ImageView imagenPlanta;
	@FXML
	private Button botonActualizarEstado;
	@FXML
	private Button botonNuevoRegistro;

	@FXML
	private TableView<RegistroObservable> tablaRegistros;

	@FXML
	private TableColumn<RegistroObservable, String> columnaIdRegistro;

	@FXML
	private TableColumn<RegistroObservable, String> columnaEstadoRegistro;
	@FXML

	private AdministradorDelegado adminDelegado;
	private ObservableList<String> listaItems;
	private RegistroObservable registro;

	public VistaValidarRegistroControlador() {
		adminDelegado = adminDelegado.administradorDelegado;
	}

	@FXML
	private void initialize() {
		tablaRegistros.setItems((ObservableList<RegistroObservable>) adminDelegado.listarRegistrosObservables());

		columnaIdRegistro.setCellValueFactory(cellData -> cellData.getValue().getIdRegistro());
		columnaEstadoRegistro.setCellValueFactory(cellData -> cellData.getValue().getEstado());

		tablaRegistros.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> verRegistroDetalle(newValue));

		listaItems = FXCollections.observableArrayList();
		listaItems.add("enviado");
		listaItems.add("aprobado");
		listaItems.add("rechazado");
		comoBoxEstado.setItems(listaItems);

	}

	private void verRegistroDetalle(RegistroObservable registro) {

		this.registro = registro;
		ImagenPlanta x = registro.getImagen();
		InputStream in = new ByteArrayInputStream(x.getImagen());
		Image z = new Image(in);
		imagenPlanta.setImage(z);

		nombrePlanta.setText(registro.getNombreEspecie().getValue());
		familiaPlanta.setText(registro.getNombreFamilia().getValue());
		generoPlanta.setText(registro.getNombreGenero().getValue());

		if (registro.getEstado().getValue().equals("enviado")) {

			comoBoxEstado.getSelectionModel().select(0);

		} else if (registro.getEstado().getValue().equals("aprobado")) {
			comoBoxEstado.getSelectionModel().select(1);

		} else {
			comoBoxEstado.getSelectionModel().select(2);

		}

	}

	public void setManejador(ManejadorEscenarios manejadorEscenarios) {
		this.miEscenario = manejadorEscenarios;

	}

	@FXML
	public void insertarRegistro() {
		miEscenario.cargarEscenarioRegistroEspecie();
	}

	@FXML
	void actualizarEstado() {
//la idea es que cuando cambie el estado a aprobado se persista una especie nueva
		// y ademas debe haber una opcion de si la espceie ya existe tomar la imagen del
		// registro y agregarla a la lista de imagenes de la especie
		adminDelegado.validarRegistro(Integer.parseInt(registro.getIdRegistro().getValue()),
				Estado.valueOf(comoBoxEstado.getSelectionModel().getSelectedItem()));

	}

}

package co.alfite.sis.controlador;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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
	private Label GeneroPlantaPlanta;
	@FXML
	private Label familiaPlanta;
	@FXML
	private ComboBox<String> estado;
	@FXML
	private ComboBox<String> filtrarPor;
	@FXML
	private ImageView imagenPlanta;
	@FXML
	private Button botonactualizarEstado;
	@FXML
	private Button botonNuevoRegistro;

	@FXML
	private TableView<String> tabalaRegistros;
//	
//	@FXML
//	private TableColumn<S, T> columnaIdRegistro;
//	
//	@FXML
//	private TableColumn<S, T> columnaEstadoRegistro;
//	@FXML
//	private TableColumn<S, T> columnaNombreTrabajador;

	@FXML
	private void initialize() {

	}

	public void setManejador(ManejadorEscenarios manejadorEscenarios) {
		this.miEscenario = manejadorEscenarios;

	}

	

	

}

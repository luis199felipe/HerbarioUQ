package co.alfite.sis.controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;

public class TrabajadorVistaControlador {

	@FXML
	SplitMenuButton control;
	@FXML
	Button gestionarEmpleado;
	@FXML
	Button gestionarRecolectore;
	@FXML
	Button gestionarFamilia;
	@FXML
	Button gestionarGenero;
	@FXML
	Button gestionarEspecie;
	@FXML
	Button gestionaRegistro;
	@FXML
	Button buscar;

	@FXML
	private void initialize() {

		control.getItems().clear();
		control.getItems().add(new MenuItem("Actualizar Informacion"));
		control.getItems().add(new MenuItem("Cerrar sesion"));
	}
}

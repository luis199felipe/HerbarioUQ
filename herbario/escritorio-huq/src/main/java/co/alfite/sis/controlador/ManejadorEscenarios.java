package co.alfite.sis.controlador;

import java.io.IOException;

import co.alfite.sis.Main;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Trabajador;
import co.alfite.sis.modelo.AdministradorDelegado;
import co.alfite.sis.modelo.EmpleadoObservable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Permite manejar los escenarios que tiene la aplicacion
 * 
 * @author EinerZG
 * @version 1.0
 */
public class ManejadorEscenarios {

	/**
	 * contenedor prinpipal de la aplicacion
	 */
	private Stage escenario;
	/**
	 * tipo de panel inicial
	 */
	private BorderPane bordePanel;
	/**
	 * para almacenar empleados observables
	 */
	private ObservableList<EmpleadoObservable> empleadosObservables;
	/**
	 * conexion con capa de negocio
	 */
	private AdministradorDelegado administradorDelegado;

	/**
	 * recibe el escenario principla de la aplicacion
	 * 
	 * @param escenario inicial
	 */
	public ManejadorEscenarios(Stage escenario) {

		this.escenario = escenario;
//
//		administradorDelegado = AdministradorDelegado.administradorDelegado;
//		empleadosObservables = FXCollections.observableArrayList();

		try {
			// se inicializa el escenario
			escenario.setTitle("Herbario");

			// se carga la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/inicio.fxml"));

			bordePanel = (BorderPane) loader.load();

			// se carga la escena
			Scene scene = new Scene(bordePanel);
			escenario.setScene(scene);
			escenario.show();

			iniciarVistaLogin();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * carga una escena en el centro del escenario
	 */

	public void iniciarVistaLogin() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/Vistalogin.fxml"));
			BorderPane loginVista = (BorderPane) loader.load();

			bordePanel.setCenter(loginVista);

			LoginVistaControlador controladorVistaPersona = loader.getController();
			controladorVistaPersona.asignarMain(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * muestra el escenario para crear un empleado nuevo
	 */
	public void cargarEscenarioCrearEmpleado() {

		try {

			// se carga la interfaz
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/editar_empleado.fxml"));
			AnchorPane page = (AnchorPane) loader.load();

			// se crea el escenario
			Stage escenarioCrear = new Stage();
			escenarioCrear.setTitle("Crear");
			Scene scene = new Scene(page);
			escenarioCrear.setScene(scene);

			// se carga el controlador
			EdicionEmpleadoControlador empleadoControlador = loader.getController();
			empleadoControlador.setEscenarioEditar(escenarioCrear);
			empleadoControlador.setManejador(this);

			// se crea el escenario
			escenarioCrear.showAndWait();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void iniciarVistaTrabajador() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/VistaTrabajador.fxml"));
			BorderPane vista = (BorderPane) loader.load();

			BorderPane root = new BorderPane();
			root.setCenter(vista);
			Scene scene = new Scene(root);
			Stage stage = new Stage();

			stage.setScene(scene);
			stage.show();

			escenario.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void iniciarVistaRegistro() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/VistaRegistroTrabajador.fxml"));
			BorderPane vista = (BorderPane) loader.load();

			Scene scene = new Scene(vista);
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<EmpleadoObservable> getEmpleadosObservables() {
		return empleadosObservables;
	}

	public void agregarALista(Persona empleado) {
		empleadosObservables.add(new EmpleadoObservable(empleado));
	}

	public Stage getEscenario() {
		return escenario;
	}

	public void iniciarDialogo() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/VistaDialogo.fxml"));
			BorderPane vista = (BorderPane) loader.load();

			Scene scene = new Scene(vista);

			Stage stage = new Stage();

			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	public boolean registrarTrabajador(Empleado empleado) {
		try {
			return administradorDelegado.registrarTrabajador(empleado);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	public boolean eliminarEmpleado(Empleado empleado) {
		return administradorDelegado.eliminarEmpleado(empleado);
	}

}

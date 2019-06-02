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
		administradorDelegado = AdministradorDelegado.administradorDelegado;
//		empleadosObservables = FXCollections.observableArrayList();

		try {
			// se inicializa el escenario
			escenario.setTitle("Herbario");

			// se carga la vista
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/VistaRaiz.fxml"));

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

	public void escenarioInicial() {
		escenario.show();
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

			VistaLoginControlador controladorVistaPersona = loader.getController();
			controladorVistaPersona.setManejador(this);
			controladorVistaPersona.setStage(escenario);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cargarEscenarioTrabajador() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/VistaSesionRaiz.fxml"));
			BorderPane vista = (BorderPane) loader.load();

			BorderPane root = new BorderPane();
			root.setCenter(vista);
			Scene scene = new Scene(root);
			Stage stage = new Stage();

			VistaSesionRaizControlador controladorVistaTrabajador = loader.getController();
			controladorVistaTrabajador.setManejador(this);
			controladorVistaTrabajador.setStage(vista);
			controladorVistaTrabajador.setStage(stage);

			stage.setScene(scene);
			stage.show();
			controladorVistaTrabajador.cargarMenu();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void iniciarVistaMenuTrabajador(BorderPane pane) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/VistaTrabajadorMenu.fxml"));
			BorderPane subVista = (BorderPane) loader.load();

			pane.setCenter(subVista);

			VistaTrabajadorMenuControlador controladorMenu = loader.getController();
			controladorMenu.setManejador(this);
			controladorMenu.setPanePrincipal(pane);
		

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void cargarEscenarioRegistro() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/VistaRegistro.fxml"));
			BorderPane vista = (BorderPane) loader.load();

			VistaRegistroControlador controladorVistaRegistro = loader.getController();
			// importante
			controladorVistaRegistro.setManejador(this);

			Scene scene = new Scene(vista);
			Stage stage = new Stage();

			stage.setScene(scene);
			stage.show();
			// importante
			controladorVistaRegistro.setStage(stage);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void iniciarVistaGestionar(BorderPane pane) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/VistaGestionarPersona.fxml"));
			BorderPane subVista = (BorderPane) loader.load();

			pane.setCenter(subVista);

			VistaGestionarPersonaControlador controladorGestionar = loader.getController();
			controladorGestionar.setManejador(this);
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void iniciarVistaValidarRegistros(BorderPane pane) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/VistaValidarRegistro.fxml"));
			BorderPane subVista = (BorderPane) loader.load();

			pane.setCenter(subVista);

			VistaValidarRegistroControlador controladorValidarRegistros = loader.getController();
			controladorValidarRegistros.setManejador(this);
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void iniciarVistaGestionarGFE(BorderPane pane,String elementoAGestionar) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/VistaGestionarGFE.fxml"));
			BorderPane subVista = (BorderPane) loader.load();

			pane.setCenter(subVista);

			VistaGestionarGFEControlador controladorGestionGFE = loader.getController();
			controladorGestionGFE.setManejador(this);
			controladorGestionGFE.adaptarVista(elementoAGestionar);
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cargarEscenarioDialogo() {
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

	public ObservableList<EmpleadoObservable> getEmpleadosObservables() {
		return empleadosObservables;
	}

	public void agregarALista(Persona empleado) {
		empleadosObservables.add(new EmpleadoObservable(empleado));
	}

	public Stage getEscenario() {
		return escenario;
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

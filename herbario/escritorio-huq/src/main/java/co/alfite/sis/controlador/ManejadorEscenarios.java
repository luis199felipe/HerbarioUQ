package co.alfite.sis.controlador;

import java.io.IOException;

import java.util.List;

import co.alfite.sis.Main;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.ImagenPlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.Usuario;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;
import co.alfite.sis.modelo.AdministradorDelegado;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;

import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Permite manejar los escenarios que tiene la aplicacion
 * 
 * @author EinerZG
 * @version 1.0
 */
public class ManejadorEscenarios {

	private final static String ruta = "./util/iconH.png";
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

	private Persona personaEnSesion;

	public Persona getPersonaEnSesion() {
		return personaEnSesion;
	}

	public void setPersonaEnSesion(Persona personaEnSesion) {
		this.personaEnSesion = personaEnSesion;
	}

	/**
	 * recibe el escenario principla de la aplicacion
	 * 
	 * @param escenario inicial
	 */
	public ManejadorEscenarios(Stage escenario) {

		this.escenario = escenario;

		try {

			escenario.getIcons().add(new Image(Main.class.getResourceAsStream(ruta)));
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

	public void cargarEscenarioTrabajador(Persona p) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/VistaSesionRaiz.fxml"));
			BorderPane vista = (BorderPane) loader.load();

			BorderPane root = new BorderPane();
			root.setCenter(vista);

			Scene scene = new Scene(root);
			Stage stage = new Stage();
			stage.getIcons().add(new Image(Main.class.getResourceAsStream(ruta)));
			stage.setTitle("Herbario");
			VistaSesionRaizControlador controladorVistaSesionRaiz = loader.getController();
			controladorVistaSesionRaiz.setManejador(this);
			controladorVistaSesionRaiz.setStage(vista);
			controladorVistaSesionRaiz.setStage(stage);
			controladorVistaSesionRaiz.setPersonaEnSesion(p);
			this.personaEnSesion = p;
			stage.setScene(scene);
			stage.show();
			controladorVistaSesionRaiz.cargarMenu();

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

	/**
	 * 
	 * @param tipo   define si es una vista para registro o para actualizacion de
	 *               datos
	 * 
	 * @param origen define desde que vista se llama al escenario
	 */
	public void cargarEscenarioRegistro(String tipo, String origen, Persona p) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/VistaRegistro.fxml"));
			BorderPane vista = (BorderPane) loader.load();

			VistaRegistroControlador controladorVistaRegistro = loader.getController();
			// importante
			controladorVistaRegistro.setManejador(this);

			Scene scene = new Scene(vista);
			Stage stage = new Stage();
			stage.setTitle("Herbario/registro");

			stage.getIcons().add(new Image(Main.class.getResourceAsStream(ruta)));
			stage.setScene(scene);
			stage.show();
			// importante
			controladorVistaRegistro.setStage(stage);
			controladorVistaRegistro.adaptarVista(tipo, origen, p);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que añade el boredn pane vistaGestionarPersona al centro del borde
	 * pane de la VistaSesionRaiz
	 * 
	 * @param pane             Border pane que equivale al la vista sesion raiz
	 * @param personaGestionar atributo para poder reutilizar la vista gestionar
	 */
	public void iniciarVistaGestionar(BorderPane pane, String personaGestionar) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/VistaGestionarPersona.fxml"));
			BorderPane subVista = (BorderPane) loader.load();

			pane.setCenter(subVista);

			VistaGestionarPersonaControlador controladorGestionar = loader.getController();
			controladorGestionar.adaptarVista(personaGestionar);
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

	public void iniciarVistaGestionarFamilia(BorderPane pane) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/VistaGestionarFamilia.fxml"));
			BorderPane subVista = (BorderPane) loader.load();

			pane.setCenter(subVista);

			VistaGestionarFamiliaControlador controladorGestionFamilia = loader.getController();
			controladorGestionFamilia.setManejador(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void iniciarVistaGestionarGenero(BorderPane pane) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/VistaGestionarGenero.fxml"));
			BorderPane subVista = (BorderPane) loader.load();

			pane.setCenter(subVista);

			VistaGestionarGeneroControlador controladorGestionGenero = loader.getController();
			controladorGestionGenero.setManejador(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void iniciarVistaGestionarEspecie(BorderPane pane) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/VistaGestionarEspecie.fxml"));
			BorderPane subVista = (BorderPane) loader.load();

			pane.setCenter(subVista);

			VistaGestionarEspecieControlador controladorGestionEspecie = loader.getController();
			controladorGestionEspecie.setManejador(this);
			
			controladorGestionEspecie.setPane(pane);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void cargarEscenarioAgregarGF(String tipoObjeto) {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/VistaAgregarGF.fxml"));
			BorderPane vista = (BorderPane) loader.load();

			VistaAgregarGFControlador controladorVistaAgregarGF = loader.getController();
			// importante
			//controladorVistaRegistroEspecie.setManejador(this);

			Scene scene = new Scene(vista);
			Stage stage = new Stage();
			stage.setTitle("Herbario/registro");

			stage.getIcons().add(new Image(Main.class.getResourceAsStream(ruta)));
			stage.setScene(scene);
			stage.show();
			// importante
			controladorVistaAgregarGF.setStage(stage);
			controladorVistaAgregarGF.adaptarVista(tipoObjeto);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void cargarEscenarioRegistroEspecie() {
		try {

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("./vista/VistaRegistroEspecie.fxml"));
			BorderPane vista = (BorderPane) loader.load();

			VistaRegistroEspecieControlador controladorVistaRegistroEspecie = loader.getController();
			// importante
			controladorVistaRegistroEspecie.setManejador(this);

			Scene scene = new Scene(vista);
			Stage stage = new Stage();
			stage.setTitle("Herbario/registro");

			stage.getIcons().add(new Image(Main.class.getResourceAsStream(ruta)));
			stage.setScene(scene);
			stage.show();
			// importante
			controladorVistaRegistroEspecie.setStage(stage);

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
			stage.setTitle("Herbario/dialogo");
			stage.getIcons().add(new Image(Main.class.getResourceAsStream(ruta)));
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Stage getEscenario() {
		return escenario;
	}

}

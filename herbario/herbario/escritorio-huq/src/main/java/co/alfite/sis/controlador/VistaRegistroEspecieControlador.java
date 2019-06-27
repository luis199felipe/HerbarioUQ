package co.alfite.sis.controlador;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.util.Date;

import org.apache.tomcat.util.digester.XercesParser;

import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.ImagenPlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.RegistroEspecie.Estado;
import co.alfite.sis.entidades.Trabajador;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;
import co.alfite.sis.modelo.AdministradorDelegado;
import co.alfite.sis.util.Utilidades;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class VistaRegistroEspecieControlador {

	@FXML
	private Button botonCargarImagen;
	@FXML
	private Button realizarRegistro;

	@FXML
	private ImageView imagenPlanta;

//	   @FXML
//	    private ComboBox<> comoboBoxGenero;

//	@FXML
//    private ComboBox<?> comboBoxFamilia;

	@FXML
	private TextField campoNombre;

	@FXML
	private TextField campoNombreFamilia;

	@FXML
	private TextField campoNombreGenero;
	

	private AdministradorDelegado adminDelegado;

	private Stage stage;

	private ManejadorEscenarios miEscenario;
	private byte[] buffers;
	private int readers;
	private Path rutaImagen;
	

	public VistaRegistroEspecieControlador() {
		adminDelegado = adminDelegado.administradorDelegado;
	}

	@FXML
	private void initialize() {

	}

	@FXML
	private void insertarRegistro() {

		RegistroEspecie nuevoRegistro = new RegistroEspecie();
		ImagenPlanta nuevaImagen = new ImagenPlanta();
		nuevaImagen.setImagen(buffers);
		Persona p = miEscenario.getPersonaEnSesion();
		Trabajador trabajadorEnSesion = new Trabajador();

		trabajadorEnSesion.setNombre(p.getNombre());
		trabajadorEnSesion.setEmail(p.getEmail());
		trabajadorEnSesion.setEstado(p.getEstado());
		trabajadorEnSesion.setFechaNacimiento(p.getFechaNacimiento());
		trabajadorEnSesion.setPassword(p.getPassword());
		trabajadorEnSesion.setTelefono(p.getTelefono());
		trabajadorEnSesion.setIdPersona(p.getIdPersona());

		nuevoRegistro.setEstado(Estado.enviado);
		nuevoRegistro.setFecha(new Date());
		nuevoRegistro.setTrabajador(trabajadorEnSesion);
		nuevoRegistro.setImagen(nuevaImagen);

		// en la validacion es donde se debe persistir la espcie asociada al registro y
		// el genero y la familia a esa especie tambien

		nuevoRegistro.setNombreGenero(campoNombreGenero.getText());
		nuevoRegistro.setNombreEspecie(campoNombre.getText());
		nuevoRegistro.setNombreFamilia(campoNombreFamilia.getText());

		boolean r=(adminDelegado.insertarRegistro(nuevoRegistro)!=null);
		if(r) {
			Utilidades.mostrarMensaje("OK", "El registro se envio correctamente", AlertType.INFORMATION);

		}else {
			Utilidades.mostrarMensaje("Error", "no se pudo enviar el registro", AlertType.ERROR);
		}

	}

	@FXML
	private void cargarImagen() {

		FileChooser chooser = new FileChooser();
		chooser.setTitle("Open File");
		chooser.setInitialDirectory(new File(System.getProperty("user.home")));
		chooser.getExtensionFilters()
				.addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.gif"));
		File file = chooser.showOpenDialog(new Stage());
		if (file != null) {
			try {
				byte[] btImagens = Files.readAllBytes(file.toPath());
				InputStream is = new FileInputStream(file);
				buffers = new byte[(int) file.length()];
				readers = is.read(buffers);

				rutaImagen = file.toPath();
				Image img = new Image(new ByteArrayInputStream(btImagens), 199, 199, false, false);

				imagenPlanta.setImage(img);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}

	}

	public void setStage(Stage stage) {
		this.stage = stage;

	}

	public void setManejador(ManejadorEscenarios manejadorEscenarios) {

		this.miEscenario = manejadorEscenarios;
	}

	@FXML
	private void descartar() {
		stage.close();
	}

}

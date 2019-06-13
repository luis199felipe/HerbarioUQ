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
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

//	  @FXML
//	    private TextField campoNombre;
	private Path rutaImagen;

	private Stage stage;

	private ManejadorEscenarios miEscenario;
	private byte[] buffers;
	private int readers;

	@FXML
	private void initialize() {

	}

	@FXML
	private void insertarRegistro() {

		RegistroEspecie nuevoRegistro = new RegistroEspecie();
		ImagenPlanta nuevaImagen = new ImagenPlanta();
		nuevaImagen.setImagen(buffers);

		EspeciePlanta nuevaEspecie=new EspeciePlanta();
		nuevaEspecie.setNombre("esp9000");
			
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
		nuevoRegistro.setEspecie(nuevaEspecie);

		// nuevoRegistro.setGenero(genero);
		// nuevoRegistro.setFamilia(familia);

		try {

			miEscenario.insertarRegistro(nuevoRegistro);
			
			//el hecho de que tenga una especie asociada quiere decir que la especie ya existe
			//ahorafalta probar a  relacionar registro con genero y familia
			
			
			
		} catch (ElementoRepetidoExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

}

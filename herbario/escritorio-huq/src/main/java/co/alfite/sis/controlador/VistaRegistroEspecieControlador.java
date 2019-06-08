package co.alfite.sis.controlador;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Blob;

import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.ImagenPlanta;
import co.alfite.sis.entidades.RegistroEspecie;
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
	private ImageView imagenPlanta;

	private Path rutaImagen;

	@FXML
	private void initialize() {

	}

	@FXML
	private void insertarRegistro() {

		RegistroEspecie x = new RegistroEspecie();
		EspeciePlanta z = new EspeciePlanta();

		ImagenPlanta y = new ImagenPlanta();
		y.setImagen(cargarImagen2());
		y.setEspecie(z);
		
		

	}

	private Blob cargarImagen2() {
		try {
			FileInputStream is = new FileInputStream(rutaImagen.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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
			// String imagepath = file.toURI().toURL().toString();
			byte[] btImagen;
			try {
				btImagen = Files.readAllBytes(file.toPath());

				rutaImagen = file.toPath();
				Image img = new Image(new ByteArrayInputStream(btImagen), 199, 199, false, false);

				imagenPlanta.setImage(img);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}

	}

}

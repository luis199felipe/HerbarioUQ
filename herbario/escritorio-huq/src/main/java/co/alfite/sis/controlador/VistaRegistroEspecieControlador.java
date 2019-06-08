package co.alfite.sis.controlador;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

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
	
	@FXML
	private void initialize() {

	}
	
	@FXML
	private void insertarRegistro() {
		
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

				Image img = new Image(new ByteArrayInputStream(btImagen), 199, 199, false, false);

				Image image = new Image(file.toURI().toString());

				System.out.println("height:" + image.getHeight() + "\nWidth:" + image.getWidth());
				imagenPlanta.setImage(img);
				System.out.println(imagenPlanta.isVisible());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
		
	}
	
}

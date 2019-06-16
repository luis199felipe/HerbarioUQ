package co.alfite.sis.controlador;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import com.sun.faces.application.applicationimpl.Stage;

import co.alfite.sis.Main;
import co.alfite.sis.entidades.ImagenPlanta;
import co.alfite.sis.modelo.AdministradorDelegado;
import co.alfite.sis.modelo.DisplayShelf;
import co.alfite.sis.modelo.UsuarioDelegado;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class VistaUsuarioControlador {

	@FXML
	private Label resenia_2;

	@FXML
	private Button leer_1;

	@FXML
	private Button leer_2;

	@FXML
	private Label resenia_1;

	@FXML
	private ToggleButton like_1;

	@FXML
	private GridPane gridPane;

	@FXML
	private BorderPane paneMasGustadas;

	@FXML
	private BorderPane paneTodas;
	@FXML
	private ToggleButton like_2;

	private BorderPane x = new BorderPane();

	private Timeline animation;

	private UsuarioDelegado usuarioDelegado;

	private ManejadorEscenarios manejadorEscenario;

	private static final double WIDTH = 50, HEIGHT = 1000;

	private static final String[] urls = {

			"./util/Animal1.jpg",

			"./util/Animal2.jpg",

			"./util/Animal3.jpg",

			"./util/Animal4.jpg"

	};

	public VistaUsuarioControlador() {

		usuarioDelegado = usuarioDelegado.UsuarioDelegado;
	}

	@FXML
	private void initialize() {

//		x.setCenter(createContent());
//		paneMasGustadas=x;
//		paneTodas=new BorderPane();
		iniciarVistaGaleria();
	}

	public void setStage(Stage stage) {

	}

	public void setManejador(ManejadorEscenarios manejadorEscenarios) {

		this.manejadorEscenario = manejadorEscenarios;
	}

	public void iniciarVistaGaleria() {

		paneMasGustadas.setCenter(createContent("masMegusta"));
		paneTodas.setCenter(createContent("todas"));

	}

	public Parent createContent(String imagenesMostrar) {

		// load images

		List<ImagenPlanta> allImages = usuarioDelegado.listarImagenes();

		if (!allImages.isEmpty()) {
			InputStream in;
			if (imagenesMostrar.equals("todas")) {
				Image[] images = new Image[allImages.size()];

				for (int i = 0; i < images.length; i++) {

					in = new ByteArrayInputStream(allImages.get(i).getImagen());
					images[i] = new Image(in);

				}

				// create display shelf

				DisplayShelf displayShelf = new DisplayShelf(images, imagenesMostrar, this);

				displayShelf.setPrefSize(WIDTH, HEIGHT);

//		String css = getClass().getResource("./util/DisplayShelf.css").toExternalForm();
//
//		displayShelf.getStylesheets().add(css);

				return displayShelf;
				
			} 
		}
		return new BorderPane();

	}

	@FXML
	void insertarLike1() {

		System.out.println("GGG");
	}

	public void actualizarGaleriaMasMeGusta(double index) {
		resenia_1.setText(index + "");

	}

	public void actualizarGaleriaTodas(double index) {
		resenia_2.setText(index + "");

	}

}

package co.alfite.sis.controlador;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import com.sun.faces.application.applicationimpl.Stage;

import co.alfite.sis.Main;
import co.alfite.sis.entidades.ImagenPlanta;
import co.alfite.sis.entidades.MeGustaEspeciePlanta;
import co.alfite.sis.entidades.Usuario;
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
	private Label numeroLikes_2;

	@FXML
	private Button leer_1;

	@FXML
	private Button leer_2;

	@FXML
	private Label numeroLikes_1;

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

	private UsuarioDelegado usuarioDelegado;

	private ManejadorEscenarios manejadorEscenario;

	private static final double WIDTH = 50, HEIGHT = 1000;

	private List<ImagenPlanta> todasLasImagenes;
	private List<ImagenPlanta> imagenesPorLikes;

	private double indiceMasGustadas;

	private List<MeGustaEspeciePlanta> misLikes;

	private double indiceTodas;

	public VistaUsuarioControlador() {

		usuarioDelegado = usuarioDelegado.UsuarioDelegado;
	}

	@FXML
	private void initialize() {

		iniciarVistaGaleria();

	}

	private void insertarMisLikes() {

		misLikes = usuarioDelegado.listarMeGusta(manejadorEscenario.getPersonaEnSesion().getIdPersona());

	}

	public void setStage(Stage stage) {

	}

	public void setManejador(ManejadorEscenarios manejadorEscenarios) {

		this.manejadorEscenario = manejadorEscenarios;
		insertarMisLikes();
	}

	public void iniciarVistaGaleria() {

		paneMasGustadas.setCenter(createContent("masMegusta"));
		paneTodas.setCenter(createContent("todas"));

	}

	public Parent createContent(String imagenesMostrar) {

		// load images

		InputStream in;
		Image[] images = null;
		boolean existenImagenes = false;
		if (imagenesMostrar.equals("todas")) {
			todasLasImagenes = usuarioDelegado.listarImagenes();
			images = new Image[todasLasImagenes.size()];

			if (!todasLasImagenes.isEmpty()) {
				for (int i = 0; i < images.length; i++) {

					in = new ByteArrayInputStream(todasLasImagenes.get(i).getImagen());
					images[i] = new Image(in);

				}
				existenImagenes = true;
			} else {
				like_1.setVisible(false);
			}
		} else {
			imagenesPorLikes = usuarioDelegado.obtenerListaImagenesOrdenadasPorLikes();
			images = new Image[imagenesPorLikes.size()];

			if (!imagenesPorLikes.isEmpty()) {
				for (int i = 0; i < images.length; i++) {

					in = new ByteArrayInputStream(imagenesPorLikes.get(i).getImagen());
					images[i] = new Image(in);

				}
				existenImagenes = true;

			} else {
				like_2.setVisible(false);
			}
		}

		if (existenImagenes) {
			DisplayShelf displayShelf = new DisplayShelf(images, imagenesMostrar, this);
			displayShelf.setPrefSize(WIDTH, HEIGHT);
			return displayShelf;
		}

//	String css = getClass().getResource("./util/DisplayShelf.css").toExternalForm();
//
//	displayShelf.getStylesheets().add(css);	

		Label info = new Label("No hay imagenes para mostrar");
		return info;

	}

	@FXML
	void accionarLike1() {

		// hay que validar que el like no este
		if (like_1.isSelected()) {
			ImagenPlanta img = imagenesPorLikes.get((int) indiceMasGustadas);

			if (validarLike(img)) {
				System.out.println("VALIDA");
				MeGustaEspeciePlanta nuevoLike = new MeGustaEspeciePlanta();
				nuevoLike.setFecha(new Date());
				Usuario n = (Usuario) manejadorEscenario.getPersonaEnSesion();
				nuevoLike.setUsuario(n);
				nuevoLike.setImagen(img);
				usuarioDelegado.registrarMeGusta(nuevoLike);
				misLikes.add(nuevoLike);
			}

		} else {
			if (!misLikes.isEmpty()) {
				ImagenPlanta x = imagenesPorLikes.get((int) indiceMasGustadas);
				MeGustaEspeciePlanta likeEliminar = null;
				for (int i = 0; i < misLikes.size(); i++) {
					ImagenPlanta im = misLikes.get(i).getImagen();
					if (x.getIdImagen().equals(im.getIdImagen())) {
						likeEliminar = misLikes.get(i);
						break;
					}
				}
				System.out.println(usuarioDelegado.eliminarMegusta(likeEliminar));

			}

		}

		actualizarGaleriaMasMeGusta(indiceMasGustadas);

	}

	private boolean validarLike(ImagenPlanta img) {
		boolean valido = true;
		for (int i = 0; i < misLikes.size() && valido; i++) {

			if (img.getIdImagen().equals(misLikes.get(i).getImagen().getIdImagen())) {
				valido = false;
			}
		}
		return valido;
	}

	@FXML
	void accionarLike2() {

		if (like_2.isSelected()) {
			System.out.println("inserta");

		} else {
			System.out.println("retira");

		}
	}

	public void actualizarGaleriaMasMeGusta(double index) {
		indiceMasGustadas = index;

		ImagenPlanta temp = imagenesPorLikes.get((int) index);

		int id = temp.getIdImagen();
		if (misLikes != null && !misLikes.isEmpty()) {
			for (int i = 0; i < misLikes.size(); i++) {
				if (id == misLikes.get(i).getImagen().getIdImagen()) {

					like_1.setSelected(true);
					break;
				}
			}
		}
		numeroLikes_1.setText("le gusta a " + temp.getNumeroLikes() + " usuarios");

	}

	public void actualizarGaleriaTodas(double index) {
		indiceTodas = index;

		ImagenPlanta temp = todasLasImagenes.get((int) index);

		int id = temp.getIdImagen();
		if (misLikes != null && !misLikes.isEmpty()) {
			for (int i = 0; i < misLikes.size(); i++) {
				if (id == misLikes.get(i).getImagen().getIdImagen()) {

					like_2.setSelected(true);
					break;
				}
			}
		}
		numeroLikes_2.setText("le gusta a " + temp.getNumeroLikes() + " usuarios");

	}

	@FXML
	private void verResenias1() {

		System.out.println(imagenesPorLikes.get((int) indiceMasGustadas) + "poiuytyui");
		manejadorEscenario.cargarEscenarioResenias(imagenesPorLikes.get((int) indiceMasGustadas));
	}

	@FXML
	private void verResenias2() {
		manejadorEscenario.cargarEscenarioResenias(todasLasImagenes.get((int) indiceTodas));

	}

}

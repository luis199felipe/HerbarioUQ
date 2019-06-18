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
	private Button leer_2;

	@FXML
	private GridPane gridPane;

	@FXML
	private BorderPane paneTodas;
	@FXML
	private ToggleButton like_2;

	private UsuarioDelegado usuarioDelegado;

	private ManejadorEscenarios manejadorEscenario;

	private static final double WIDTH = 50, HEIGHT = 1000;

	private List<ImagenPlanta> todasLasImagenes;

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

		paneTodas.setCenter(createContent());

	}

	public Parent createContent() {

		// load images

		InputStream in;
		Image[] images = null;
		boolean existenImagenes = false;
		todasLasImagenes = usuarioDelegado.listarImagenes();
		images = new Image[todasLasImagenes.size()];

		if (!todasLasImagenes.isEmpty()) {
			for (int i = 0; i < images.length; i++) {

				in = new ByteArrayInputStream(todasLasImagenes.get(i).getImagen());
				images[i] = new Image(in);

			}
			existenImagenes = true;
		} else {
			like_2.setVisible(false);
		}

		if (existenImagenes) {
			DisplayShelf displayShelf = new DisplayShelf(images, this);
			displayShelf.setPrefSize(WIDTH, HEIGHT);
			return displayShelf;
		}

		Label info = new Label("No hay imagenes para mostrar");
		return info;

	}

	@FXML
	void accionarLike1() {

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

		// hay que validar que el like no este
		if (like_2.isSelected()) {
			ImagenPlanta img = todasLasImagenes.get((int) indiceTodas);

			if (validarLike(img)) {
				System.out.println("VALIDA");
				MeGustaEspeciePlanta nuevoLike = new MeGustaEspeciePlanta();
				nuevoLike.setFecha(new Date());
				Usuario n = (Usuario) manejadorEscenario.getPersonaEnSesion();
				nuevoLike.setUsuario(n);
				nuevoLike.setImagen(img);
				usuarioDelegado.registrarMeGusta(nuevoLike);
				misLikes.add(nuevoLike);
				like_2.setText("Me gusta");

			}

		} else {
			if (!misLikes.isEmpty()) {
				ImagenPlanta x = todasLasImagenes.get((int) indiceTodas);
				MeGustaEspeciePlanta likeEliminar = null;
				for (int i = 0; i < misLikes.size(); i++) {
					ImagenPlanta im = misLikes.get(i).getImagen();
					if (x.getIdImagen().equals(im.getIdImagen())) {
						likeEliminar = misLikes.get(i);
						break;
					}
				}
				System.out.println(usuarioDelegado.eliminarMegusta(likeEliminar));

				like_2.setText(" No me gusta");

			}

		}

		actualizarGaleriaTodas(indiceTodas);

	}

	public void actualizarGaleriaTodas(double index) {
		indiceTodas = index;
		boolean esta = false;
		ImagenPlanta temp = todasLasImagenes.get((int) index);
		int id = temp.getIdImagen();
		if (misLikes != null && !misLikes.isEmpty()) {
			for (int i = 0; i < misLikes.size(); i++) {
				if (id == misLikes.get(i).getImagen().getIdImagen()) {

					like_2.setSelected(true);
					like_2.setText("Me gusta");
					esta = true;
					break;
				}
			}
		}
		Long n = temp.getNumeroLikes();

		if (n == null) {
			n = 0l;
		}

		if (!esta) {
			like_2.setText("No me gusta");

		}
		numeroLikes_2.setText("le gusta a " + n + " usuarios");

	}

	@FXML
	private void verResenias2() {
		manejadorEscenario.cargarEscenarioResenias(todasLasImagenes.get((int) indiceTodas));

	}

}

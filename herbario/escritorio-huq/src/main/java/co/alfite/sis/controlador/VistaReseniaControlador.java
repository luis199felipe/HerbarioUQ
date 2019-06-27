package co.alfite.sis.controlador;

import java.util.List;

import co.alfite.sis.entidades.ImagenPlanta;
import co.alfite.sis.entidades.RegistroEspecie.Estado;
import co.alfite.sis.entidades.Resenia;
import co.alfite.sis.entidades.Usuario;
import co.alfite.sis.modelo.UsuarioDelegado;
import co.alfite.sis.util.Utilidades;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

public class VistaReseniaControlador {

	@FXML
	private Button botonEnviarResenia;

	@FXML
	private TextArea campoResenia;

	@FXML
	private VBox vBoxResenias;

	private ImagenPlanta img;
	private UsuarioDelegado usuarioDelegado;

	private List<Resenia> resenias;

	private ManejadorEscenarios manejadorEscenarios;

	public VistaReseniaControlador() {
		usuarioDelegado = usuarioDelegado.UsuarioDelegado;
	}

	@FXML
	private void initialize() {

	}

	public void enviarResenia() {

		if (!campoResenia.getText().isEmpty()) {
			Resenia nuevaResenia = new Resenia();
			nuevaResenia.setImagen(img);
			nuevaResenia.setEstado(Resenia.Estado.enviado);

			Usuario u = (Usuario) manejadorEscenarios.getPersonaEnSesion();
			nuevaResenia.setUsuario(u);
			nuevaResenia.setTexto(campoResenia.getText());
			if (usuarioDelegado.registrarResenia(nuevaResenia)) {
				Utilidades.mostrarMensaje("Exito",
						"su reseña sera publicada",
						AlertType.INFORMATION);
			}
		}else {
			Utilidades.mostrarMensaje("Error",
					"el campo no puede estar vacio",
					AlertType.INFORMATION);
		}

	}

	public void setImagen(ImagenPlanta img) {
		this.img = img;
		resenias = usuarioDelegado.listarReseniasPorImagen(img.getIdImagen());
		cargarResenias();

	}

	private void cargarResenias() {

		for (int i = 0; i < resenias.size(); i++) {
			Resenia temp=resenias.get(i);
			String texto="Creada por: "+temp.getUsuario().getNombre()+":"+"\n"+temp.getTexto();
			TextArea nuevaResenia = new TextArea(texto);
			nuevaResenia.setPrefSize(380, 150);
			nuevaResenia.setMinSize(380, 150);
			nuevaResenia.setEditable(false);
			vBoxResenias.getChildren().add(nuevaResenia);

		}
	}

	public void setManejador(ManejadorEscenarios manejadorEscenarios) {
		this.manejadorEscenarios = manejadorEscenarios;

	}

}

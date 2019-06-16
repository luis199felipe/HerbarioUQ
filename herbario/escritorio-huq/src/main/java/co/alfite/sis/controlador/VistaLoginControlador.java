package co.alfite.sis.controlador;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Usuario;
import co.alfite.sis.modelo.AdministradorDelegado;
import co.alfite.sis.util.Utilidades;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;

public class VistaLoginControlador {

	@FXML
	private TextField campoCorreo;
	@FXML
	private PasswordField campoContrasenia;

	@FXML
	private Button botonIniciar;
	@FXML
	private ImageView logoHerbario;

	private ManejadorEscenarios manejador;
	private Stage miEscenario;

	private AdministradorDelegado adminDelegado;

	public VistaLoginControlador() {
		adminDelegado = adminDelegado.administradorDelegado;
	}

	@FXML
	private void initialize() {

		campoCorreo.setText("nfigueroas@uqvirtual.edu.co");
		campoContrasenia.setText("root");

	}

	@FXML
	private void iniciarSesion() {

		String email = campoCorreo.getText();
		if (Utilidades.verificarCorreo(email)) {
			Persona p = adminDelegado.personaPorCredenciales(email, campoContrasenia.getText());

			if (p != null) {
				manejador.cargarEscenarioSesion(p);
				miEscenario.close();
			} else {

				Utilidades.mostrarMensaje("Error", "el correo o la contraseña son icorrectos", AlertType.ERROR);
			}
		} else {
			Utilidades.mostrarMensaje("Error", "Formato de correo invalido", AlertType.ERROR);
		}

	}

	/**
	 * carga la vista adaptada a registro de Usuario o recolector
	 */
	@FXML
	private void registrarTrabajador() {

		manejador.cargarEscenarioRegistro("registrar", "vistaLogin", null);
		miEscenario.close();

	}

	@FXML
	private void recuperarContrasenia() {

		if (!campoCorreo.getText().isEmpty() && Utilidades.verificarCorreo(campoCorreo.getText())) {
			String p = adminDelegado.recuperarContrasenia(campoCorreo.getText());
			if (p != null) {
				enviarConGMail("herbariouq@gmail.com", "alfite12345", campoCorreo.getText(), p);

				Utilidades.mostrarMensaje("Mensaje",
						"Su contraseña ha sido enviada a  el correo: " + campoCorreo.getText(), AlertType.INFORMATION);

			} else {
				Utilidades.mostrarMensaje("Error", "El correo no esta registrado en el herbario", AlertType.ERROR);
			}

		} else {
			Utilidades.mostrarMensaje("Error", "Para recuperar la contraseña deba ingresar al menos su correo y tener un formato valido.",
					AlertType.ERROR);
		}

	}

	private void enviarConGMail(String remitente, String clave, String destinatario, String password) {
		// Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el
		// remitente también.

		// Para la dirección nomcuenta@gmail.com
		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
		props.put("mail.smtp.user", remitente);
		props.put("mail.smtp.clave", clave); // La clave de la cuenta
		props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
		BodyPart texto = new MimeBodyPart();
		MimeMultipart multiParte = new MimeMultipart();
		try {
			texto.setText("la contraseña asociada con el correo proporcionado es " + "password ");

			multiParte.addBodyPart(texto);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		try {
			message.setFrom(new InternetAddress(remitente));
			message.addRecipients(Message.RecipientType.TO, destinatario);

			message.setSubject("Recuperacion de contraseña Herbario Universidad del quindio:");
			message.setText("prueba");
			message.setContent(multiParte);

			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", remitente, clave);

			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException me) {
			me.printStackTrace();
		}
	}

	public void setManejador(ManejadorEscenarios m) {

		this.manejador = m;

	}

	public void setStage(Stage escenario) {
		this.miEscenario = escenario;

	}
}

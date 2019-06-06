package co.alfite.sis.controlador;

import co.alfite.sis.Main;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class VistaLoginControlador {

	@FXML
	private TextField campoCorreo;
	@FXML
	private PasswordField campoContrasenia;
	@FXML
	private ProgressIndicator spinerCarga;
	@FXML
	private Button botonIniciar;
	@FXML
	private ImageView logoHerbario;
	@FXML
	private ComboBox<String> comboBoxCargo;
	@FXML
	private ObservableList<String> listaItems;

	private ManejadorEscenarios manejador;
	private Stage miEscenario;

	private static Properties mailServerProperties;
	private static Session getMailSession;
	private static MimeMessage generateMailMessage;

	public VistaLoginControlador() {

	}

	@FXML
	private void initialize() {

		listaItems = FXCollections.observableArrayList();
		listaItems.add("Administrador");
		listaItems.add("Usuario");
		comboBoxCargo.setItems(listaItems);

	}

	@FXML
	private void iniciarSesion() {
		manejador.cargarEscenarioTrabajador();
		miEscenario.close();
	}

	@FXML
	private void registrarTrabajador() {

		manejador.cargarEscenarioRegistro();
		miEscenario.close();

	}

	@FXML
	private void recuperarContrasenia() {
		enviarConGMail("neidersanchez2000@gmail.com", campoContrasenia.getText(), "nfigueroas@uqvirtual.edu.co");
	}

	private void enviarConGMail(String remitente, String clave, String destinatario) {
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

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);
	//	BodyPart texto = new MimeBodyPart();
		MimeMultipart multiParte = new MimeMultipart();
//		try {
//			texto.setText("informe programa elaborado por Neyder Figueroa ");
//			BodyPart adjunto = new MimeBodyPart();
//			// adjunto.setDataHandler(new DataHandler(new FileDataSource(RUTA_INFORME)));
//			// cambiar esto
//			//adjunto.setFileName("informe-Orange_mall_neyder_figueroa");
//
//			multiParte.addBodyPart(texto);
//			multiParte.addBodyPart(adjunto);
//		} catch (MessagingException e) {
//			e.printStackTrace();
//		}

		try {
			message.setFrom(new InternetAddress(remitente));
			message.addRecipients(Message.RecipientType.TO, destinatario);

			// Se podrían añadir varios de la misma manera
			message.setSubject("Recuperacion de contraseña herbario uq");
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

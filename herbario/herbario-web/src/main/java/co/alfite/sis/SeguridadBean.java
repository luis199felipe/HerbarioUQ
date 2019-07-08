package co.alfite.sis;

import java.io.Serializable;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import co.alfite.sis.ejb.AdministradorEJB;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.util.Util;

/**
 * permite manejar la sesion en la pagina web
 * 
 * @author figue
 *
 */
@FacesConfig(version = Version.JSF_2_3)
@Named("seguridadBean")
@SessionScoped
public class SeguridadBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Persona persona;
	/**
	 * dice si la persona inicio sesion o no
	 */
	private boolean autenticado;
	
	private boolean flagRecolector;
	@EJB
	private AdministradorEJB adminEJB;

	private boolean flagEmpleado;

	private boolean flagUsuario;

	/**
	 * inicializa la informacion base
	 */
	@PostConstruct
	private void init() {
		persona = new Persona();
		autenticado = false;

	}

	public String iniciarSesion() {

		Persona u = adminEJB.personaPorCredenciales(persona.getEmail(), persona.getPassword());
		if (u != null) {
			persona = u;
			System.out.println(persona.getNombre());
			autenticado = true;

			String user = persona.getClass().getSimpleName();

			switch (user) {
	
			case "Empleado":
				setFlagEmpleado(true);
				return "admin/contenido_empleado";
			case "Recolector":
				flagRecolector=true;
				return "admin/contenido_recolector";
			case "Usuario":
				flagUsuario=true;
				return "admin/contenido_usuario";

			default:
			}

		} else {
			Util.mostrarMensaje("Verifique sus credenciales", "Verifique sus credenciales");
		}

		return "";

	}

	public String olvide() {

		return "admin/registrar_persona";

	}
	public String prueba() {

		System.out.println("puedo redireccionar");
		return "admin/registrar_empleado";
	}

	public String cerrarSesion() {

		persona = new Persona();
		autenticado = false;
		System.out.println("sale");
		return "/index";

	}

	
	
	public void recuperarClave() {

		String destinatario = persona.getEmail();
		 System.out.println(persona.getEmail());
		 System.out.println(adminEJB.recuperarContrasenia(persona.getEmail())+"#$%%%");
		try {
		
			enviarConGMail(destinatario, "Contraseña", "Hola, su clave es:" + adminEJB.recuperarContrasenia(persona.getEmail()));
			Util.mostrarMensaje("Email enviado exitosamente", "Email enviado exitosamente");

		} catch (NullPointerException e) {
			Util.mostrarMensaje("Error", "El correo no existe");
		}

	}

	/**
	 * Permite hacer la conexion con gmail para enviar el correo
	 * 
	 * @param destinatario correo de receptor
	 * @param asunto       Asunto del mensaje
	 * @param cuerpo       Cuerpo del mensaje
	 */
	private static void enviarConGMail(String destinatario, String asunto, String cuerpo) {
		// Esto es lo que va delante de @gmail.com en tu cuenta de correo. Es el
		// remitente también.
		String remitente = "herbariouq@gmail.com"; // Para la dirección nomcuenta@gmail.com

		Properties props = System.getProperties();
		props.put("mail.smtp.host", "smtp.gmail.com"); // El servidor SMTP de Google
		props.put("mail.smtp.user", remitente);
		props.put("mail.smtp.clave", "alfite12345"); // La clave de la cuenta
		props.put("mail.smtp.auth", "true"); // Usar autenticación mediante usuario y clave
		props.put("mail.smtp.starttls.enable", "true"); // Para conectar de manera segura al servidor SMTP
		props.put("mail.smtp.port", "587"); // El puerto SMTP seguro de Google
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getDefaultInstance(props);
		MimeMessage message = new MimeMessage(session);

		try {
			message.setFrom(new InternetAddress(remitente));
			// message.addRecipients(Message.RecipientType.TO, destinatario); //Se podrían
			// añadir varios de la misma manera
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			message.setSubject(asunto);
			message.setText(cuerpo);
			Transport transport = session.getTransport("smtp");
			transport.connect("smtp.gmail.com", remitente,"alfite12345" );
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (MessagingException me) {
			me.printStackTrace(); // Si se produce un error
		}
	}
	
	
	public String redirect() {

	
		return "admin/contenido_recolector";

	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public boolean isAutenticado() {
		return autenticado;
	}

	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}

	/**
	 * @return the flagRecolector
	 */
	public boolean isFlagRecolector() {
		return flagRecolector;
	}

	/**
	 * @param flagRecolector the flagRecolector to set
	 */
	public void setFlagRecolector(boolean flagRecolector) {
		this.flagRecolector = flagRecolector;
	}

	public boolean isFlagEmpleado() {
		return flagEmpleado;
	}

	public void setFlagEmpleado(boolean flagEmpleado) {
		this.flagEmpleado = flagEmpleado;
	}

	/**
	 * @return the flagUsuario
	 */
	public boolean isFlagUsuario() {
		return flagUsuario;
	}

	/**
	 * @param flagUsuario the flagUsuario to set
	 */
	public void setFlagUsuario(boolean flagUsuario) {
		this.flagUsuario = flagUsuario;
	}

}

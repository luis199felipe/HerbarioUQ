package co.alfite.sis.controlador;

import java.time.LocalDate;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import co.alfite.sis.entidades.Administrador;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Persona.Estado;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.Trabajador;
import co.alfite.sis.entidades.Usuario;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;
import co.alfite.sis.modelo.AdministradorDelegado;
import co.alfite.sis.util.Utilidades;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VistaRegistroControlador {

	@FXML
	private TextField campoNombre;
	@FXML
	private TextField campoCorreo;
	@FXML
	private TextField campoContrasenia;
	@FXML
	private TextField campoCedula;
	@FXML
	private TextField campoTelefono;
	@FXML
	private ComboBox<String> comboboxCargo;
	@FXML
	private Button botonRealizarRegistro;
	@FXML
	private Button botonRegresar;
	@FXML
	private DatePicker fechaNacimiento;

	@FXML
	private Label labelTitulo;

	@FXML
	private ObservableList<String> listaItems;
	private ManejadorEscenarios manejador;
	private Stage miEscenario;
	private String tipoVista;
	private String origen;
	private AdministradorDelegado adminDelegado;
	private Persona persona;

	public VistaRegistroControlador() {
		adminDelegado = adminDelegado.administradorDelegado;
	}

	@FXML
	private void initialize() {
		listaItems = FXCollections.observableArrayList();
		listaItems.add("Recolector");
		listaItems.add("Usuario");
		listaItems.add("Empleado");
		listaItems.add("Administrador");
		comboboxCargo.setItems(listaItems);
	}

	@FXML
	public void registrarPersona() {

		if (tipoVista.equals("registrar")) {
			try {

				boolean registroValido = false;
				if (validarCampos()) {
					String cargoPersona = comboboxCargo.getSelectionModel().getSelectedItem();
					
					if (cargoPersona.equals("Empleado")) {
						Empleado persona = new Empleado();
						persona.setIdPersona(campoCedula.getText());
						persona.setNombre(campoNombre.getText());
						persona.setPassword(campoContrasenia.getText());
						persona.setEmail(campoCorreo.getText());
						persona.setTelefono(campoTelefono.getText());
						persona.setFechaNacimiento(Utilidades.pasarADate(fechaNacimiento.getValue()));
						persona.setEstado(Estado.activo);
						registroValido = (adminDelegado.insertarEmpleado(persona) != null);

					} else if (cargoPersona.equals("Recolector")) {
						Recolector persona = new Recolector();

						persona.setIdPersona(campoCedula.getText());
						persona.setNombre(campoNombre.getText());
						persona.setPassword(campoContrasenia.getText());
						persona.setEmail(campoCorreo.getText());
						persona.setTelefono(campoTelefono.getText());
						persona.setEstado(Estado.activo);
						persona.setFechaNacimiento(Utilidades.pasarADate(fechaNacimiento.getValue()));

						registroValido = (adminDelegado.insertarRecolector(persona) != null);

					} else if (cargoPersona.equals("Usuario")) {
						Usuario nuevoUsuario = new Usuario();
						nuevoUsuario.setIdPersona(campoCedula.getText());
						nuevoUsuario.setNombre(campoNombre.getText());
						nuevoUsuario.setEstado(Estado.activo);
						nuevoUsuario.setPassword(campoContrasenia.getText());
						nuevoUsuario.setEmail(campoCorreo.getText());
						nuevoUsuario.setTelefono(campoTelefono.getText());
						nuevoUsuario.setFechaNacimiento(Utilidades.pasarADate(fechaNacimiento.getValue()));

						registroValido = adminDelegado.insertarUsusario(nuevoUsuario);
					}

				} 
				if (registroValido) {
					
					Utilidades.mostrarMensaje("Bienvenida", "Bienvenido al Herbario de la universidad del quindio",
							AlertType.INFORMATION);
					regresar();
				}
			} catch (ElementoRepetidoExcepcion e) {
				Utilidades.mostrarMensaje("Error", e.getMessage(), AlertType.ERROR);
			}

		} else {
			actualizarDatosPersona();
		}
	}

	@FXML
	private void actualizarDatosPersona() {

		boolean actualiza = false;
		if (validarCampos()) {
			String tipoPersona = persona.getClass().getSimpleName();
			if (tipoPersona.equals("Recolector")) {
				Recolector nuevo = new Recolector();
				nuevo.setEmail(campoCorreo.getText());
				nuevo.setNombre(campoNombre.getText());
				nuevo.setPassword(campoContrasenia.getText());
				nuevo.setTelefono(campoTelefono.getText());
				nuevo.setFechaNacimiento(Utilidades.pasarADate(fechaNacimiento.getValue()));
				nuevo.setIdPersona(campoCedula.getText());
				nuevo.setEstado(persona.getEstado());
				actualiza = (adminDelegado.actualizarRecolector(nuevo) != null);
			} else if (tipoPersona.equals("Administrador")) {
				Administrador nuevo = new Administrador();
				nuevo.setEmail(campoCorreo.getText());
				nuevo.setNombre(campoNombre.getText());
				nuevo.setPassword(campoContrasenia.getText());
				nuevo.setTelefono(campoTelefono.getText());
				nuevo.setFechaNacimiento(Utilidades.pasarADate(fechaNacimiento.getValue()));
				nuevo.setIdPersona(campoCedula.getText());
				nuevo.setEstado(persona.getEstado());
				// actualiza= (adminDelegado.actualizarAdministrador(nuevo)!=null);
			} else if (tipoPersona.equals("Empleado")) {
				Empleado nuevo = new Empleado();
				nuevo.setEmail(campoCorreo.getText());
				nuevo.setNombre(campoNombre.getText());
				nuevo.setPassword(campoContrasenia.getText());
				nuevo.setTelefono(campoTelefono.getText());
				nuevo.setFechaNacimiento(Utilidades.pasarADate(fechaNacimiento.getValue()));
				nuevo.setIdPersona(campoCedula.getText());
				nuevo.setEstado(persona.getEstado());
				actualiza = (adminDelegado.actualizarEmpleado(nuevo) != null);
			}
		}
		if (actualiza) {
			Utilidades.mostrarMensaje("OK", "La actualizacion de datos se ha completado satisfactoriamente",
					AlertType.INFORMATION);
			regresar();
		} else {
			Utilidades.mostrarMensaje("ERROR", "La actualizacion de datos  NO se ha completao satisfactoriamente",
					AlertType.ERROR);
		}

	}

	@FXML
	public void regresar() {
		if (!tipoVista.equals("actualizar") && !origen.equals("vistaAdministradorRecolector")
				&& !origen.equals("vistaAdministradorEmpleado")) {
			manejador.escenarioInicial();
		}
		miEscenario.close();
	}

	private boolean validarCampos() {

		boolean valido = true;
		if (comboboxCargo.getSelectionModel().getSelectedItem() == null || campoCedula.getText().isEmpty()
				|| campoNombre.getText().isEmpty() || campoContrasenia.getText().isEmpty()
				|| campoCorreo.getText().isEmpty() || campoTelefono.getText().isEmpty()
				|| Utilidades.pasarADate(fechaNacimiento.getValue()) == null) {
			Utilidades.mostrarMensaje("Error", "Debe llenar todos los campos", AlertType.ERROR);
			valido = false;
		}
		
		
		if(valido && !Utilidades.verificarFecha(fechaNacimiento.getValue()) ) {
			Utilidades.mostrarMensaje("Error", "La edad valida minima es de 15 años ", AlertType.ERROR);
			valido = false;
		}
		
		if(valido && !Utilidades.verificarCorreo(campoCorreo.getText()) ) {
			Utilidades.mostrarMensaje("Error", "El formato del correo es incorrecto.", AlertType.ERROR);
			valido = false;
		}
		
		if(valido && !Utilidades.verificarNumero(campoTelefono.getText()) ) {
			Utilidades.mostrarMensaje("Error", "El telefono debe ser solo numeros", AlertType.ERROR);
			valido = false;
		}
		
		return valido;
	}

	public void setManejador(ManejadorEscenarios m) {
		this.manejador = m;
	}

	public void setStage(Stage stage) {
		this.miEscenario = stage;

	}

	public void adaptarVista(String tipo, String origen, Persona p) {
		this.tipoVista = tipo;
		this.origen = origen;
		this.persona = p;
		if (tipo.equals("actualizar")) {
			labelTitulo.setText("Actualizacion de datos");
			

			campoNombre.setText(p.getNombre());
			campoCedula.setText(p.getIdPersona());
			campoCedula.setEditable(false);
			campoCorreo.setText(p.getEmail());
			campoTelefono.setText(p.getTelefono());
			campoContrasenia.setText(p.getPassword());

			fechaNacimiento.valueProperty().set((Utilidades.pasarALocalDate(p.getFechaNacimiento())));

			botonRealizarRegistro.setText("Actualizar Datos");
		}

		if (origen.equals("vistaLogin")) {

			listaItems.remove(3);
			listaItems.remove(2);
			
		}
		if (origen.equals("vistaAdministradorRecolector")) {
			listaItems.clear();
			listaItems.add("Recolector");
		} else if (origen.equals("vistaAdministradorEmpleado")) {
			listaItems.clear();
			listaItems.add("Empleado");
		} else if (origen.equals("vistaGestionarPersona")) {
			listaItems.clear();
			listaItems.add(p.getClass().getSimpleName());
			comboboxCargo.setEditable(false);
		}
		
		System.out.println(origen);
		comboboxCargo.setItems(listaItems);
	}

}

package co.alfite.sis.controlador;

import javafx.fxml.FXML;

public class VistaGestionarGFEControlador {

	private ManejadorEscenarios miEscenario;
	private String elementoAGestionar;

	@FXML
	private void initialize() {
//la idea es reutilizar varias vistas para acciones parecidas, para eso es el 
		// elementoAGestionar
		// la validacion de todos lo componenetes que tambien falta por declarar se hace
		//lo mismo se hace en la ventana gestionar
		// por codigo

	
	}

	public void setManejador(ManejadorEscenarios manejadorEscenarios) {
		this.miEscenario = manejadorEscenarios;

	}

	
	public void adaptarVista(String elementoAGestionar) {
		this.elementoAGestionar = elementoAGestionar;
	}

}

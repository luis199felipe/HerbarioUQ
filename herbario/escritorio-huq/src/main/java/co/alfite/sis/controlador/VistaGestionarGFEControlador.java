package co.alfite.sis.controlador;

import javafx.fxml.FXML;

public class VistaGestionarGFEControlador {

	private ManejadorEscenarios miEscenario;
	private String elementoAGestionar;

	@FXML
	private void initialize() {

	}

	public void setManejador(ManejadorEscenarios manejadorEscenarios) {
		this.miEscenario = manejadorEscenarios;

	}

	/**
	 * Adapta las etiquetas de la vista a genero, familia o especie
	 * 
	 * @param elementoAGestionar puede ser un genero ,familia,especie
	 */
	public void adaptarVista(String elementoAGestionar) {
		this.elementoAGestionar = elementoAGestionar;
	}

}

package co.alfite.sis.excepciones;

import java.io.Serializable;

public class ElementoRepetidoExcepcion extends Exception  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ElementoRepetidoExcepcion(String mensaje) {

		super(mensaje);
	}

}

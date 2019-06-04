package co.alfite.sis.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Util {

	public static void mostrarMensaje(String mensaje, String titulo) {

		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, facesMsg);
	}
}

package co.alfite.sis.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
/**
 * Permite manejar las operaciones generales de la capa de presentacion
 * @author Neyder Figueroa, Melissa Alvarez, Felipe Tejada
 * @version 1.0
 */
public final class Utilidades {

	/**
	 * permite mostrar un texto informativo en pantalla
	 * @param titulo subtitulo de la alerta
	 * @param mensaje mensaje principal
	 */
	public static boolean mostrarMensaje( String titulo, String mensaje, AlertType tipo) {
		Alert alert = new Alert(tipo);
		alert.setTitle("Herbario UQ");
		alert.setHeaderText(titulo);
		alert.setContentText(mensaje);
		alert.showAndWait();	
	
		return false;
	}
	
	/**
	 * permite hacer un casting de localDate a Date
	 * @param localDate que se quiere cambiar
	 * @return una fecha en formato date
	 */
	public static Date pasarADate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	/**
	 * permite hacer un casting de date a localdate
	 * @param date que se desea cambiar de formato
	 * @return una fecha en formato local date
	 */
	public static LocalDate pasarALocalDate(Date date) {
		 return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
	}
	
	public static boolean verificarCorreo(String email) {
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
 
        if (mather.find() == true) {
            return true;
        } else {
        	return false;
        }
	}
	
	public static boolean verificarFecha(LocalDate value) {
		if (value.getYear() > LocalDate.now().getYear()-14 ) {
			return false;
		}
		return true;
	}

	public static boolean verificarNumero(String text) {
		try {
			int n = Integer.parseInt(text);
		} catch (Exception e) {
			return false;	
		}
		return true;
	}
	
	
}

package co.alfite.sis;

public class RegistroFechaDTO {
	
	
	private int idRegistro;
	private String nombreEspecie;
	private String nombreGenero;
	
	private String idPersona;
	private String email;
	
	public RegistroFechaDTO(int idRegistro, String nombreEspecie, String nombreGenero, String email, String idPersona) {
		this.idRegistro = idRegistro;
		this.nombreEspecie = nombreEspecie;
		this.nombreGenero = nombreGenero;
		this.idPersona = idPersona;
		this.email = email;
	}	

	public int getIdRegistro() {
		return idRegistro;
	}

	public void setIdRegistro(int idRegistro) {
		this.idRegistro = idRegistro;
	}

	public String getNombreEspecie() {
		return nombreEspecie;
	}

	public void setNombreEspecie(String nombreEspecie) {
		this.nombreEspecie = nombreEspecie;
	}

	public String getNombreGenero() {
		return nombreGenero;
	}

	public void setNombreGenero(String nombreGenero) {
		this.nombreGenero = nombreGenero;
	}

	public String getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(String idPersona) {
		this.idPersona = idPersona;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}

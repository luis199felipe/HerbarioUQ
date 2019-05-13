package co.alfite.sis;

public class DTO {

	private String cedula;
	private long numeroRegistros;

	public DTO(String cedula, long numeroRegistros) {

		this.cedula = cedula;
		this.numeroRegistros = numeroRegistros;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public long getNumeroRegistros() {
		return numeroRegistros;
	}

	public void setNumeroRegistros(long numeroRegistros) {
		this.numeroRegistros = numeroRegistros;
	}
	
	
}

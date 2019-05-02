package co.alfite.sis.entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * Entity implementation class for Entity: Resenia
 *
 */
@Entity
public class Resenia implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int idResenia;
	
	private String texto;
	
	/**
	 * llave foranea: conecta registro con especiePlanta
	 */
	@Column(length = 10, nullable = false)
	private String idEspecie;   
	/**
	 * estado del registro (enviado, aprobado, rechazado)
	 */
	private enum Estado{
		enviado,aprovado,rechazado
	}
	@Enumerated(EnumType.STRING)
	@Column(length = 10)
	private Estado estado;
	
	
	private static final long serialVersionUID = 1L;

	public Resenia() {
		super();
	}

	public int getIdResenia() {
		return idResenia;
	}

	public void setIdResenia(int idResenia) {
		this.idResenia = idResenia;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getIdEspecie() {
		return idEspecie;
	}

	public void setIdEspecie(String idEspecie) {
		this.idEspecie = idEspecie;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
   
}

package co.alfite.sis.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: HerbarioUQ
 * 
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * @version 1.0
 */
@Entity

public class HerbarioUQ implements Serializable {

	

	/**
	 * un Herbario tiene muchas FamiliaPlanta
	 */
	@OneToMany(mappedBy = "herbario")
	private List<FamiliaPlanta> familias;

	/**
	 * un HErbario tiene muchas Personas (Usuarios, Administradores, Empleados,
	 * Recolectores)
	 */
	@OneToMany(mappedBy = "herbario")
	private List<Persona> personas;

	/**
	 * Identificacion unica de un Herbario
	 */
	@Id
	private String idHerbario;

	private static final long serialVersionUID = 1L;

	public HerbarioUQ() {
		super();
	}

	public List<FamiliaPlanta> getFamilias() {
		return familias;
	}

	public void setFamilias(List<FamiliaPlanta> familias) {
		this.familias = familias;
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}

	public String getIdHerbario() {
		return idHerbario;
	}

	public void setIdHerbario(String idHerbario) {
		this.idHerbario = idHerbario;
	}

}

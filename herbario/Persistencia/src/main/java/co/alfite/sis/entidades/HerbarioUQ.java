package co.alfite.sis.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: HerbarioUQ
 *
 */
@Entity

public class HerbarioUQ implements Serializable {

	@OneToMany(mappedBy = "herbario")
	private List<FamiliaPlanta> familias;
	@OneToMany(mappedBy = "herbario")
	private List<Persona> personas;
	@OneToMany(mappedBy = "herbario")
	private List<Persona> usuarios;

	private static final long serialVersionUID = 1L;

	@Id
	private String idHerbario;

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

	public List<Persona> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Persona> usuarios) {
		this.usuarios = usuarios;
	}

	public String getIdHerbario() {
		return idHerbario;
	}

	public void setIdHerbario(String idHerbario) {
		this.idHerbario = idHerbario;
	}

 }

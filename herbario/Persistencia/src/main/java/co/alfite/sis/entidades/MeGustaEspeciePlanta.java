package co.alfite.sis.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: MeGustaEspeciePlanta
 * 
 * @author MelissaAlvarezCastro, NeyderFigueroaSanchez, LuisFelipeTejadaPadilla
 * @version 1.0
 */
@Entity
@NamedQueries({
@NamedQuery(name=MeGustaEspeciePlanta.MEGUSTAESPECIE_GET_ALL ,query="select megustaEspecie from MeGustaEspeciePlanta megustaEspecie"),
@NamedQuery(name=MeGustaEspeciePlanta.MEGUSTAESPECIE_ESPECIE ,query="select megusta from MeGustaEspeciePlanta megusta where megusta.especie.idEspecie = :esp "),
@NamedQuery(name=MeGustaEspeciePlanta.MEGUSTAESPECIE_ESPECIEMASMEGUSTAS ,query="select count(megusta.especie.idEspecie) as n from MeGustaEspeciePlanta megusta group by megusta.especie order by n desc")
})
public class MeGustaEspeciePlanta implements Serializable {

	/**
	 * Muchos MeGustaEspeciePlanta pertenecen a un Usuario
	 */
	
	@ManyToOne
	private Usuario usuario;
	
	public static final String MEGUSTAESPECIE_GET_ALL = "MeGustaEspecieGetAll";
	public static final String MEGUSTAESPECIE_ESPECIE = "MeGustaDeUnaEspecieEspecifica";
	public static final String MEGUSTAESPECIE_ESPECIEMASMEGUSTAS = "EspecieConMasMegustas";

	
	/**
	 * Muchos MeGustaEspeciePlanta pertenecen a una EspeciePlanta
	 */
	@ManyToOne
	private EspeciePlanta especie;

	/**
	 * Identificacion unica de MegustaEspeciePlanta
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idMegusta;

	/**
	 * fecha de publicacion del MeGustaEspeciePlanta
	 */
	private Date fecha;

	private static final long serialVersionUID = 1L;

	public MeGustaEspeciePlanta() {
		super();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public EspeciePlanta getEspecie() {
		return especie;
	}

	public void setEspecie(EspeciePlanta especie) {
		this.especie = especie;
	}

	public Integer getIdMegusta() {
		return idMegusta;
	}

	public void setIdMegusta(Integer idMegusta) {
		this.idMegusta = idMegusta;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}

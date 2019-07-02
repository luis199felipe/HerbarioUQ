package co.alfite.sis;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.alfite.sis.ejb.AdministradorEJB;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.ImagenPlanta;
import co.alfite.sis.entidades.Trabajador;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.RegistroEspecie.Estado;

@FacesConfig(version=Version.JSF_2_3)
@Named("generoBean")
@ApplicationScoped
public class RegistroBean {
	
	private Trabajador trabajador;
	private String nombreEspecie;
	private String nombreGenero;
	private String nombreFamilia;
	private EspeciePlanta especie;
	private ImagenPlanta imagen;
	private Integer idRegistro;
	private Estado estado;
	private String mensaje;
	private Date fecha;
	
	private List<RegistroEspecie> registros;
	private List<EspeciePlanta> plantas;
	private List<Trabajador> trabajadores;
	
	
	@EJB
	private AdministradorEJB admiEJB;
	
	public RegistroBean() {
		
	}
	
	@PostConstruct
	private void init() {
		registros = admiEJB.listarRegistros();
	}
	
	public Trabajador getTrabajador() {
		return trabajador;
	}
	public void setTrabajador(Trabajador trabajador) {
		this.trabajador = trabajador;
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
	public String getNombreFamilia() {
		return nombreFamilia;
	}
	public void setNombreFamilia(String nombreFamilia) {
		this.nombreFamilia = nombreFamilia;
	}
	public EspeciePlanta getEspecie() {
		return especie;
	}
	public void setEspecie(EspeciePlanta especie) {
		this.especie = especie;
	}
	public ImagenPlanta getImagen() {
		return imagen;
	}
	public void setImagen(ImagenPlanta imagen) {
		this.imagen = imagen;
	}
	public Integer getIdRegistro() {
		return idRegistro;
	}
	public void setIdRegistro(Integer idRegistro) {
		this.idRegistro = idRegistro;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
}

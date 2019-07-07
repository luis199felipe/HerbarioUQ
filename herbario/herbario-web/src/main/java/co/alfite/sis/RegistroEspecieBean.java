package co.alfite.sis;

import java.awt.Graphics2D;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import com.sun.prism.RectShadowGraphics;

import co.alfite.sis.ejb.AdministradorEJB;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.ImagenPlanta;
import co.alfite.sis.entidades.Trabajador;
import co.alfite.sis.util.Util;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.RegistroEspecie.Estado;

@FacesConfig(version = Version.JSF_2_3)
@Named("registroBean")
@ApplicationScoped
public class RegistroEspecieBean {

	@Inject
	@ManagedProperty(value = "#{seguridadBean.persona}")
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
	private RegistroEspecie registroEspecie;

	private UploadedFile img;

	private StreamedContent graphicImage;

	private List<RegistroEspecie> registros;
	private List<EspeciePlanta> plantas;
	private List<Trabajador> trabajadores;

	@EJB
	private AdministradorEJB admiEJB;

	public RegistroEspecieBean() {

	}

	@PostConstruct
	private void init() {
		registros = admiEJB.listarRegistros();
	
	}

	public void nuevoRegistro() {

		registroEspecie = null;

		registroEspecie = new RegistroEspecie();

		registroEspecie.setEstado(estado.enviado);
		registroEspecie.setFecha(new Date());
		// registroEspecie.setImagen(imagen);
		registroEspecie.setNombreEspecie(nombreEspecie);
		registroEspecie.setNombreFamilia(nombreFamilia);
		registroEspecie.setNombreGenero(nombreGenero);

		// debe quedas asi
		// la persistencia se hace desde el rol solicitante
	}

	public String upload() {
		
		
		return "/admin/registro_especie";

	}

	public void handleFileUpload(FileUploadEvent event) {
		System.out.println(event.getFile().getFileName());
		//File im = new File(event.getFile().getFileName());
	
		img=event.getFile();
		
		
		 graphicImage = new DefaultStreamedContent(new ByteArrayInputStream(img.getContents()), "image/png"); 
		
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

	/**
	 * @return the registroEspecie
	 */
	public RegistroEspecie getRegistroEspecie() {
		return registroEspecie;
	}

	/**
	 * @param registroEspecie the registroEspecie to set
	 */
	public void setRegistroEspecie(RegistroEspecie registroEspecie) {
		this.registroEspecie = registroEspecie;
	}

	public UploadedFile getImg() {
		return img;
	}

	public void setImg(UploadedFile img) {
		this.img = img;
	}

	/**
	 * @return the graphicImage
	 */
	public StreamedContent getGraphicImage() {
		return graphicImage;
	}

	/**
	 * @param graphicImage the graphicImage to set
	 */
	public void setGraphicImage(StreamedContent graphicImage) {
		this.graphicImage = graphicImage;
	}

}

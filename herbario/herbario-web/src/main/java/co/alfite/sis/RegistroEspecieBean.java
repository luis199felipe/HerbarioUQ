package co.alfite.sis;

import java.io.ByteArrayInputStream;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.ManagedProperty;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import co.alfite.sis.ejb.AdministradorEJB;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.entidades.ImagenPlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Trabajador;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.RegistroEspecie.Estado;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;

@FacesConfig(version = Version.JSF_2_3)
@Named("registroEspecieBean")
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
	private List<RegistroEspecie> allRegistros;

	@EJB
	private AdministradorEJB admiEJB;

	@PostConstruct
	private void init() {
		registros = admiEJB.listarRegistrosRecolector(trabajador.getIdPersona());

		allRegistros = admiEJB.listarRegsitrosPorEstado(Estado.enviado);

	}

	public void nuevoRegistro() {

		registroEspecie = null;
		registroEspecie = new RegistroEspecie();
		registroEspecie.setEstado(Estado.enviado);
		registroEspecie.setFecha(new Date());

		ImagenPlanta im = new ImagenPlanta();
		im.setImagen(img.getContents());
		im.setRegistro(registroEspecie);
		registroEspecie.setImagen(im);
		registroEspecie.setNombreEspecie(nombreEspecie);
		registroEspecie.setNombreFamilia(nombreFamilia);
		registroEspecie.setNombreGenero(nombreGenero);

		registroEspecie.setTrabajador(trabajador);
		admiEJB.insertarRegistro(registroEspecie);

	}

	public void rechazarRegistro() {

		admiEJB.validarRegistro(registroEspecie.getIdRegistro(), Estado.rechazado);
	}

	public void aceptarRegistro() {
		
		
		System.out.println("aceta");
			//la idea es que cuando cambie el estado a aprobado se persista una especie nueva
					// y ademas debe haber una opcion de si la espceie ya existe tomar la imagen del
					// registro y agregarla a la lista de imagenes de la especie
									
		admiEJB.validarRegistro(registroEspecie.getIdRegistro(), Estado.aprobado);
						FamiliaPlanta fam = admiEJB.buscarFamiliaPlanta(registroEspecie.getNombreFamilia());
						if (fam == null) {

							FamiliaPlanta nuevaFamilia = new FamiliaPlanta();
							nuevaFamilia.setNombre(registroEspecie.getNombreFamilia());
							try {
								fam = admiEJB.insertarFamilia(nuevaFamilia);
							} catch (ElementoRepetidoExcepcion e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						
						GeneroPlanta gen = admiEJB.buscarGeneroPlanta(registroEspecie.getNombreFamilia());

						if (gen == null) {
							GeneroPlanta nuevoGenero = new GeneroPlanta();
							nuevoGenero.setNombre(registroEspecie.getNombreFamilia());
							nuevoGenero.setFamiliaPlanta(fam);
							try {
								gen = admiEJB.insertarGenero(nuevoGenero);
							} catch (ElementoRepetidoExcepcion e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						EspeciePlanta esp = admiEJB.buscarEspeciePlanta(registroEspecie.getNombreEspecie());

						if (esp == null) {

							EspeciePlanta nuevaEspecie = new EspeciePlanta();
							nuevaEspecie.setGeneroPlanta(gen);
							nuevaEspecie.setNombre(registroEspecie.getNombreEspecie());
							admiEJB.insertarEspecie(nuevaEspecie);

							ImagenPlanta im = admiEJB.buscarImagenPlanta(registroEspecie.getImagen().getIdImagen());
							
							im.setEspecie(nuevaEspecie);
						    admiEJB.actualizarImagenPlanta(im);

						}

					}

				

	}

	public String upload() {

		return "/admin/registro_especie";

	}

	public void handleFileUpload(FileUploadEvent event) {
		System.out.println(event.getFile().getFileName());
		// File im = new File(event.getFile().getFileName());

		img = event.getFile();

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

	/**
	 * @return the registros
	 */
	public List<RegistroEspecie> getRegistros() {
		return registros;
	}

	/**
	 * @param registros the registros to set
	 */
	public void setRegistros(List<RegistroEspecie> registros) {
		this.registros = registros;
	}

	/**
	 * @return the allRegistros
	 */
	public List<RegistroEspecie> getAllRegistros() {
		return allRegistros;
	}

	/**
	 * @param allRegistros the allRegistros to set
	 */
	public void setAllRegistros(List<RegistroEspecie> allRegistros) {
		this.allRegistros = allRegistros;
	}

}

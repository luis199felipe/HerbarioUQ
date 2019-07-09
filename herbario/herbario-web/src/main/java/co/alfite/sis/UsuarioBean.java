package co.alfite.sis;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
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

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import co.alfite.sis.ejb.UsuarioEJB;
import co.alfite.sis.entidades.ImagenPlanta;
import co.alfite.sis.entidades.MeGustaEspeciePlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Resenia;
import co.alfite.sis.entidades.Resenia.Estado;
import co.alfite.sis.entidades.Usuario;

@FacesConfig(version = Version.JSF_2_3)
@Named("usuarioBean")
@ApplicationScoped
public class UsuarioBean {

	/**
	 * lista de los likes del usuario
	 */
	List<MeGustaEspeciePlanta> mislikes;
	/**
	 * lista de imagenes mas gustadas ordenadas en forma desecendente
	 */
	List<ImagenPlanta> imagenesMasGustadas;
	/**
	 * lista de imagenes
	 */
	List<ImagenPlanta> imagenes;
	List<byte[]> imagenes1;
	List<StreamedContent> imagenes2;
	
	private String cantImagenes;
	@EJB
	UsuarioEJB usuarioEJB;

	/**
	 * Usuario en sesion
	 */
	@Inject
	@ManagedProperty(value = "#{seguridadBean.persona}")
	Persona usuario;
	private String textoResenia;
	private ImagenPlanta imagen;

	private StreamedContent img;
	public UsuarioBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	private void init() {
		
		
		mislikes = usuarioEJB.obtenerListaMeGusta(usuario.getIdPersona());
		imagenes = usuarioEJB.obtenerListaImagenes();
		
		imagenesMasGustadas = usuarioEJB.obtenerListaImagenesOrdenadasPorLikes();

		imagenes1=usuarioEJB.obtenerListaImagenesByte();
		
		cantImagenes=imagenes.size()+"";
		
		img=new DefaultStreamedContent(new ByteArrayInputStream(imagenes1.get(0)), "image/png");
	imagenes2=new ArrayList<>();
	
	for (int i = 0; i <imagenes1.size(); i++) {
		
		imagenes2.add(new DefaultStreamedContent(new ByteArrayInputStream(imagenes1.get(i)), "image/png"));
	}

		// likes de un a imagen y reseniasd de una imagem
	}

	/**
	 * inserta un like en la tabla
	 */
	public void insertarLike() {
		MeGustaEspeciePlanta nuevoLike = new MeGustaEspeciePlanta();
		nuevoLike.setFecha(new Date());
		//nuevoLike.setUsuario(usuario);
		nuevoLike.setImagen(imagen);
		usuarioEJB.insertarMegusta(nuevoLike);
		imagenes = usuarioEJB.obtenerListaImagenes();
		imagenesMasGustadas = usuarioEJB.obtenerListaImagenesOrdenadasPorLikes();

	}
	
	public String cantImagenes() {
		
		return imagenes.size()+"";
	}
	
	
	public StreamedContent toImagen(ImagenPlanta im) {
		
		
	return new DefaultStreamedContent(new ByteArrayInputStream(im.getImagen()), "image/png");
		
	}

	/**
	 * inserta una resenia en la base de datos
	 */
	public void insertarResenia() {

		Resenia nuevaResenia = new Resenia();

		nuevaResenia.setTexto(this.textoResenia);
		nuevaResenia.setImagen(imagen);
		//nuevaResenia.setUsuario(usuario);
		nuevaResenia.setEstado(Estado.aprobado);
		usuarioEJB.insertarResenia(nuevaResenia);

	}

	/**
	 * elimina un like de la base de datos
	 */
	public void eliminarLike() {
		
		//usuarioEJB.eliminarMegusta(meGusta)

	}

	/**
	 * elimina una resenia de la base de datos
	 */
	public void eliminarResenia() {

	}

	/**
	 * 
	 */

	public void registrarUsuario() {

	}

	/**
	 * 
	 * @return
	 */
	public List<MeGustaEspeciePlanta> getMislikes() {
		return mislikes;
	}

	/**
	 * 
	 * @param mislikes
	 */

	public void setMislikes(List<MeGustaEspeciePlanta> mislikes) {
		this.mislikes = mislikes;
	}

	/**
	 * 
	 * @return
	 */
	public UsuarioEJB getUsuarioEJB() {
		return usuarioEJB;
	}

	/**
	 * 
	 * @param usuarioEJB
	 */

	public void setUsuarioEJB(UsuarioEJB usuarioEJB) {
		this.usuarioEJB = usuarioEJB;
	}


	/**
	 * 
	 * @param usuario
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * 
	 * @return
	 */
	public List<ImagenPlanta> getImagenesMasGustadas() {
		return imagenesMasGustadas;
	}

	/**
	 * @param imagenesMasGustadas
	 */
	public void setImagenesMasGustadas(List<ImagenPlanta> imagenesMasGustadas) {
		this.imagenesMasGustadas = imagenesMasGustadas;
	}

	public String getTextoResenia() {
		return textoResenia;
	}

	public void setTextoResenia(String textoResenia) {
		this.textoResenia = textoResenia;
	}

	public ImagenPlanta getImagen() {
		return imagen;
	}

	public void setImagen(ImagenPlanta imagen) {
		this.imagen = imagen;
	}

	/**
	 * @return the imagenes
	 */
	public List<ImagenPlanta> getImagenes() {
		return imagenes;
	}

	/**
	 * @param imagenes the imagenes to set
	 */
	public void setImagenes(List<ImagenPlanta> imagenes) {
		this.imagenes = imagenes;
	}

	/**
	 * @return the usuario
	 */
	public Persona getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Persona usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the imagenes1
	 */
	public List<byte[]> getImagenes1() {
		return imagenes1;
	}

	/**
	 * @param imagenes1 the imagenes1 to set
	 */
	public void setImagenes1(List<byte[]> imagenes1) {
		this.imagenes1 = imagenes1;
	}

	/**
	 * @return the imagenes2
	 */
	public List<StreamedContent> getImagenes2() {
		return imagenes2;
	}

	/**
	 * @param imagenes2 the imagenes2 to set
	 */
	public void setImagenes2(List<StreamedContent> imagenes2) {
		this.imagenes2 = imagenes2;
	}

	/**
	 * @return the img
	 */
	public StreamedContent getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(StreamedContent img) {
		this.img = img;
	}

	public String getCantImagenes() {
		return cantImagenes;
	}

	public void setCantImagenes(String cantImagenes) {
		this.cantImagenes = cantImagenes;
	}

}

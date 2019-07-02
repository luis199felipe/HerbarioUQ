package co.alfite.sis;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Named;

import co.alfite.sis.ejb.UsuarioEJB;
import co.alfite.sis.entidades.ImagenPlanta;
import co.alfite.sis.entidades.MeGustaEspeciePlanta;
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
	@EJB
	UsuarioEJB usuarioEJB;

	/**
	 * Usuario en sesion
	 */
	Usuario usuario;
	private String textoResenia;
	private ImagenPlanta imagen;

	public UsuarioBean() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	private void init() {
		mislikes = usuarioEJB.obtenerListaMeGusta(usuario.getIdPersona());
		imagenes = usuarioEJB.obtenerListaImagenes();
		imagenesMasGustadas = usuarioEJB.obtenerListaImagenesOrdenadasPorLikes();

		// likes de un a imagen y reseniasd de una imagem
	}

	/**
	 * inserta un like en la tabla
	 */
	public void insertarLike() {
		MeGustaEspeciePlanta nuevoLike = new MeGustaEspeciePlanta();
		nuevoLike.setFecha(new Date());
		nuevoLike.setUsuario(usuario);
		nuevoLike.setImagen(imagen);
		usuarioEJB.insertarMegusta(nuevoLike);
		imagenes = usuarioEJB.obtenerListaImagenes();
		imagenesMasGustadas = usuarioEJB.obtenerListaImagenesOrdenadasPorLikes();

	}

	/**
	 * inserta una resenia en la base de datos
	 */
	public void insertarResenia() {

		Resenia nuevaResenia = new Resenia();

		nuevaResenia.setTexto(this.textoResenia);
		nuevaResenia.setImagen(imagen);
		nuevaResenia.setUsuario(usuario);
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
	 * @return
	 */
	public Usuario getUsuario() {
		return usuario;
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

}

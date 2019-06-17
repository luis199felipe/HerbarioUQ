package co.alfite.sis.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.alfite.sis.entidades.ImagenPlanta;
import co.alfite.sis.entidades.MeGustaEspeciePlanta;
import co.alfite.sis.entidades.Resenia;

@Remote
public interface UsuarioEJBRemote {

	String JNDI = "java:global/ear-huq/Negocio/UsuarioEJB!co.alfite.sis.ejb.UsuarioEJBRemote";

	/**
	 * @param resenia
	 * @return
	 */
	Resenia insertarResenia(Resenia resenia);

	/**
	 * 
	 * @param meGusta
	 * @return
	 */
	MeGustaEspeciePlanta insertarMegusta(MeGustaEspeciePlanta meGusta);

	List<ImagenPlanta> obtenerListaImagenes();

	List<Resenia> obtenerListaResenias(String id);

	List<MeGustaEspeciePlanta> obtenerListaMeGusta(String id);

	List<ImagenPlanta> obtenerListaImagenesOrdenadasPorLikes();

	List<Resenia> listaReseniasPorImagen(Integer id);

	List<MeGustaEspeciePlanta> obtenerListaLikesPorEspecie(String id);
	
	MeGustaEspeciePlanta eliminarMegusta(MeGustaEspeciePlanta meGusta);
}

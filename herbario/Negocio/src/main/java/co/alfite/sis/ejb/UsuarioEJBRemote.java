package co.alfite.sis.ejb;

import javax.ejb.Remote;

import co.alfite.sis.entidades.MeGustaEspeciePlanta;
import co.alfite.sis.entidades.Resenia;

@Remote
public interface UsuarioEJBRemote {

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
}

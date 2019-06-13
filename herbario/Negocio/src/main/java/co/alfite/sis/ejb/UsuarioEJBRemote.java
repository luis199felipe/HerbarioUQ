package co.alfite.sis.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.alfite.sis.entidades.MeGustaEspeciePlanta;
import co.alfite.sis.entidades.Resenia;

@Remote
public interface UsuarioEJBRemote {

	Resenia insertarResenia(Resenia resenia);

	MeGustaEspeciePlanta insertarMegusta(MeGustaEspeciePlanta meGusta);
	
	boolean eliminarResenia(Resenia resenia);

	boolean eliminarMeGusta(MeGustaEspeciePlanta meGusta);
	
	Resenia ActualizarResenia(Resenia r);

	MeGustaEspeciePlanta ActualizarMeGusta(MeGustaEspeciePlanta m);
	
	List<MeGustaEspeciePlanta> listarMisMeGustas(String idPersona);
	
	List<Resenia> listarMisResenias(String idPersona);	

	List<MeGustaEspeciePlanta> listarMeGustasDeUnaEspecie(String nombreCientifico);

	List<Resenia> listarReseniasDeUnaEspecie(String nombreCientifico);
}

package co.alfite.sis.ejb;

import javax.ejb.Remote;

import co.alfite.sis.entidades.Administrador;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.entidades.ImagenPlanta;
import co.alfite.sis.entidades.MeGustaEspeciePlanta;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.Resenia;
import co.alfite.sis.entidades.Usuario;

@Remote
public interface ActualizarEJBRemote {

	String JNDI =  "java:global/ear-huq/Negocio/ActualizarEJB!co.alfite.sis.ejb.ActualizarEJBRemote";

	Empleado ActualizarEmpleado(Empleado empleado);

	Recolector ActualizarRecolector(Recolector recolector);

	Object ActualizarAdministrador(Administrador administrador);

	Usuario ActualizarUsuario(Usuario us);

	ImagenPlanta ActualizarImagenPlanta(ImagenPlanta i);

	FamiliaPlanta ActualizarFamiliaPlanta(FamiliaPlanta f);

	GeneroPlanta ActualizarGeneroPlanta(GeneroPlanta g);

	EspeciePlanta ActualizarEspeciePlanta(EspeciePlanta esp);

	RegistroEspecie ActualizarRegistroEspecie(RegistroEspecie registro);

	Resenia ActualizarResenia(Resenia r);

	MeGustaEspeciePlanta ActualizarMeGustaEspeciePlanta(MeGustaEspeciePlanta m);

}

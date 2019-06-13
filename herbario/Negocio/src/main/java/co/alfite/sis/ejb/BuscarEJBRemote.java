package co.alfite.sis.ejb;

import java.util.List;

import javax.ejb.Remote;

import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.RegistroEspecie.Estado;

@Remote
public interface BuscarEJBRemote {

	String JNDI = "java:global/ear-huq/Negocio/BuscarEJB!co.alfite.sis.ejb.BuscarEJBRemote";

	Persona personaPorCredenciales(String correo, String password);

	List<EspeciePlanta> especiesPorEstado(Estado est);

	List<EspeciePlanta> especiesPorFamilia(String nombreFamilia);

	Persona personaPorCorreo(String correo);
}

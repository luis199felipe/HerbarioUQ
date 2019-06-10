package co.alfite.sis.modelo;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import co.alfite.sis.ejb.InsertarEJBRemote;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.ImagenPlanta;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.Usuario;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;

public class InsertarDelegado {

	/**
	 * instancia que representa el ejb remoto de administrador
	 */
	private InsertarEJBRemote insertarEJB;
	/**
	 * permite manejar una unica instancia para le manejo de delegados
	 */
	public static InsertarDelegado insertarDelegado = instancia();

	/**
	 * constructor para conectar con la capa de negocio
	 */
	private InsertarDelegado() {
		try {
			insertarEJB = (InsertarEJBRemote) new InitialContext().lookup(InsertarEJBRemote.JNDI);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Permite devolver una unica instancia de delegado
	 * 
	 * @return instancia unica del delegado
	 */
	private static InsertarDelegado instancia() {

		if (insertarDelegado == null) {
			insertarDelegado = new InsertarDelegado();
			return insertarDelegado;
		}
		return insertarDelegado;
	}

	/**
	 * pemite registar un nuevo empleado
	 * 
	 * @param empleado empleado a agregar
	 * @return devuelve true si el empleado fue eliminado
	 */

	public boolean insertarEmpleado(Empleado empleado) throws ElementoRepetidoExcepcion {

		return insertarEJB.insertarEmpleado(empleado) != null;

	}

	public boolean insertarUsusario(Usuario nuevoUsuario) throws ElementoRepetidoExcepcion {
		return insertarEJB.insertarUsuario(nuevoUsuario) != null;
	}

	public boolean insertarRecolector(Recolector recolector) throws ElementoRepetidoExcepcion {
		return insertarEJB.insertarRecolector(recolector) != null;

	}

	public boolean insertarRegistro(RegistroEspecie registro) {
		// TODO Auto-generated method stub
		return insertarEJB.insertarRegistroEspecie(registro) != null;
	}

	public boolean insertarImagenPlanta(ImagenPlanta y) {
		return insertarEJB.insertarImagenPlanta(y) != null;	
		}
	
	public  ImagenPlanta obtenerImagen(int id) {
		
		return insertarEJB.obtenerImagen(id);
	}

}

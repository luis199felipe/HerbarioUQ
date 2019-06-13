package co.alfite.sis.ejb;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;

import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.Usuario;

public class EliminarEJB implements EliminarEJBRemote {
	/**
	 * Default constructor.
	 */

	@PersistenceContext
	EntityManager entityManager;

	public EliminarEJB() {
		// TODO Auto-generated constructor stub
	}

	public boolean eliminarEspecie(EspeciePlanta esp) {

		EspeciePlanta e = entityManager.find(EspeciePlanta.class, esp.getIdEspecie());
		if (e!= null) {
			try {
				entityManager.remove(e);
				return true;
			} catch (Exception e1) {
				e1.printStackTrace();
				return false;
			}
		} else {

			return false;
		}

	}
	
	public boolean eliminarGenero(GeneroPlanta gen) {

		EspeciePlanta e = entityManager.find(EspeciePlanta.class, gen.getIdGenero());
		if (e != null) {
			try {
				entityManager.remove(e);
				return true;
			} catch (Exception e1) {
				e1.printStackTrace();
				return false;
			}
		} else {

			return false;
		}

	}
	public boolean eliminarFamilia(FamiliaPlanta fam) {

		EspeciePlanta e = entityManager.find(EspeciePlanta.class, fam.getIdFamilia());
		if (e != null) {
			try {
				entityManager.remove(e);
				return true;
			} catch (Exception e1) {
				e1.printStackTrace();
				return false;
			}
		} else {

			return false;
		}

	}


	public boolean eliminarRegistro(RegistroEspecie registro) {

		if (entityManager.find(RegistroEspecie.class, registro.getIdRegistro()) != null) {
			try {
				entityManager.remove(registro);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		} else {

			return false;
		}

	}

	public boolean eliminarEmpleado(Empleado empleado) {

		if (entityManager.find(Empleado.class, empleado.getIdPersona()) != null) {
			try {
				entityManager.remove(empleado);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		} else {
			return false;
		}
	}

	public boolean eliminarRecolector(Recolector recolector) {

		if (entityManager.find(Recolector.class, recolector.getIdPersona()) != null) {
			try {
				entityManager.remove(recolector);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		} else {
			return false;
		}
	}

	public boolean inactivarRecolector(Recolector recolector) {

		Recolector r = entityManager.find(Recolector.class, recolector.getIdPersona());
		if (r != null) {
			try {
				r.setEstado(co.alfite.sis.entidades.Persona.Estado.inactivo);
				entityManager.merge(r);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		} else {
			return false;
		}
	}

	public boolean inactivarEmpleado(Empleado empleado) {

		Empleado e = entityManager.find(Empleado.class, empleado.getIdPersona());
		if (e != null) {
			try {
				e.setEstado(co.alfite.sis.entidades.Persona.Estado.inactivo);
				entityManager.merge(e);
				return true;
			} catch (Exception e1) {
				e1.printStackTrace();
				return false;
			}

		} else {
			return false;
		}
	}

	public boolean inactivarUsuario(Usuario usuario) {

		Usuario u = entityManager.find(Usuario.class, usuario.getIdPersona());
		if (u != null) {
			try {
				u.setEstado(co.alfite.sis.entidades.Persona.Estado.inactivo);
				entityManager.merge(u);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}

		} else {
			return false;
		}
	}
}

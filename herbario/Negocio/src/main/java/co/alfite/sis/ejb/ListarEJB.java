package co.alfite.sis.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.alfite.sis.entidades.Administrador;
import co.alfite.sis.entidades.Empleado;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.entidades.MeGustaEspeciePlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.RegistroEspecie.Estado;
import co.alfite.sis.entidades.Resenia;
import co.alfite.sis.entidades.Usuario;

/**
 * Session Bean implementation class ListarBean
 */
@Stateless
@LocalBean
public class ListarEJB implements ListarEJBRemote {

	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ListarEJB() {
		// TODO Auto-generated constructor stub
	}

	public List<Persona> listarPersonas() {
		TypedQuery<Persona> query = entityManager.createNamedQuery(Persona.LISTAR_TODOS, Persona.class);
		return query.getResultList();
	}

	public List<Usuario> listarUsuarios() {
		TypedQuery<Usuario> query = entityManager.createNamedQuery(Usuario.USUARIO_GET_ALL, Usuario.class);
		return query.getResultList();
	}

	public List<Administrador> listarAdministradores() {
		TypedQuery<Administrador> query = entityManager.createNamedQuery(Administrador.ADMINISTRADOR_GET_ALL,
				Administrador.class);
		return query.getResultList();
	}

	public List<Empleado> listarEmpleados() {
		TypedQuery<Empleado> query = entityManager.createNamedQuery(Empleado.EMPLEADO_GET_ALL, Empleado.class);
		return query.getResultList();
	}

	public List<Recolector> listarRecolectores() {

		TypedQuery<Recolector> query = entityManager.createNamedQuery(Recolector.RECOLECTOR_GET_ALL, Recolector.class);
		return query.getResultList();
	}

	public List<RegistroEspecie> listarRegistros() {

		TypedQuery<RegistroEspecie> query = entityManager.createNamedQuery(RegistroEspecie.REGISTRO_GET_ALL,
				RegistroEspecie.class);
		return query.getResultList();
	}

	public List<FamiliaPlanta> listarFamilias() {

		TypedQuery<FamiliaPlanta> query = entityManager.createNamedQuery(FamiliaPlanta.FAMILIA_GET_ALL,
				FamiliaPlanta.class);
		return query.getResultList();
	}

	public List<GeneroPlanta> listarGeneros() {

		TypedQuery<GeneroPlanta> query = entityManager.createNamedQuery(GeneroPlanta.GENERO_GET_ALL,
				GeneroPlanta.class);
		return query.getResultList();
	}

	public List<EspeciePlanta> listarEspecies() {

		TypedQuery<EspeciePlanta> query = entityManager.createNamedQuery(EspeciePlanta.ESPECIE_GET_ALL,
				EspeciePlanta.class);
		return query.getResultList();
	}

	public List<EspeciePlanta> listarEspeciesPorFamilia(String nombre) {

		TypedQuery<EspeciePlanta> query = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_FAMILIA_NOMBRE,
				EspeciePlanta.class);
		query.setParameter("fam", nombre);
		return query.getResultList();
	}

	public List<EspeciePlanta> listarEspeciesPorGenero(String nombre) {

		TypedQuery<EspeciePlanta> query = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_GENERO_NOMBRE,
				EspeciePlanta.class);
		query.setParameter("gen", nombre);
		return query.getResultList();
	}

	public List<EspeciePlanta> listarEspeciesAceptadas() {
		Estado estado = Estado.aprobado;
		TypedQuery<EspeciePlanta> query = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_ESTADO,
				EspeciePlanta.class);
		query.setParameter("est", estado);
		return query.getResultList();
	}

	public List<EspeciePlanta> listarEspeciesRechazadas() {
		Estado estado = Estado.rechazado;
		TypedQuery<EspeciePlanta> query = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_ESTADO,
				EspeciePlanta.class);
		query.setParameter("est", estado);
		return query.getResultList();
	}

	public List<RegistroEspecie> listarRegistrosAceptadasDeUnTrabajador(String idPersona) {
		Estado estado = Estado.aprobado;
		TypedQuery<RegistroEspecie> query = entityManager.createNamedQuery(RegistroEspecie.REGISTRO_ESTADO_TRABAJADOR,
				RegistroEspecie.class);
		query.setParameter("per", idPersona);
		query.setParameter("est", estado);
		return query.getResultList();
	}

	public List<RegistroEspecie> listarRegistrosRechazadasDeUnTrabajador(String idPersona) {
		Estado estado = Estado.rechazado;
		TypedQuery<RegistroEspecie> query = entityManager.createNamedQuery(RegistroEspecie.REGISTRO_ESTADO_TRABAJADOR,
				RegistroEspecie.class);
		query.setParameter("per", idPersona);
		query.setParameter("est", estado);
		return query.getResultList();
	}

	public List<MeGustaEspeciePlanta> listarMeGustas() {

		TypedQuery<MeGustaEspeciePlanta> query = entityManager
				.createNamedQuery(MeGustaEspeciePlanta.MEGUSTAESPECIE_GET_ALL, MeGustaEspeciePlanta.class);
		return query.getResultList();
	}

	public List<MeGustaEspeciePlanta> listarMeGustasDeUnUsuario(String idPersona) {
		TypedQuery<MeGustaEspeciePlanta> query = entityManager
				.createNamedQuery(MeGustaEspeciePlanta.MEGUSTAESPECIE_USUARIO, MeGustaEspeciePlanta.class);
		query.setParameter("per", idPersona);
		return query.getResultList();
	}

	public List<MeGustaEspeciePlanta> listarMeGustasDeUnaEspecie(String nombreCientifico) {
		TypedQuery<MeGustaEspeciePlanta> query = entityManager
				.createNamedQuery(MeGustaEspeciePlanta.MEGUSTAESPECIE_ESPECIE_NOMBRECIENTIFICO, MeGustaEspeciePlanta.class);
		query.setParameter("nom", nombreCientifico);
		return query.getResultList();
	}

	public List<Resenia> listarResenias() {

		TypedQuery<Resenia> query = entityManager.createNamedQuery(Resenia.RESENIA_GET_ALL, Resenia.class);
		return query.getResultList();
	}

	public List<Resenia> listarReseniasDeUnUsuario(String idPersona) {

		TypedQuery<Resenia> query = entityManager.createNamedQuery(Resenia.RESENIA_USUARIO, Resenia.class);
		query.setParameter("per", idPersona);
		return query.getResultList();
	}

	public List<Resenia> listarReseniasDeUnaEspecie(String nombreCientifico) {

		TypedQuery<Resenia> query = entityManager.createNamedQuery(Resenia.RESENIA_ESPECIE_NOMBRECIENTIFICO, Resenia.class);
		query.setParameter("nom", nombreCientifico);
		return query.getResultList();
	}

}

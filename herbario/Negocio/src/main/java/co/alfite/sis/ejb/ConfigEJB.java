package co.alfite.sis.ejb;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import co.alfite.sis.entidades.Administrador;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;

/**
 * Se encarga de verificar la configuaracion por defecto
 */
@Singleton
@LocalBean
@Startup
public class ConfigEJB {

	@PersistenceContext
	private EntityManager entityManager;

	private FamiliaPlanta f;

	/**
	 * Default constructor.
	 */
	public ConfigEJB() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * inicializa la informacion basica
	 */
	@PostConstruct
	private void inicializar() {
		TypedQuery<Long> query = entityManager.createNamedQuery(Administrador.ADMINISTRADOR_GET_NUMBER, Long.class);
		Long numAdmins = query.getSingleResult();
		if (numAdmins == 0) {
			Administrador administrador = new Administrador();
			administrador.setNombre("neyder");
			administrador.setIdPersona("1005095547");
			administrador.setEmail("nfigueroas@uqvirtual.edu.co");
			administrador.setPassword("root");
			administrador.setFechaNacimiento(new Date());
			administrador.setTelefono("321221321");
			entityManager.persist(administrador);
		}

		TypedQuery<Long> q = entityManager.createNamedQuery(FamiliaPlanta.FAMILIA_GET_NUMBER, Long.class);
		Long numFamilias = q.getSingleResult();
		if (numFamilias == 0) {
			f = new FamiliaPlanta();
			f.setNombre("fam1");
			entityManager.persist(f);

		}

		TypedQuery<Long> q1 = entityManager.createNamedQuery(GeneroPlanta.GENERO_GET_NUMBER, Long.class);
		Long numGeneros = q1.getSingleResult();
		if (numGeneros == 0) {

			GeneroPlanta g = new GeneroPlanta();
			g.setNombre("gen1");
			g.setFamiliaPlanta(f);
			entityManager.persist(g);

		}

	}

}

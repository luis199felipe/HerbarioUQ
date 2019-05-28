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

/**
 * Se encarga de verificar la configuaracion por defecto
 */
@Singleton
@LocalBean
@Startup
public class ConfigEJB {

	@PersistenceContext
	private EntityManager entityManager;
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
		TypedQuery<Long>query=entityManager.createNamedQuery(Administrador.ADMINISTRADOR_GET_NUMBER,Long.class);
		Long numAdmins=query.getSingleResult();
		if(numAdmins==0) {
			Administrador administrador=new Administrador();
			administrador.setNombre("julio");
			administrador.setIdPersona("89000");
			administrador.setEmail("julio@gmail.com");
			administrador.setPassword("julio123");
			administrador.setFechaNacimiento(new Date());
			administrador.setTelefono("7897578");
			entityManager.persist(administrador);
		}
	}

}

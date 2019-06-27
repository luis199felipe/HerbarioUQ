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
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.RegistroEspecie.Estado;
import co.alfite.sis.entidades.Usuario;

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

	private GeneroPlanta g;

	private Administrador administrador;

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
			administrador = new Administrador();
			administrador.setNombre("Neyder Figueroa");
			administrador.setIdPersona("1005095547");
			administrador.setEmail("nfigueroas@uqvirtual.edu.co");
			administrador.setPassword("root");
			administrador.setFechaNacimiento(new Date());
			administrador.setTelefono("321221321");
			entityManager.persist(administrador);
			
			Administrador administrador2 = new Administrador();
			administrador2.setNombre("pipe tejada");
			administrador2.setIdPersona("1094977266");
			administrador2.setEmail("lftejadap@uqvirtual.edu.co");
			administrador2.setPassword("root");
			administrador2.setFechaNacimiento(new Date());
			administrador2.setTelefono("321221321");
			entityManager.persist(administrador2);
			
			Administrador administrador3 = new Administrador();
			administrador3.setNombre("melissa Alvarez");
			administrador3.setIdPersona("1094975613");
			administrador3.setEmail("malvarezc_1@uqvirtual.edu.co");
			administrador3.setPassword("root");
			administrador3.setFechaNacimiento(new Date());
			administrador3.setTelefono("321221321");
			entityManager.persist(administrador3);
		}
		
		TypedQuery<Long> query4 = entityManager.createNamedQuery(Recolector.RECOLECTOR_GET_NUMBER, Long.class);
		Long numRecolectores = query4.getSingleResult();
		if(numRecolectores==0) {
			
			Recolector nuevoRecolector=new Recolector();
			nuevoRecolector.setNombre("juan");
			nuevoRecolector.setEmail("juan@gamil.com");
			nuevoRecolector.setFechaNacimiento(new Date());
			nuevoRecolector.setIdPersona("1234");
			nuevoRecolector.setPassword("1234");
			nuevoRecolector.setEstado(co.alfite.sis.entidades.Persona.Estado.activo);
			nuevoRecolector.setTelefono("45566766");
			entityManager.persist(nuevoRecolector);
		}
		
		TypedQuery<Long> query5 = entityManager.createNamedQuery(Usuario.USUARIO_GET_NUMBER, Long.class);
		Long numUsuarios = query5.getSingleResult();
		if(numUsuarios==0) {
			
			Usuario nuevoUsuario=new Usuario();
			nuevoUsuario.setNombre("User");
			nuevoUsuario.setEmail("user@gmail.com");
			nuevoUsuario.setFechaNacimiento(new Date());
			nuevoUsuario.setIdPersona("1234567");
			nuevoUsuario.setPassword("user");
			nuevoUsuario.setEstado(co.alfite.sis.entidades.Persona.Estado.activo);
			nuevoUsuario.setTelefono("45566766");
			entityManager.persist(nuevoUsuario);
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

			g = new GeneroPlanta();
			g.setNombre("gen1");
			g.setFamiliaPlanta(f);
			entityManager.persist(g);

		}
		
		TypedQuery<Long> q2 = entityManager.createNamedQuery(EspeciePlanta.ESPECIES_GET_NUMBER, Long.class);
		Long numEsp = q2.getSingleResult();
		if (numEsp == 0) {
			
			RegistroEspecie re= new RegistroEspecie();

			EspeciePlanta e = new EspeciePlanta();
			e.setGeneroPlanta(g);
			e.setNombre("esp1");
			g.setFamiliaPlanta(f);
			
			re.setEspecie(e);
			re.setFecha(new Date());
			re.setEstado(Estado.aprobado);
			re.setMensaje("muy buen aporte");			
			re.setTrabajador(administrador);
			
			e.setRegistro(re);

			entityManager.persist(e);
			entityManager.persist(re);

		}


	}

}

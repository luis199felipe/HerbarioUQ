package co.alfite.sis;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.annotation.FacesConfig;
import javax.faces.annotation.ManagedProperty;
import javax.faces.annotation.FacesConfig.Version;
import javax.inject.Inject;
import javax.inject.Named;

import co.alfite.sis.ejb.AdministradorEJB;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.entidades.Persona;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.RegistroEspecie.Estado;

@FacesConfig(version = Version.JSF_2_3)
@Named("especieBean")
@ApplicationScoped
public class EspecieBean {

	private List<EspeciePlanta> especies;

	private List<EspeciePlanta> especiesAceptadas;
	private List<RegistroEspecie> especiesRechazadas;
	private List<RegistroEspecie> registrosAceptadosDeRecolector;

	private List<RegistroEspecie> registrosAceptadosDeRecolectorF;
	private List<RegistroEspecie> registrosAceptadosDeRecolectorG;
	
	private List<EspeciePlanta> especiesPorFamilia;
	private List<EspeciePlanta> especiesPorGenero;
	
	
	private List<EspeciePlanta>allEspecies;
	

	private RegistroEspecie reTemp;

	private EspeciePlanta especie;

	@Inject
	@ManagedProperty(value = "#{seguridadBean.persona}")
	private Persona personaEnSesion;

	private FamiliaPlanta familiaTemp;
	private GeneroPlanta generoTemp;

	@EJB
	AdministradorEJB bean;

	@PostConstruct
	public void init() {
		especies = bean.listarEspecies();
		especiesAceptadas = bean.listarEspeciesPorEstado(co.alfite.sis.entidades.RegistroEspecie.Estado.aprobado);
		especiesRechazadas = bean.listarRegsitrosPorEstado(Estado.rechazado);
		
		
		registrosAceptadosDeRecolector = bean.listarRegistrosRecolector(personaEnSesion.getIdPersona());

	}
	

	public void filtrarPorFamilia() {
		 registrosAceptadosDeRecolectorF=bean.listarRegistrosRecolectorAF(personaEnSesion.getIdPersona(),
				 familiaTemp.getIdFamilia()+"");
	}

	public void filtrarPorGenero() {
		registrosAceptadosDeRecolectorG = bean.listarRegistrosRecolectorAG(personaEnSesion.getIdPersona(),
				generoTemp.getIdGenero() + "");

	}
	
	public void filtrarAllPorFamilia() {
		 especiesPorFamilia=bean.listarEspeciesPorFamilia(familiaTemp.getNombre());

	}
	public void filtrarAllPorGenero() {
		
		especiesPorGenero=bean.listarEspeciesPorGenero(generoTemp.getNombre());

	}

	/**
	 * @return the especies
	 */
	public List<EspeciePlanta> getEspecies() {
		return especies;
	}

	/**
	 * @param especies the especies to set
	 */
	public void setEspecies(List<EspeciePlanta> especies) {
		this.especies = especies;
	}

	/**
	 * @return the especie
	 */
	public EspeciePlanta getEspecie() {
		return especie;
	}

	/**
	 * @param especie the especie to set
	 */
	public void setEspecie(EspeciePlanta especie) {
		this.especie = especie;
	}

	public List<EspeciePlanta> getEspeciesAceptadas() {
		return especiesAceptadas;
	}

	public void setEspeciesAceptadas(List<EspeciePlanta> especiesAceptadas) {
		this.especiesAceptadas = especiesAceptadas;
	}

	
	/**
	 * @return the personaEnSesion
	 */
	public Persona getPersonaEnSesion() {
		return personaEnSesion;
	}

	/**
	 * @param personaEnSesion the personaEnSesion to set
	 */
	public void setPersonaEnSesion(Persona personaEnSesion) {
		this.personaEnSesion = personaEnSesion;
	}

	/**
	 * @return the bean
	 */
	public AdministradorEJB getBean() {
		return bean;
	}

	/**
	 * @param bean the bean to set
	 */
	public void setBean(AdministradorEJB bean) {
		this.bean = bean;
	}

	/**
	 * @return the registrosAceptadosDeRecolector
	 */
	public List<RegistroEspecie> getRegistrosAceptadosDeRecolector() {
		return registrosAceptadosDeRecolector;
	}

	/**
	 * @param registrosAceptadosDeRecolector the registrosAceptadosDeRecolector to
	 *                                       set
	 */
	public void setRegistrosAceptadosDeRecolector(List<RegistroEspecie> registrosAceptadosDeRecolector) {
		this.registrosAceptadosDeRecolector = registrosAceptadosDeRecolector;
	}

	/**
	 * @return the registrosAceptadosDeRecolectorG
	 */
	public List<RegistroEspecie> getRegistrosAceptadosDeRecolectorG() {
		return registrosAceptadosDeRecolectorG;
	}

	/**
	 * @param registrosAceptadosDeRecolectorG the registrosAceptadosDeRecolectorG to
	 *                                        set
	 */
	public void setRegistrosAceptadosDeRecolectorG(List<RegistroEspecie> registrosAceptadosDeRecolectorG) {
		this.registrosAceptadosDeRecolectorG = registrosAceptadosDeRecolectorG;
	}

	/**
	 * @return the registrosAceptadosDeRecolectorF
	 */
	public List<RegistroEspecie> getRegistrosAceptadosDeRecolectorF() {
		return registrosAceptadosDeRecolectorF;
	}

	/**
	 * @param registrosAceptadosDeRecolectorF the registrosAceptadosDeRecolectorF to
	 *                                        set
	 */
	public void setRegistrosAceptadosDeRecolectorF(List<RegistroEspecie> registrosAceptadosDeRecolectorF) {
		this.registrosAceptadosDeRecolectorF = registrosAceptadosDeRecolectorF;
	}

	/**
	 * @return the familiaTemp
	 */
	public FamiliaPlanta getFamiliaTemp() {
		return familiaTemp;
	}

	/**
	 * @param familiaTemp the familiaTemp to set
	 */
	public void setFamiliaTemp(FamiliaPlanta familiaTemp) {
		this.familiaTemp = familiaTemp;
	}

	/**
	 * @return the generoTemp
	 */
	public GeneroPlanta getGeneroTemp() {
		return generoTemp;
	}

	/**
	 * @param generoTemp the generoTemp to set
	 */
	public void setGeneroTemp(GeneroPlanta generoTemp) {
		this.generoTemp = generoTemp;
	}


	/**
	 * @return the especiesPorFamilia
	 */
	public List<EspeciePlanta> getEspeciesPorFamilia() {
		return especiesPorFamilia;
	}


	/**
	 * @param especiesPorFamilia the especiesPorFamilia to set
	 */
	public void setEspeciesPorFamilia(List<EspeciePlanta> especiesPorFamilia) {
		this.especiesPorFamilia = especiesPorFamilia;
	}


	/**
	 * @return the especiesPorGenero
	 */
	public List<EspeciePlanta> getEspeciesPorGenero() {
		return especiesPorGenero;
	}


	/**
	 * @param especiesPorGenero the especiesPorGenero to set
	 */
	public void setEspeciesPorGenero(List<EspeciePlanta> especiesPorGenero) {
		this.especiesPorGenero = especiesPorGenero;
	}


	/**
	 * @return the allEspecies
	 */
	public List<EspeciePlanta> getAllEspecies() {
		return allEspecies;
	}


	/**
	 * @param allEspecies the allEspecies to set
	 */
	public void setAllEspecies(List<EspeciePlanta> allEspecies) {
		this.allEspecies = allEspecies;
	}


	/**
	 * @return the especiesRechazadas
	 */
	public List<RegistroEspecie> getEspeciesRechazadas() {
		return especiesRechazadas;
	}


	/**
	 * @param especiesRechazadas the especiesRechazadas to set
	 */
	public void setEspeciesRechazadas(List<RegistroEspecie> especiesRechazadas) {
		this.especiesRechazadas = especiesRechazadas;
	}


	/**
	 * @return the reTemp
	 */
	public RegistroEspecie getReTemp() {
		return reTemp;
	}


	/**
	 * @param reTemp the reTemp to set
	 */
	public void setReTemp(RegistroEspecie reTemp) {
		this.reTemp = reTemp;
	}

}

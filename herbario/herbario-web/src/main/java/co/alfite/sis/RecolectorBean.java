package co.alfite.sis;

import java.util.Date;
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
import co.alfite.sis.entidades.Administrador;
import co.alfite.sis.entidades.EspeciePlanta;
import co.alfite.sis.entidades.FamiliaPlanta;
import co.alfite.sis.entidades.GeneroPlanta;
import co.alfite.sis.entidades.Recolector;
import co.alfite.sis.entidades.RegistroEspecie;
import co.alfite.sis.entidades.RegistroEspecie.Estado;
import co.alfite.sis.excepciones.ElementoRepetidoExcepcion;
import co.alfite.sis.util.Util;

@FacesConfig(version = Version.JSF_2_3)
@Named("recolectorBean")
@ApplicationScoped
public class RecolectorBean {

	@Inject
	@ManagedProperty(value = "#{seguridadBean.persona}")
	private Administrador recolector;

	// duda con el registro, siemre que la instancia cambie en registro bean
	// cambiara aaqui tambien?

	@Inject
	@ManagedProperty(value = "#{RegistroBean.registroEspecie}")
	private RegistroEspecie registroEspecie;

	@EJB
	private AdministradorEJB trabajadorEJB;

	private EspeciePlanta especieDetalle;
	
	private String generoTemp;
	private String famTemp;

	private List<FamiliaPlanta> familias;
	private List<GeneroPlanta> generos;
	private List<RegistroEspecie> misRegistros;
	private List<RegistroEspecie> misRegistrosAceptados;
	private List<RegistroEspecie> misRegistrosAG;
	private List<RegistroEspecie> misRegistrosAF;
	
	private boolean flagListarRegistros;
	
	private boolean flagListarFamilias;

	@PostConstruct
	public void init() {
		familias = trabajadorEJB.listarFamilias();
		generos = trabajadorEJB.listarGeneros();

		misRegistros = trabajadorEJB.listarRegistrosRecolector(recolector.getIdPersona());
		misRegistrosAceptados = trabajadorEJB.listarRegistrosAcetpadosRecolector(recolector.getIdPersona());

		flagListarFamilias=false;
		flagListarRegistros=false;
	}

	public void hola(int a) {

		System.out.println(a);

	}
	
	public void listar() {

		System.out.println("puedo listar");

	}


	public void actualizarDatos() {

	}

	public void realizarNuevoRegistro() {

		registroEspecie.setTrabajador(recolector);
		trabajadorEJB.insertarRegistro(registroEspecie);

		familias = trabajadorEJB.listarFamilias();
		generos = trabajadorEJB.listarGeneros();
		misRegistros = trabajadorEJB.listarRegistrosRecolector(recolector.getIdPersona());
		misRegistrosAceptados = trabajadorEJB.listarRegistrosAcetpadosRecolector(recolector.getIdPersona());

	}

	/**
	 * @return the usuario
	 */
	public Administrador getUsuario() {
		return recolector;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Administrador usuario) {
		this.recolector = usuario;
	}

	/**
	 * @return the recolector
	 */
	public Administrador getRecolector() {
		return recolector;
	}

	/**
	 * @param recolector the recolector to set
	 */
	public void setRecolector(Administrador recolector) {
		this.recolector = recolector;
	}

	/**
	 * @return the registroEspecie
	 */
	public RegistroEspecie getRegistroEspecie() {
		return registroEspecie;
	}

	/**
	 * @param registroEspecie the registroEspecie to set
	 */
	public void setRegistroEspecie(RegistroEspecie registroEspecie) {
		this.registroEspecie = registroEspecie;
	}

	/**
	 * @return the trabajadorEJB
	 */
	public AdministradorEJB getTrabajadorEJB() {
		return trabajadorEJB;
	}

	/**
	 * @param trabajadorEJB the trabajadorEJB to set
	 */
	public void setTrabajadorEJB(AdministradorEJB trabajadorEJB) {
		this.trabajadorEJB = trabajadorEJB;
	}

	/**
	 * @return the especieDetalle
	 */
	public EspeciePlanta getEspecieDetalle() {
		return especieDetalle;
	}

	/**
	 * @param especieDetalle the especieDetalle to set
	 */
	public void setEspecieDetalle(EspeciePlanta especieDetalle) {
		this.especieDetalle = especieDetalle;
	}

	/**
	 * @return the generoTemp
	 */
	public String getGeneroTemp() {
		return generoTemp;
	}

	/**
	 * @param generoTemp the generoTemp to set
	 */
	public void setGeneroTemp(String generoTemp) {
		this.generoTemp = generoTemp;
	}

	/**
	 * @return the famTemp
	 */
	public String getFamTemp() {
		return famTemp;
	}

	/**
	 * @param famTemp the famTemp to set
	 */
	public void setFamTemp(String famTemp) {
		this.famTemp = famTemp;
	}

	/**
	 * @return the familias
	 */
	public List<FamiliaPlanta> getFamilias() {
		return familias;
	}

	/**
	 * @param familias the familias to set
	 */
	public void setFamilias(List<FamiliaPlanta> familias) {
		this.familias = familias;
	}

	/**
	 * @return the generos
	 */
	public List<GeneroPlanta> getGeneros() {
		return generos;
	}

	/**
	 * @param generos the generos to set
	 */
	public void setGeneros(List<GeneroPlanta> generos) {
		this.generos = generos;
	}

	/**
	 * @return the misRegistros
	 */
	public List<RegistroEspecie> getMisRegistros() {
		return misRegistros;
	}

	/**
	 * @param misRegistros the misRegistros to set
	 */
	public void setMisRegistros(List<RegistroEspecie> misRegistros) {
		this.misRegistros = misRegistros;
	}

	/**
	 * @return the misRegistrosAceptados
	 */
	public List<RegistroEspecie> getMisRegistrosAceptados() {
		return misRegistrosAceptados;
	}

	/**
	 * @param misRegistrosAceptados the misRegistrosAceptados to set
	 */
	public void setMisRegistrosAceptados(List<RegistroEspecie> misRegistrosAceptados) {
		this.misRegistrosAceptados = misRegistrosAceptados;
	}

	/**
	 * @return the misRegistrosAG
	 */
	public List<RegistroEspecie> getMisRegistrosAG() {
		return misRegistrosAG;
	}

	/**
	 * @param misRegistrosAG the misRegistrosAG to set
	 */
	public void setMisRegistrosAG(List<RegistroEspecie> misRegistrosAG) {
		this.misRegistrosAG = misRegistrosAG;
	}

	/**
	 * @return the misRegistrosAF
	 */
	public List<RegistroEspecie> getMisRegistrosAF() {
		return misRegistrosAF;
	}

	/**
	 * @param misRegistrosAF the misRegistrosAF to set
	 */
	public void setMisRegistrosAF(List<RegistroEspecie> misRegistrosAF) {
		this.misRegistrosAF = misRegistrosAF;
	}

	/**
	 * @return the flagListarRegistros
	 */
	public boolean isFlagListarRegistros() {
		return flagListarRegistros;
	}

	/**
	 * @param flagListarRegistros the flagListarRegistros to set
	 */
	public void setFlagListarRegistros(boolean flagListarRegistros) {
		this.flagListarRegistros = flagListarRegistros;
	}

	/**
	 * @return the flagListarFamilias
	 */
	public boolean isFlagListarFamilias() {
		return flagListarFamilias;
	}

	/**
	 * @param flagListarFamilias the flagListarFamilias to set
	 */
	public void setFlagListarFamilias(boolean flagListarFamilias) {
		this.flagListarFamilias = flagListarFamilias;
	}

}

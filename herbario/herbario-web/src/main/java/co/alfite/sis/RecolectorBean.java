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
	private Recolector recolector;

	// duda con el registro, siemre que la instancia cambie en registro bean
	// cambiara aaqui tambien?

	@Inject
	@ManagedProperty(value = "#{RegistroBean.registroEspecie}")
	private RegistroEspecie registroEspecie;

	@EJB
	private AdministradorEJB trabajadorEJB;

	private EspeciePlanta especieDetalle;


	private String famTemp;
	private FamiliaPlanta familiaTemp;
	private GeneroPlanta generoTemp;

	private List<FamiliaPlanta> familias;
	private List<GeneroPlanta> generos;
	private List<RegistroEspecie> misRegistros;
	private List<RegistroEspecie> misRegistrosAceptados;
	private List<RegistroEspecie> misRegistrosAG;
	private List<RegistroEspecie> misRegistrosAF;

	private boolean flagListarRegistros;
	private boolean flagSelectorF;
	private boolean flagSelectorG;

	private boolean flagListarFamilias;
	private String tituloTabla;



	private List<RegistroEspecie> misRegistrosAceptadosG;

	private List<RegistroEspecie> misRegistrosAceptadosF;

	@PostConstruct
	public void init() {
		familias = trabajadorEJB.listarFamilias();
		generos = trabajadorEJB.listarGeneros();

		misRegistros = trabajadorEJB.listarRegistrosRecolector(recolector.getIdPersona());
		misRegistrosAceptados = trabajadorEJB.listarRegistrosAcetpadosRecolector(recolector.getIdPersona());

		flagListarFamilias = false;
		flagListarRegistros = false;
		flagSelectorF= false;
		familiaTemp=new FamiliaPlanta();
		familiaTemp.setIdFamilia(-1);
		generoTemp=new GeneroPlanta();
		generoTemp.setIdGenero(-1);

	}

	public void hola(int a) {

		System.out.println(a);

	}

	public String listarRegistros(int tipo) {

		switch (tipo) {
		case 0:
			misRegistros = trabajadorEJB.listarRegistrosRecolector(recolector.getIdPersona());
			tituloTabla="Lista de registros";
			break;
		case 1:
			misRegistros = trabajadorEJB.listarRegistrosAcetpadosRecolector(recolector.getIdPersona());
			tituloTabla="Lista de registros Aceptados";
			break;
		case 2:
			try {
				flagSelectorF= true;
				tituloTabla="Lista de registros Aceptados Por familia";
			misRegistros = trabajadorEJB.listarRegistrosRecolectorAF(recolector.getIdPersona(),""+ familiaTemp.getIdFamilia());
			}catch (Exception e) {
				// TODO: handle exception
			}
			break;
		case 3:
			try {
				tituloTabla="Lista de registros Aceptados Por Genero";
				flagSelectorG= true;
				flagSelectorF=false;
			misRegistros = trabajadorEJB.listarRegistrosRecolectorAG(recolector.getIdPersona(), generoTemp.getIdGenero()+"");
			}catch (Exception e) {
				// TODO: handle exception
			}	
			break;
					

		default:
			break;
		}
		flagListarRegistros = true;
		return "/index?faces-redirect=true";
	}

	

	public void actualizarDatos() {

	}

	public String realizarNuevoRegistro() {

		//registroEspecie.setTrabajador(recolector);
		//trabajadorEJB.insertarRegistro(registroEspecie);

//		familias = trabajadorEJB.listarFamilias();
//		generos = trabajadorEJB.listarGeneros();
//		misRegistros = trabajadorEJB.listarRegistrosRecolector(recolector.getIdPersona());
//		misRegistrosAceptados = trabajadorEJB.listarRegistrosAcetpadosRecolector(recolector.getIdPersona());

		System.err.println("A registro");
		return "/index?faces-redirect=true";
	}

	/**
	 * /**
	 * 
	 * @return the recolector
	 */
	public Recolector getRecolector() {
		return recolector;
	}

	/**
	 * @param recolector the recolector to set
	 */
	public void setRecolector(Recolector recolector) {
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

	public List<RegistroEspecie> getMisRegistrosAceptadosG() {
		return misRegistrosAceptadosG;
	}

	public void setMisRegistrosAceptadosG(List<RegistroEspecie> misRegistrosAceptadosG) {
		this.misRegistrosAceptadosG = misRegistrosAceptadosG;
	}

	public List<RegistroEspecie> getMisRegistrosAceptadosF() {
		return misRegistrosAceptadosF;
	}

	public void setMisRegistrosAceptadosF(List<RegistroEspecie> misRegistrosAceptadosF) {
		this.misRegistrosAceptadosF = misRegistrosAceptadosF;
	}

	public String getTituloTabla() {
		return tituloTabla;
	}

	public void setTituloTabla(String tituloTabla) {
		this.tituloTabla = tituloTabla;
	}

	public FamiliaPlanta getFamiliaTemp() {
		return familiaTemp;
	}

	public void setFamiliaTemp(FamiliaPlanta familiaTemp) {
		this.familiaTemp = familiaTemp;
	}

	public boolean isFlagSelectorF() {
		return flagSelectorF;
	}

	public void setFlagSelectorF(boolean flagSelectorF) {
		this.flagSelectorF = flagSelectorF;
	}

	public boolean isFlagSelectorG() {
		return flagSelectorG;
	}

	public void setFlagSelectorG(boolean flagSelectorG) {
		this.flagSelectorG = flagSelectorG;
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

}

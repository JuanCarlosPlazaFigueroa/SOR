package bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.DaoGestionUsurios;
import vo.VoGestionUsuarios;

@ManagedBean
@SessionScoped
public class BeanGestionProyectos {

	ArrayList<VoGestionUsuarios> listaProyectos;

	private VoGestionUsuarios miPersona;

	private boolean muestraInfo;
	private String Respuesta;

	DaoGestionUsurios personaDao = new DaoGestionUsurios();
	
	ArrayList<VoGestionUsuarios> listaPersonas = new ArrayList<>();
	
	private String NombreProyecto;
	private String Integrantes;
	private String Descripcion;
	private String res;
	private String consulta;

	public BeanGestionProyectos() {
		miPersona = new VoGestionUsuarios();

	}

	public void agregarPersona() {
		res = "";
		System.out.println(NombreProyecto + Integrantes + Descripcion);
		setRes(personaDao.agregarPersona(miPersona));
		miPersona = new VoGestionUsuarios();

	}

	public void consultaProyecto() {

		listaProyectos = new ArrayList<>();
		listaProyectos.clear();

		listaProyectos = personaDao.obetenerListaPersonas();

		System.out.println(miPersona.getConsultaP() + "esto es lo que imprime consulta");

	}
	
	public void  consultaIndividual(){
		
		listaProyectos.clear();
		listaProyectos=personaDao.obtenerConsultaIndividual();
		
	}

	public void editar(VoGestionUsuarios miPersona) {
		miPersona.setEditar(true);

		
	}

	public void guardar(VoGestionUsuarios miPersona) {

		System.out.println("entro a tabla bean " + miPersona.getNombreProyecto() + miPersona.getDescripcion() + miPersona.getIntegrantes() );
		setRes(personaDao.guardar(miPersona));
		miPersona.setEditar(false);

		
	}

	public boolean isMuestraInfo() {
		return muestraInfo;
	}

	public void setMuestraInfo(boolean muestraInfo) {
		this.muestraInfo = muestraInfo;
	}

	public String getRespuesta() {
		return Respuesta;
	}

	public void setRespuesta(String respuesta) {
		Respuesta = respuesta;
	}

	public String getNombreProyecto() {
		return NombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		NombreProyecto = nombreProyecto;
	}

	public String getIntegrantes() {
		return Integrantes;
	}

	public void setIntegrantes(String integrantes) {
		Integrantes = integrantes;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public VoGestionUsuarios getMiPersona() {
		return miPersona;
	}

	public void setMiPersona(VoGestionUsuarios miPersona) {
		this.miPersona = miPersona;
	}

	public String getConsulta() {
		return consulta;
	}

	public String setConsulta(String consulta) {
		return this.consulta = consulta;
	}

	public ArrayList<VoGestionUsuarios> getListaProyectos() {
		return listaProyectos;
	}

	public void setListaProyectos(ArrayList<VoGestionUsuarios> listaProyectos) {
		this.listaProyectos = listaProyectos;
	}

}

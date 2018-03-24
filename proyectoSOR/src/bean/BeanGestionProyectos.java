package bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.DaoGestionProyectos;
import dao.DaoGestionUsurios;
import vo.VoGestionProyectos;
import vo.VoGestionUsuarios;

@ManagedBean
@SessionScoped
public class BeanGestionProyectos {

	ArrayList<VoGestionProyectos> listaProyectos;

	private VoGestionProyectos miPersona;

	private boolean muestraInfo;
	private String Respuesta;

	DaoGestionProyectos personaDao = new DaoGestionProyectos();
	
	ArrayList<VoGestionProyectos> listaPersonas = new ArrayList<>();
	
	private String NombreProyecto;
	private String Integrantes;
	private String Descripcion;
	private String res;
	private String consulta;

	public BeanGestionProyectos() {
		miPersona = new VoGestionProyectos();

	}

	public void agregarPersona() {
		res = "";
		System.out.println(NombreProyecto + Integrantes + Descripcion);
		setRes(personaDao.agregarPersona(miPersona));
		miPersona = new VoGestionProyectos();

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

	public void editar(VoGestionProyectos miPersona) {
		miPersona.setEditar(true);

		
	}

	public void guardar(VoGestionProyectos miPersona) {

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

	public VoGestionProyectos getMiPersona() {
		return miPersona;
	}

	public void setMiPersona(VoGestionProyectos miPersona) {
		this.miPersona = miPersona;
	}

	public String getConsulta() {
		return consulta;
	}

	public String setConsulta(String consulta) {
		return this.consulta = consulta;
	}

	public ArrayList<VoGestionProyectos> getListaProyectos() {
		return listaProyectos;
	}

	public void setListaProyectos(ArrayList<VoGestionProyectos> listaProyectos) {
		this.listaProyectos = listaProyectos;
	}

}

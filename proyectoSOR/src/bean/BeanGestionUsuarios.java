package bean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.DaoGestionUsurios;
import vo.VoGestionUsuarios;

@ManagedBean
@SessionScoped
public class BeanGestionUsuarios {

	int x;
	private VoGestionUsuarios miPersona;
	DaoGestionUsurios personaDao = new DaoGestionUsurios();
	ArrayList<VoGestionUsuarios> listaPersonas = new ArrayList<>();
	ArrayList<VoGestionUsuarios> listaRequisitos = new ArrayList<>();

	private String nombre;
	private String correo;
	private String documento;
	private String res;
	
	
	

	public BeanGestionUsuarios() {
		miPersona = new VoGestionUsuarios();
		consultarLista();
		consultaIndividual();
		GeneralRequisitos();
		contador();
	}

	
	public void contador(){
		
		miPersona=personaDao.contadorProgreso();
		
		if(miPersona.getContar()>=2){
			
			miPersona.setBarra(20);
			
		}
		
	}
	
	
	
	public void agregarPersona() {
		System.out.println(nombre + documento + correo);
		setRes(personaDao.agregarPersona(miPersona));
		miPersona = new VoGestionUsuarios();
	}

	public void agregar() {

		System.out.println(nombre + documento + correo);

	}

	public String ingresar() {
		res = "";

		System.out.println(miPersona.getContrase() + miPersona.getContra());
		miPersona = personaDao.ingresar(miPersona.getContrase(), miPersona.getContra());

		if (miPersona.getUsuario() == null) {

			System.out.println("Usuario Invalido");

		} else {

			if (miPersona.getUsuario().equals("administrador")) {

				System.out.println("Bienvenido Administrador");

				res = "administrador.jsf";

			} else {
				if (miPersona.getUsuario().equals("aprendiz")) {

					System.out.println("Bienvenido Aprendiz");
					res = "Aprendiz.jsf";

				}
			}

		}
		return res;

	}

	public void consultarLista() {

		listaPersonas.clear();
		listaPersonas = personaDao.obtenerListaPersonas();

	}

	public void eliminarPersona(VoGestionUsuarios miPersona) {

		setRes(personaDao.eliminarPersona(miPersona));
		listaPersonas.remove(miPersona);

	}

	public String editarPersona(VoGestionUsuarios miPersona) {

		System.out.println("Entro editar");

		miPersona.setEditar(true);

		return "consultar.jsf";

	}

	public void guardarPersona(VoGestionUsuarios miPersona) {

		setRes(personaDao.editarPersona(miPersona));

		System.out.println("Entro guardar" + miPersona.getNombre() + miPersona.getCorreo() + miPersona.getDocumento()
				+ miPersona.getUsuario() + miPersona.getContrase());

		System.out.println("" + getRes());

		miPersona.setEditar(false);

	}

	public void consultaIndividual() {

		listaPersonas.clear();
		listaPersonas = personaDao.consultaIndiovidual(miPersona.getDocumento());

	}

	public void registroRequisitos() {

		setRes(personaDao.ingresarRequisito(miPersona));
		miPersona = new VoGestionUsuarios();

	}

	public void GeneralRequisitos() {
        listaRequisitos.clear();
		listaRequisitos=personaDao.obtenerListaRequisitos();
		
	}

	public void eliminarRequisitos(VoGestionUsuarios mipersona) {

		setRes(personaDao.eliminarRequisito(miPersona));
		listaRequisitos.remove(miPersona);
	}
	
	public void guardarRequisitos(VoGestionUsuarios miPersona){
		
		setRes(personaDao.guardarRequisitos(miPersona));
		miPersona.setEditar(false);

		
	}

	public ArrayList<VoGestionUsuarios> getListaPersonas() {
		return listaPersonas;
	}

	public void setListaPersonas(ArrayList<VoGestionUsuarios> listaPersonas) {
		this.listaPersonas = listaPersonas;
	}

	public VoGestionUsuarios getMiPersona() {
		return miPersona;
	}

	public void setMiPersona(VoGestionUsuarios miPersona) {
		this.miPersona = miPersona;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public ArrayList<VoGestionUsuarios> getListaRequisitos() {
		return listaRequisitos;
	}

	public void setListaRequisitos(ArrayList<VoGestionUsuarios> listaRequisitos) {
		this.listaRequisitos = listaRequisitos;
	}
}

package bean;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.swing.ImageIcon;

import dao.DaoDiagramaClases;
import vo.VoGestionUsuarios;
import vo.VoDiagramaClases;

@ManagedBean
@SessionScoped
public class BeanDiagramaClases {

	InputStream inputStream = null;
	
	OutputStream outputstream = null;
	VoGestionUsuarios miPersona;
	List<String> miLista;
	VoDiagramaClases miDiagrama;
	private Part llenar;
	private String datosSelect;
	ImageIcon foto;
	
	HttpServletResponse response;



	DaoDiagramaClases daoImagen = new DaoDiagramaClases();

	@SuppressWarnings("unused")
	private String llenarContenedor;

	public BeanDiagramaClases() {

		miPersona = new VoGestionUsuarios();
		miLista = new ArrayList<>();
		miDiagrama = new VoDiagramaClases();

		listaProyectos();

	}

	@SuppressWarnings("resource")
	public void cargarImagen() {

		try {

			llenarContenedor = new Scanner(llenar.getInputStream()).useDelimiter("\\A").next();

			if (llenar != null) {

				inputStream = llenar.getInputStream();

				miDiagrama.setNombre("juan");
				miDiagrama.setNombreProyecto(datosSelect);
				miDiagrama.setImagen(inputStream);
				System.out.println("Muerete "+ datosSelect);

				DaoDiagramaClases imagenDao = new DaoDiagramaClases();
				String res = imagenDao.agregarImagen(miDiagrama);

				System.out.println("*******************************");
				System.out.println("REGISTRA LA IMAGEN");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void listaProyectos() {

		miLista.clear();
		miLista = daoImagen.listadoProyectos();

	}
	
	public void consultaDeImagen(){
		
		miDiagrama.setNombreProyecto(datosSelect);
		System.out.println("hola  "+ datosSelect);

		 response = daoImagen.mostrarImagen(miDiagrama);
		
	}
	
	

	public VoDiagramaClases getMiDiagrama() {
		return miDiagrama;
	}

	public void setMiDiagrama(VoDiagramaClases miDiagrama) {
		this.miDiagrama = miDiagrama;
	}

	public Part getLlenar() {
		return llenar;
	}

	public void setLlenar(Part llenar) {
		this.llenar = llenar;
	}

	public List<String> getMiLista() {
		return miLista;
	}

	public void setMiLista(ArrayList<String> miLista) {
		this.miLista = miLista;
	}

	public String getDatosSelect() {
		return datosSelect;
	}

	public void setDatosSelect(String datosSelect) {
		this.datosSelect = datosSelect;
	}
	public ImageIcon getFoto() {
		return foto;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setFoto(ImageIcon foto) {
		this.foto = foto;
	}

	public VoGestionUsuarios getMiPersona() {
		return miPersona;
	}

	public void setMiPersona(VoGestionUsuarios miPersona) {
		this.miPersona = miPersona;
	}

}

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

import dao.SubirImagenDao;
import vo.Persona;
import vo.PersonaDiagrama;

@ManagedBean
@SessionScoped
public class TablaDiagrama {

	InputStream inputStream = null;
	
	OutputStream outputstream = null;
	Persona miPersona;
	List<String> miLista;
	PersonaDiagrama miDiagrama;
	private Part llenar;
	private String datosSelect;
	ImageIcon foto;
	
	HttpServletResponse response;



	SubirImagenDao daoImagen = new SubirImagenDao();

	@SuppressWarnings("unused")
	private String llenarContenedor;

	public TablaDiagrama() {

		miPersona = new Persona();
		miLista = new ArrayList<>();
		miDiagrama = new PersonaDiagrama();

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

				SubirImagenDao imagenDao = new SubirImagenDao();
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
	
	

	public PersonaDiagrama getMiDiagrama() {
		return miDiagrama;
	}

	public void setMiDiagrama(PersonaDiagrama miDiagrama) {
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

	public Persona getMiPersona() {
		return miPersona;
	}

	public void setMiPersona(Persona miPersona) {
		this.miPersona = miPersona;
	}

}

package vo;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


public class VoDiagramaClases {

	
	private String nombre;
	private InputStream imagen;
	private String nombreProyecto;
	
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public InputStream getImagen() {
		return imagen;
	}
	public void setImagen(InputStream imagen) {
		this.imagen = imagen;
	}
	public String getNombreProyecto() {
		return nombreProyecto;
	}
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}
	
	
	
}

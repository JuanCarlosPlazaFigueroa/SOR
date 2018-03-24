package vo;

import java.io.InputStream;

public class VoCasosUso {

	
	private String casoUso;
	private String descripcionCU;
	private String rol;

	private String documento;
	private String nombre;
	private InputStream imagen;
	

	
	private boolean editar;
	private boolean guardar;
	
	public VoCasosUso(){
		
	}
	public VoCasosUso(String casoUso, String descripcionCU,  String rol, boolean editar, boolean guardar) {
		
		super();
		this.casoUso = casoUso;
		this.descripcionCU = descripcionCU;
		this.rol = rol; 
		this.editar = editar;
		this.guardar = guardar;
	}
	
	
	


	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

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

	
	
	public boolean isEditar() {
		return editar;
	}
	public void setEditar(boolean editar) {
		this.editar = editar;
	}
	public boolean isGuardar() {
		return guardar;
	}
	public void setGuardar(boolean guardar) {
		this.guardar = guardar;
	}
	
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getCasoUso() {
		return casoUso;
	}
	public void setCasoUso(String casoUso) {
		this.casoUso = casoUso;
	}
	public String getDescripcionCU() {
		return descripcionCU;
	}
	public void setDescripcionCU(String descripcionCU) {
		this.descripcionCU = descripcionCU;
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

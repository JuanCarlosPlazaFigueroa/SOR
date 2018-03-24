package vo;

public class VoGestionProyectos {

	private	String  NombreProyecto;
	private String Integrantes;
	private String Descripcion;
	private String consultaP;
	private boolean editar;
	private boolean guardar;
	

	
	public VoGestionProyectos(){
		
	}
	public VoGestionProyectos(String NombreProyecto, String Integrantes,  String Descripcion, String consultaP, boolean editar,boolean guardar) {
		super();
		this.NombreProyecto = NombreProyecto;
		this.Integrantes = Integrantes;
		this.Descripcion = Descripcion; 
		this.consultaP = consultaP; 
		this.editar = editar; 
		this.guardar = guardar; 
	
	}
	

	
	
	public boolean isGuardar() {
		return guardar;
	}
	public void setGuardar(boolean guardar) {
		this.guardar = guardar;
	}
	public boolean isEditar() {
		return editar;
	}
	public void setEditar(boolean editar) {
		this.editar = editar;
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
	public String getConsultaP() {
		return consultaP;
	}
	public void setConsultaP(String consultaP) {
		this.consultaP = consultaP;
	}
	
	
	
}

package bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

import dao.DaoCasosUso;
import vo.VoCasosUso;


@ManagedBean
@ViewScoped
public class BeanCU {

	private int filesUploaded = 0;
	InputStream inputStream = null;

	private Part file;
    private String fileContent;
	
	DaoCasosUso miDao = new DaoCasosUso();
	VoCasosUso micu = new VoCasosUso();

	ArrayList<VoCasosUso> listaProyectos;

	private boolean editar;
	private boolean guardar;
	private String res;
	private VoCasosUso miCu;
	private String casoUso;
	private String descripcionCU;
	private String rol;;

	public BeanCU() {
		miCu = new VoCasosUso();

	}

	public void agregarPersona() {

		setRes(miDao.agregarPersona(miCu));
		miCu = new VoCasosUso();

	}

	public void consultaProyecto() {

		listaProyectos = new ArrayList<>();
		listaProyectos.clear();

		listaProyectos = miDao.obetenerListaPersonas();

		System.out.println(miCu.getCasoUso() + "esto es lo que imprime consulta");

	}
	
	
	
	
	@SuppressWarnings("resource")
	public void upload() {
		
		
        try {
            fileContent = new Scanner(file.getInputStream())
                    .useDelimiter("\\A").next();
            System.out.println(fileContent + " uploaded");
            filesUploaded++;
            
            if (file != null) {
                // prints out some information for debugging
                System.out.println(file.getName());
                System.out.println(file.getSize());
                System.out.println(file.getContentType());
                 
                // obtains input stream of the upload file
                inputStream = file.getInputStream();
                
                VoCasosUso miP=new VoCasosUso();
                miP.setDocumento("123");
                miP.setNombre("Imagen");
                miP.setImagen(inputStream);
                
                DaoCasosUso registra=new DaoCasosUso();
                String resp=registra.agregarimagen(miP);
                System.out.println("*******************************");
                System.out.println("REGISTRA LA IMAGEN");
                System.out.println("*******************************");
                System.out.println("*******************************");
                
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	
	public int getFilesUploaded() {
        return filesUploaded;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }
	public String editar(VoCasosUso miCuVo) {
		miCuVo.setEditar(true);

		return res;

	}

	public String guardar(VoCasosUso miCuVo) {

		setRes(miDao.guardar(miCuVo));
		miCuVo.setEditar(false);

		return res;
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

	public DaoCasosUso getMiDao() {
		return miDao;
	}

	public void setMiDao(DaoCasosUso miDao) {
		this.miDao = miDao;
	}

	public VoCasosUso getMicu() {
		return micu;
	}

	public void setMicu(VoCasosUso micu) {
		this.micu = micu;
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

	public String getRes() {
		return res;
	}

	public void setRes(String res) {
		this.res = res;
	}

	public ArrayList<VoCasosUso> getListaProyectos() {
		return listaProyectos;
	}

	public void setListaProyectos(ArrayList<VoCasosUso> listaProyectos) {
		this.listaProyectos = listaProyectos;
	}

	public VoCasosUso getMiCu() {
		return miCu;
	}

	public void setMiCu(VoCasosUso miCu) {
		this.miCu = miCu;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

}

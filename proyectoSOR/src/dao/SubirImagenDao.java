package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import com.mysql.jdbc.Buffer;

import conexion.Conexion;
import vo.Persona;
import vo.PersonaDiagrama;

public class SubirImagenDao {

	PersonaDiagrama miDiagrama = new PersonaDiagrama();

	public String agregarImagen(PersonaDiagrama miDiagrama) {

		String resultado = "";

		Connection connection = null;
		Conexion conexion = new Conexion();
		PreparedStatement preStatement = null;

		connection = conexion.getConnection();

		String consulta = "INSERT INTO  imagenes (nombre,nombreProyecto,imagen)" + "VALUES (?,?,?)";

		try {
			preStatement = connection.prepareStatement(consulta);
			preStatement.setString(1, miDiagrama.getNombre());
			preStatement.setString(2, miDiagrama.getNombreProyecto());

			

				preStatement.setBinaryStream(3, miDiagrama.getImagen());

			

			int row = preStatement.executeUpdate();
			if (row > 0) {
				resultado = "File uploaded and saved into database";
			}

		} catch (Exception e) {
			System.out.println("No se pudo registra la persona: " + e.getMessage());
			resultado = "No se pudo registrar";
		} finally {
			conexion.desconectar();
		}

		return resultado;

	}

	public ArrayList<String> listadoProyectos() {

		Connection connection = null;
		Conexion miConexion = new Conexion();
		PreparedStatement statement = null;
		ResultSet result = null;

		ArrayList<String> lista = null;
		connection = miConexion.getConnection();

		String consulta = "SELECT rol FROM requisitos";

		try {
			if (connection != null) {

				lista = new ArrayList<>();

				statement = connection.prepareStatement(consulta);

				result = statement.executeQuery();

				lista.add("Name Project");
				while (result.next() == true) {

					lista.add(result.getString("rol"));

				}

			}
		} catch (SQLException e) {
			System.out.println("Error en la consulta del usuario: " + e.getMessage());
		} finally {
			miConexion.desconectar();
		}

		return lista;

	}

	public HttpServletResponse mostrarImagen(PersonaDiagrama miDiagrama) {

		Connection connection = null;
		Conexion miConexion = new Conexion();
		PreparedStatement statement = null;
		ResultSet result = null;

		InputStream inputStream = null;
		ImageIcon ii = null;
		HttpServletResponse response = null;
		byte[] bytes = null;
		
		
		connection = miConexion.getConnection();

		String consulta = "select imagen from imagenes where nombreProyecto= ? ";
		

		try {
			

				statement = connection.prepareStatement(consulta);
       			statement.setString(1, miDiagrama.getNombreProyecto());

				result = statement.executeQuery();

				while (result.next() == true) {

					bytes = result.getBytes("imagen");

				}

				 response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
				response.getOutputStream().write(bytes);
				response.getOutputStream().close();
				
				FacesContext.getCurrentInstance().responseComplete();
				
		} catch (SQLException e) {
			System.out.println("Error en la consulta del usuario: " + e.getMessage());
			System.out.println("hola");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		finally {
			miConexion.desconectar();
		}
		return response;

	}

}
package dao;

import java.sql.*;
import java.util.ArrayList;

import conexion.Conexion;
import vo.VoGestionProyectos;
import vo.VoGestionUsuarios;



public class DaoGestionProyectos {

	VoGestionProyectos miPersona = new VoGestionProyectos();

	public String agregarPersona(VoGestionProyectos miPersona) {

		String resultado = "";

		Connection connection = null;
		Conexion conexion = new Conexion();
		PreparedStatement preStatement = null;

		connection = conexion.getConnection();
		String consulta = "insert into usuarios (NombreProyecto,Integrantes,Descripcion)" + "VALUES(?,?,?)";

		try {

			preStatement = connection.prepareStatement(consulta);
			preStatement.setString(1, miPersona.getNombreProyecto());
			preStatement.setString(2, miPersona.getIntegrantes());
			preStatement.setString(3, miPersona.getDescripcion());
			preStatement.execute();
			System.out.println("Registro exitoso");
			resultado = "se registro";
		} catch (SQLException e) {

			System.out.println("No se pudo Registrar La persopna:" + e.getMessage());
			resultado = "No se pudo registrar";

		} finally {

			conexion.desconectar();
		}
		return resultado;
	
	}

	public String ingresar(String user, String cont) {
		String respu = "";

		Connection connection = null;
		Conexion conexion = new Conexion();
		PreparedStatement preStatement = null;
		ResultSet result = null;

		connection = conexion.getConnection();

		String consulta = "select nombre,documento from registroInstru where nombre=? and documento=?;";

		try {
			if (connection != null) {
				System.out.println("nas" + cont + user);
				preStatement = connection.prepareStatement(consulta);
				preStatement.setString(1, user);
				preStatement.setString(2, cont);

				result = preStatement.executeQuery();

				if (result.next() == true) {
					respu = "ingreso";
				} else {
					respu = " nooooo ingreso";
				}

			}
		} catch (SQLException e) {

			System.out.println("error al consultar el usuario" + e.getMessage());

		}
		return respu;

	}

	public VoGestionProyectos consultaProyecto(String consultaP) {

		Connection connection = null;
		Conexion conexion = new Conexion();
		PreparedStatement statement = null;
		ResultSet result = null;

		VoGestionProyectos miPersona = null;

		connection = conexion.getConnection();

		String consulta = "select * from usuarios where NombreProyecto =?";

		try {
			if (connection != null) {

				System.out.println("Esta es la consulta" + consultaP);
				
				statement = connection.prepareStatement(consulta);
				statement.setString(1, consultaP);
				result = statement.executeQuery();

				if (result.next() == true) {

					miPersona = new VoGestionProyectos();
					

					if (result != null) {
						miPersona = new VoGestionProyectos();

						miPersona.setConsultaP(result.getString("NombreProyecto"));
						miPersona.setConsultaP(result.getString("Integrantes"));
						miPersona.setConsultaP(result.getString("Descripcion"));

					}

				}
			}

		} catch (SQLException e) {

			System.out.println("error al consultar el usuario" + e.getMessage());

		} finally {
			conexion.desconectar();
		}
		return miPersona;
	
	}

	public ArrayList<VoGestionProyectos> obetenerListaPersonas() {
	
		Connection connection=null;
		Conexion  miConexion= new Conexion();
		PreparedStatement statement=null;
		ResultSet result = null;
		
		
		ArrayList<VoGestionProyectos> listaProyectos = null;
		
		connection = miConexion.getConnection();
		
		String Consulta = "select * from usuarios";
		
		
		try {
		   
			
			if (connection!=null) {
				listaProyectos = new ArrayList<>();
				statement = connection.prepareStatement(Consulta);
				
				result = statement.executeQuery();
				
				
				while (result.next() == true) {
					
					miPersona = new VoGestionProyectos();
					miPersona.setNombreProyecto(result.getString("nombreProyecto"));
					miPersona.setIntegrantes(result.getString("integrantes"));
					miPersona.setDescripcion(result.getString("descripcion"));
					listaProyectos.add(miPersona);
				}
				
			}
			
		} catch (Exception e) {
			System.out.println("Errror en la consulta de la tabla usuarios  " + e.getMessage());
		}finally {
			miConexion.desconectar();
		}
		
		
		return listaProyectos;
	}

	public String guardar(VoGestionProyectos miPersona2) {
		
		System.out.println("entro al dao "+ miPersona2.getNombreProyecto());
		
		
		String resultado="";
		Connection connection=null;
		Conexion miConexion = new Conexion();
		connection = miConexion.getConnection();
		
		try {
			
			String consulta = "UPDATE usuarios SET nombreProyecto=?,integrantes=?,descripcion=? where nombreProyecto=? ";
			
			PreparedStatement statement = connection.prepareStatement(consulta);
			
			
			
			statement.setString(1, miPersona2.getNombreProyecto() );
			statement.setString(2, miPersona2.getIntegrantes() );
			statement.setString(3, miPersona2.getDescripcion() );
			statement.setString(4, miPersona2.getNombreProyecto() );
			statement.executeUpdate();
			
			
			System.out.println("entro al dao en guardar");
			resultado="se actualizado con exito ";
			
			miConexion.desconectar();
			
		} catch (Exception e) {
			System.out.println(e);
			 resultado = "no se pudo actualizar";
			
		}
		
		return resultado;
	}
	public ArrayList<VoGestionProyectos> obtenerConsultaIndividual() {
		Connection connection = null;
		Conexion miConexion = new Conexion();
		PreparedStatement statement = null;
		ResultSet result = null;

		ArrayList<VoGestionProyectos> listaProyecto = null;

		connection = miConexion.getConnection();

		String consulta = "SELECT * FROM usuarios where nombreProyecto="+miPersona.getNombreProyecto();

		try {
			if (connection != null) {
				listaProyecto  = new ArrayList<>();
				statement = connection.prepareStatement(consulta);

				result = statement.executeQuery();

				while (result.next() == true) {

					miPersona = new VoGestionProyectos();

					miPersona.setNombreProyecto(result.getString("nombreProyecto"));
					miPersona.setIntegrantes(result.getString("integrantes"));
					miPersona.setDescripcion(result.getString("descripcion"));
					
					listaProyecto.add(miPersona);

				}

			}
		} catch (SQLException e) {
			System.out.println("Error en la consulta del usuario: " + e.getMessage());
		} finally {
			miConexion.desconectar();
		}
		return listaProyecto;
	}
	
	
}
	











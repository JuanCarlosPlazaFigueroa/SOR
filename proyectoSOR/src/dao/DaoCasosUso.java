package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import conexion.Conexion;
import vo.VoCasosUso;


public class DaoCasosUso {
	
	
	VoCasosUso miCuVo = new VoCasosUso();

	public String agregarPersona(VoCasosUso miCuVo) {

		String resultado = "";

		Connection connection = null;
		Conexion conexion = new Conexion();
		PreparedStatement preStatement = null;

		connection = conexion.getConnection();
		String consulta = "insert into casosDeUso (casoUso,descripcionCU,rol)" + "VALUES(?,?,?)";

		try {

			preStatement = connection.prepareStatement(consulta);
			preStatement.setString(1, miCuVo.getCasoUso() );
			preStatement.setString(2, miCuVo.getDescripcionCU() );
			preStatement.setString(3, miCuVo.getRol());
		
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

	/*
	
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



	public Persona consultaProyecto(String consultaP) {

		Connection connection = null;
		Conexion conexion = new Conexion();
		PreparedStatement statement = null;
		ResultSet result = null;

		Persona miPersona = null;

		connection = conexion.getConnection();

		String consulta = "select * from usuarios where NombreProyecto =?";

		try {
			if (connection != null) {

				System.out.println("Esta es la consulta" + consultaP);
				
				statement = connection.prepareStatement(consulta);
				statement.setString(1, consultaP);
				result = statement.executeQuery();

				if (result.next() == true) {

					miPersona = new Persona();
					

					if (result != null) {
						miPersona = new Persona();

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
*/
	public ArrayList<VoCasosUso> obetenerListaPersonas() {
	
		Connection connection=null;
		Conexion  miConexion= new Conexion();
		PreparedStatement statement=null;
		ResultSet result = null;
		
		
		ArrayList<VoCasosUso> listaProyectos = null;
		
		connection = miConexion.getConnection();
		
		String Consulta = "select * from casosDeUso";
		
		
		try {
		   
			
			if (connection!=null) {
				listaProyectos = new ArrayList<>();
				statement = connection.prepareStatement(Consulta);
				
				result = statement.executeQuery();
				
				
				while (result.next() == true) {
					
					VoCasosUso micu = new VoCasosUso();
					micu.setCasoUso(result.getString("casoUso"));
					micu.setDescripcionCU(result.getString("descripcionCU"));
					micu.setRol(result.getString("rol"));
				
					listaProyectos.add(micu);
				}
				
			}
			
		} catch (Exception e) {
			System.out.println("Errror en la consulta de la tabla usuarios  " + e.getMessage());
		}finally {
			miConexion.desconectar();
		}
		
		
		return listaProyectos;
	}

	public String guardar(VoCasosUso miCuVo) {
		
		System.out.println("entro al dao "+ miCuVo.getCasoUso());
		
		
		String resultado="";
		Connection connection=null;
		Conexion miConexion = new Conexion();
		connection = miConexion.getConnection();
		
		try {
			
			String consulta = "UPDATE casosDeUso SET rf =?,nombreCu=?,rol=?, funcionalidad =?,resultado =?,flujoNormal=? where rf=? ";
			
			PreparedStatement statement = connection.prepareStatement(consulta);
			
			
			
			statement.setString(1, miCuVo.getCasoUso() );
			statement.setString(2, miCuVo.getDescripcionCU() );
			statement.setString(3, miCuVo.getRol() );
		
			statement.executeUpdate();
			
			resultado="se actualizado con exito ";
			
			miConexion.desconectar();
			
		} catch (Exception e) {
			System.out.println(e);
			 resultado = "no se pudo actualizar";
			
		}
		
		return resultado;
	}

	public String agregarimagen(VoCasosUso miPersona) {
		String resultado = "";

		Connection connection = null;
		Conexion conexion = new Conexion();
		PreparedStatement preStatement = null;

		connection = conexion.getConnection();
		String consulta = "INSERT INTO persona_imagen (documento,nombre,imagen)"
				+ "  VALUES (?,?,?)";

		try {
			preStatement = connection.prepareStatement(consulta);
			preStatement.setString(1, miPersona.getDocumento());
			preStatement.setString(2, miPersona.getNombre());
			
			if (miPersona.getImagen() != null) {
	                // fetches input stream of the upload file for the blob column
				preStatement.setBlob(3, miPersona.getImagen());
	        }
			
			 int row = preStatement.executeUpdate();
	         if (row > 0) {
	        	 resultado = "File uploaded and saved into database";
	          }


		} catch (SQLException e) {
			System.out.println("No se pudo registra la persona: " + e.getMessage());
			resultado = "No se pudo registrar";
		} finally {
			conexion.desconectar();
		}

		return resultado;
	}	
	
	

}

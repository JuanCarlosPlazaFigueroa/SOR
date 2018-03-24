package dao;

import java.sql.*;
import java.util.ArrayList;

import conexion.Conexion;
import vo.VoGestionUsuarios;

public class DaoGestionUsurios {

	VoGestionUsuarios miPersona = new VoGestionUsuarios();

	public String agregarPersona(VoGestionUsuarios miPersona) {

		String resultado = "";

		Connection connection = null;
		Conexion conexion = new Conexion();
		PreparedStatement preStatement = null;

		connection = conexion.getConnection();
		String consulta = "INSERT INTO usuarios (nombre,documento,correo,contraseña,tipo)" + "VALUES(?,?,?,?,?)";

		try {

			preStatement = connection.prepareStatement(consulta);
			preStatement.setString(1, miPersona.getNombre());
			preStatement.setString(2, miPersona.getDocumento());
			preStatement.setString(3, miPersona.getCorreo());
			preStatement.setString(4, miPersona.getContrase());
			preStatement.setString(5, miPersona.getUsuario());

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

	public VoGestionUsuarios ingresar(String contraseña, String documento) {
		Connection connection = null;
		Conexion conexion = new Conexion();
		PreparedStatement preStatement = null;
		ResultSet result = null;

		connection = conexion.getConnection();

		String consulta = "select * from usuarios where contraseña=? and documento=?; ";

		try {
			if (connection != null) {
				System.out.println("nas" + contraseña + documento);
				preStatement = connection.prepareStatement(consulta);
				preStatement.setString(1, contraseña);
				preStatement.setString(2, documento);

				result = preStatement.executeQuery();

				if (result.next() == true) {

					miPersona.setUsuario(result.getString("tipo"));

				}else{
					
					if(result.next() == false){
					miPersona.setUsuario(null);
					}
				}

			}
		} catch (SQLException e) {

			System.out.println("error al consultar el usuario" + e.getMessage());

		}
		return miPersona;

	}

	public ArrayList<VoGestionUsuarios> obtenerListaPersonas() {
		Connection connection = null;
		Conexion miConexion = new Conexion();
		PreparedStatement statement = null;
		ResultSet result = null;

		ArrayList<VoGestionUsuarios> listaPersonas = null;

		connection = miConexion.getConnection();

		String consulta = "SELECT * FROM usuarios where tipo='administrador'";

		try {
			if (connection != null) {
				listaPersonas = new ArrayList<>();
				statement = connection.prepareStatement(consulta);

				result = statement.executeQuery();

				while (result.next() == true) {

					miPersona = new VoGestionUsuarios();

					miPersona.setNombre(result.getString("nombre"));
					miPersona.setDocumento(result.getString("documento"));
					miPersona.setCorreo(result.getString("correo"));
					miPersona.setContrase(result.getString("contraseña"));
					miPersona.setUsuario(result.getString("tipo"));
					listaPersonas.add(miPersona);

				}

			}
		} catch (SQLException e) {
			System.out.println("Error en la consulta del usuario: " + e.getMessage());
		} finally {
			miConexion.desconectar();
		}
		return listaPersonas;
	}

	public String eliminarPersona(VoGestionUsuarios miPersona) {

		Connection connection = null;
		Conexion miConexion = new Conexion();
		PreparedStatement statement = null;
		connection = miConexion.getConnection();

		String resp = "";

		try {

			String sentencia = "DELETE FROM usuarios WHERE documento=?";

			statement = connection.prepareStatement(sentencia);
			statement.setString(1, miPersona.getDocumento());

			statement.executeUpdate();
			System.out.println("Entro");
			resp = "Se ha eliminado exitosamente";
			statement.close();
			miConexion.desconectar();

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			resp = "No se pudo eliminar";
		}

		return resp;
	}

	public String editarPersona(VoGestionUsuarios miPersona) {
		String resultado = "";
		Connection connection = null;
		Conexion miConexion = new Conexion();
		connection = miConexion.getConnection();
		PreparedStatement statement = null;

		try {

			String consulta = "UPDATE usuarios SET nombre= ?,correo = ? , contraseña= ?, tipo = ? WHERE documento="
					+ miPersona.getDocumento();

			statement = connection.prepareStatement(consulta);

			statement.setString(1, miPersona.getNombre());
			statement.setString(2, miPersona.getCorreo());
			statement.setString(3, miPersona.getContrase());
			statement.setString(4, miPersona.getUsuario());
			statement.executeUpdate();

			resultado = "Se ha Actualizado la persona satisfactoriamente";
			System.out.println("Entro al dao");
			miConexion.desconectar();

		} catch (SQLException e) {
			System.out.println(e);
			resultado = "No se pudo actualizar la persona";
		}
		return resultado;
	}

	public ArrayList<VoGestionUsuarios> consultaIndiovidual(String documento) {

		Connection connection = null;
		Conexion miConexion = new Conexion();
		PreparedStatement statement = null;
		ResultSet result = null;

		ArrayList<VoGestionUsuarios> lista = null;
		VoGestionUsuarios miPersona = null;
		connection = miConexion.getConnection();

		String consulta = "SELECT * FROM usuarios where documento=" + documento;

		try {
			if (connection != null) {

				lista = new ArrayList<>();

				statement = connection.prepareStatement(consulta);

				result = statement.executeQuery();

				if (result.next() == true) {

					miPersona = new VoGestionUsuarios();
					miPersona.setNombre(result.getString("nombre"));
					miPersona.setDocumento(result.getString("documento"));
					miPersona.setCorreo(result.getString("correo"));
					miPersona.setContrase(result.getString("contraseña"));
					miPersona.setUsuario(result.getString("tipo"));
					lista.add(miPersona);

				}

			}
		} catch (SQLException e) {
			System.out.println("Error en la consulta del usuario: " + e.getMessage());
		} finally {
			miConexion.desconectar();
		}
		return lista;
	}

	public String ingresarRequisito(VoGestionUsuarios miPersona) {

		String resultado = "";

		Connection connection = null;
		Conexion conexion = new Conexion();
		PreparedStatement preStatement = null;

		connection = conexion.getConnection();
		String consulta = "INSERT INTO requisitos (idRequisitos,funcionalidad,descripcion,rol,tipo)"
				+ "VALUES(?,?,?,?,?)";

		try {

			preStatement = connection.prepareStatement(consulta);
			preStatement.setString(1, miPersona.getId());
			preStatement.setString(2, miPersona.getFuncionalidad());
			preStatement.setString(3, miPersona.getDescripcion());
			preStatement.setString(4, miPersona.getRol());
			preStatement.setString(5, miPersona.getTipo());

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

	public ArrayList<VoGestionUsuarios> obtenerListaRequisitos() {

		Connection connection = null;
		Conexion miConexion = new Conexion();
		PreparedStatement statement = null;
		ResultSet result = null;

		ArrayList<VoGestionUsuarios> listaRequisitos = null;

		connection = miConexion.getConnection();

		String consulta = "SELECT * FROM requisitos ";

		try {
			if (connection != null) {
				listaRequisitos = new ArrayList<>();
				statement = connection.prepareStatement(consulta);

				result = statement.executeQuery();

				while (result.next() == true) {

					miPersona = new VoGestionUsuarios();
					miPersona.setId(result.getString("idRequisitos"));
					miPersona.setFuncionalidad(result.getString("funcionalidad"));
					miPersona.setDescripcion(result.getString("descripcion"));
					miPersona.setRol(result.getString("rol"));
					miPersona.setTipo(result.getString("tipo"));
					listaRequisitos.add(miPersona);

				}

			}
		} catch (SQLException e) {
			System.out.println("Error en la consulta del usuario: " + e.getMessage());
		} finally {
			miConexion.desconectar();
		}
		return listaRequisitos;

	}

	public String eliminarRequisito(VoGestionUsuarios miPersona) {

		Connection connection = null;
		Conexion miConexion = new Conexion();
		PreparedStatement statement = null;
		connection = miConexion.getConnection();

		String resp = "";

		try {

			String sentencia = "DELETE FROM requisitos WHERE idRequisitos=?";

			statement = connection.prepareStatement(sentencia);
			statement.setString(1, miPersona.getId());

			statement.executeUpdate();
			System.out.println("Entro");
			resp = "Se ha eliminado exitosamente";
			statement.close();
			miConexion.desconectar();

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			resp = "No se pudo eliminar";
		}

		return resp;

	}

	public String guardarRequisitos(VoGestionUsuarios miPersona) {
		String resultado = "";
		Connection connection = null;
		Conexion miConexion = new Conexion();
		connection = miConexion.getConnection();
		PreparedStatement statement = null;

		try {

			String consulta = "UPDATE requisitos SET funcionalidad= ?,descripcion = ? , rol= ?, tipo = ? WHERE idRequisitos="
					+ miPersona.getDocumento();

			statement = connection.prepareStatement(consulta);

			statement.setString(1, miPersona.getFuncionalidad());
			statement.setString(2, miPersona.getDescripcion());
			statement.setString(3, miPersona.getRol());
			statement.setString(4, miPersona.getTipo());
			statement.executeUpdate();

			resultado = "Se ha Actualizado la persona satisfactoriamente";
			System.out.println("Entro al dao");
			miConexion.desconectar();

		} catch (SQLException e) {
			System.out.println(e);
			resultado = "No se pudo actualizar la persona";
		}
		return resultado;
	}
	
	
	public VoGestionUsuarios contadorProgreso(){
		
		Connection connection = null;
		Conexion conexion = new Conexion();
		PreparedStatement preStatement = null;
		ResultSet result = null;

		connection = conexion.getConnection();

		String consulta = "select count(idRequisitos) from requisitos";

		try {
			if (connection != null) {
				preStatement = connection.prepareStatement(consulta);

				result = preStatement.executeQuery();

				if (result.next() == true) {

					miPersona.setContar(result.getInt("count(idRequisitos)"));

				}
			}
		} catch (SQLException e) {

			System.out.println("error al consultar el usuario" + e.getMessage());

		}
		return miPersona;

		
	}

}

package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Conexion {
	
	Connection con;

	public Conexion() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventarios", "root", "");
		} catch (Exception e) {
			System.err.println("Error" + e);
		}
	}

	public Connection getConnection() {
		return con;
	}

	public void cerrarConexion() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.err.println("Error al cerrar la conexión: " + e);
			}
		}
	}

	// METODO PARA VERIFICAR SI EL USUARIO SE ENCUENTRA EN LINEA(CONECTADO)
		public void actualizarEnLineaUsuario(String varUsuario, int valorEnLinea) {
			PreparedStatement statement = null;

			try {
				String sql = "UPDATE empleado SET EnLinea = ? WHERE User = ?";
				statement = con.prepareStatement(sql);
				statement.setInt(1, valorEnLinea);
				statement.setString(2, varUsuario);
				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (statement != null) {
						statement.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		//METODO PARA ACTUALIZAR LA HORA DE ULTIMA DE CONEXION
		public boolean actualizarUltimaConexion(String varUsuario) {
			boolean actualizado = false;
			PreparedStatement statement = null;

			try {
				String sql = "UPDATE empleado SET fec_ultimoacceso = CURRENT_DATE WHERE User = ?";

				statement = con.prepareStatement(sql);
				statement.setString(1, varUsuario);
				int filasActualizadas = statement.executeUpdate();

				if (filasActualizadas > 0) {
					actualizado = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (statement != null) {
						statement.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return actualizado;
		}

		public boolean actualizarUltimaHoraConexion(String varUsuario) {
			boolean actualizado = false;
			PreparedStatement statement = null;

			try {

				String sql = "UPDATE empleado SET hora_ultimoacceso = CURRENT_TIME WHERE User = ?";
				statement = con.prepareStatement(sql);
				statement.setString(1, varUsuario);
				int filasActualizadas = statement.executeUpdate();

				if (filasActualizadas > 0) {
					actualizado = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (statement != null) {
						statement.close();

					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			return actualizado;
		}
}

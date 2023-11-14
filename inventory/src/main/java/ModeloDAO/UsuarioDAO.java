package ModeloDAO;
import java.util.ArrayList;
import java.util.List;
import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Interface.CRUD;
import Modelo.Movimientos;
import Modelo.Usuario;

public class UsuarioDAO implements CRUD {
	Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps, prs;
    ResultSet rs;
    @Override
    public Usuario validar(String user, String dni) {
        Usuario u = new Usuario();
        String sql = "select*from empleado where User=? and Dni=?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, dni);
            rs = ps.executeQuery();
            while (rs.next()) {
                u.setId(rs.getInt("IdEmpleado"));
                u.setUser(rs.getString("User"));
                u.setDni(rs.getString("Dni"));
                u.setNom(rs.getString("Nombres"));
                u.setEstado(rs.getString("Estado"));
                u.setPermisos(rs.getString("permisos"));
                u.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return u;
    }
    @Override
	 public List<Usuario> listar() {
        ArrayList<Usuario> list = new ArrayList<>();
        String sql = "select*from empleado";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("IdEmpleado"));
                u.setDni(rs.getString("Dni"));
                u.setNom(rs.getString("Nombres"));
                u.setTel(rs.getString("Telefono"));
                u.setEstado(rs.getString("Estado"));
                u.setPermisos(rs.getString("permisos"));
                u.setUser(rs.getString("User"));
                u.setEmail(rs.getString("email"));
                u.setVarLinea(rs.getString("EnLinea"));
                u.setUltimoAcceso(rs.getString("fec_ultimoacceso"));
                u.setHoraUltimoAcceso(rs.getString("hora_ultimoacceso"));
                list.add(u);
            }

        } catch (Exception e) {
             e.printStackTrace();
        }
        return list;
    }
	@Override
	public boolean add(Usuario u) {
		String sql = "INSERT INTO empleado(Dni, Nombres, Telefono, Estado, permisos, User, email, EnLinea) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	    try {
	        con = cn.getConnection();
	        ps = con.prepareStatement(sql);
	        ps.setString(1, u.getDni());
	        ps.setString(2, u.getNom());
	        ps.setString(3, u.getTel());
	        ps.setString(4, u.getEstado());
	        ps.setString(5, "ACTIVO");
	        ps.setString(6, u.getUser());
	        ps.setString(7, u.getEmail());
	        ps.setInt(8, 0); 
	        ps.executeUpdate();
	        return true; 
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
        return false;
	}
	@Override
	public boolean edit(Usuario u) {
		String sql = "update empleado set Dni='" + u.getDni() + "',Nombres='" + u.getNom() + "',permisos='" + u.getPermisos() + "', Telefono='" + u.getTel() + "', Estado='"+u.getEstado()+"', User='"+u.getUser()+ "' where IdEmpleado=" + u.getId();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}
	@Override
	public boolean eliminar(int id, String tabla) {
		String sql = null;

		if ("empleado".equalsIgnoreCase(tabla)) {
			sql = "DELETE FROM empleado WHERE IdEmpleado = ?";

		} else if ("productos".equalsIgnoreCase(tabla)) {
			sql = "DELETE FROM productos WHERE id_producto = ?";

		} else if ("cliente".equalsIgnoreCase(tabla)) {
			sql = "DELETE FROM cliente WHERE id_cliente = ?";

		} else if ("proveedores".equalsIgnoreCase(tabla)) {
			sql = "DELETE FROM proveedores WHERE id_proveedor = ?";

		} else if ("materiales".equalsIgnoreCase(tabla)) {
			sql = "DELETE FROM materiales WHERE id_material = ?";
		} else if ("inventario_material".equalsIgnoreCase(tabla)) {
			sql = "DELETE FROM inventario_material WHERE id_inventario = ?";

		} else if ("inventario_productos".equalsIgnoreCase(tabla)) {
			sql = "DELETE FROM inventario_productos WHERE id_inventario = ?";

		}
        
        if (sql != null) {
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                int filasAfectadas = ps.executeUpdate();
                return filasAfectadas > 0;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
	}
	
	@Override
	public List<Usuario> buscar(String texto) {
		ArrayList<Usuario> list = new ArrayList<>();

        String sql = "SELECT * FROM empleado WHERE IdEmpleado LIKE '%" + texto + "%' OR Nombres LIKE '%" + texto + "%' OR Dni LIKE '%" + texto + "%' OR User LIKE '%" + texto + "%'";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("IdEmpleado"));
                u.setNom(rs.getString("Nombres"));
                u.setDni(rs.getString("Dni"));
                u.setUser(rs.getString("User"));
                u.setTel(rs.getString("Telefono"));
                u.setEstado(rs.getString("Estado"));
                u.setPermisos(rs.getString("permisos"));
                u.setEmail(rs.getString("email"));
                u.setUltimoAcceso(rs.getString("fec_ultimoacceso"));
                u.setHoraUltimoAcceso(rs.getString("hora_ultimoacceso"));
                list.add(u);
                System.out.println(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
        return list;
	}
	//METODO PARA OBTENER EL ID DEL INVENTARIO

    private int obtenerExistenciaActualDesdeBD(int idInventario) throws SQLException {
        String sqlObtenerExistencia = "SELECT existencia_final FROM inventario_material WHERE id_inventario = ?";
        PreparedStatement psObtenerExistencia = con.prepareStatement(sqlObtenerExistencia);
        psObtenerExistencia.setInt(1, idInventario);
        ResultSet rs = psObtenerExistencia.executeQuery();

        if (rs.next()) {
            return rs.getInt("existencia_final");
        } else {
            return -1;
        }
    }
  //METODO PARA CAMBIAR LA EXISTENCIA(CANTIDAD) DE UN MATERIAL EN EL INVENTARIO
    public boolean editarCantidad(int idInventario, String tipoMovimiento, int cantidadMovimiento) {
        String sqlActualizarExistenciaFinal = "UPDATE inventario_material SET existencia_final = ? WHERE id_inventario = ?";
        String sqlInsertarMovimiento = "INSERT INTO movimientos (id_inventario, tipo_movimiento, cantidad, fecha_movimiento) VALUES (?, ?, ?, NOW())";

        try {
            con = cn.getConnection();

            int existenciaActual = obtenerExistenciaActualDesdeBD(idInventario);
            if (existenciaActual >= 0) {
                System.out.println("Valor de ExistenciaFinal antes de la actualización: " + existenciaActual);
                int nuevaExistenciaFinal = existenciaActual;

                if (tipoMovimiento.equals("Ingreso")) {
                    nuevaExistenciaFinal += cantidadMovimiento;
                } else if (tipoMovimiento.equals("Salida")) {
                    nuevaExistenciaFinal -= cantidadMovimiento;
                } 

                System.out.println("Nuevo valor de ExistenciaFinal: " + nuevaExistenciaFinal);
                ps = con.prepareStatement(sqlActualizarExistenciaFinal);
                ps.setInt(1, nuevaExistenciaFinal);
                ps.setInt(2, idInventario);
                ps.executeUpdate();

                prs = con.prepareStatement(sqlInsertarMovimiento);
                prs.setInt(1, idInventario);
                prs.setString(2, tipoMovimiento);
                prs.setInt(3, cantidadMovimiento);
                prs.executeUpdate();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
  //METODO PARA AGREGAR LAS ENTRADAS Y SALIDAS DEL INVENTARIO DE UN MATERIAL
    @Override
    public boolean add(Movimientos mv) {
        String sql = "INSERT INTO movimientos (id_inventario, tipo_movimiento, cantidad, fecha_movimiento) VALUES (?, ?, ?, NOW())";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, mv.getIdInventario());
            ps.setString(2, mv.getTipoMovimiento());
            ps.setInt(3, mv.getCantidad());
            int resultado = ps.executeUpdate();
            return resultado == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public Usuario obtenerUsuarioPorId(int id) {
	    Usuario usuario = null;
	    String sql = "SELECT * FROM empleado WHERE IdEmpleado = ?";
	    try {
	        con = cn.getConnection();
	        ps = con.prepareStatement(sql);
	        ps.setInt(1, id);  // Establecemos el ID del usuario que estamos buscando
	        rs = ps.executeQuery();
	        if (rs.next()) {
	            usuario = new Usuario();
	            usuario.setId(rs.getInt("IdEmpleado"));
	            usuario.setDni(rs.getString("Dni"));
	            usuario.setNom(rs.getString("Nombres"));
	            usuario.setTel(rs.getString("Telefono"));
	            usuario.setEstado(rs.getString("Estado"));
	            usuario.setUser(rs.getString("User"));
	            usuario.setEmail(rs.getString("email"));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        
	    }
	    return usuario;
	}
	

}

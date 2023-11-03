package ModeloDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Conexion.Conexion;
import Interface.CRUDExistencias;
import Modelo.Existencia;
import Modelo.ProveedorInfo;

public class ExistenciasDAO implements CRUDExistencias {
	Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    

	@Override
	public List existencia() {
		 ArrayList<Existencia> list = new ArrayList<>();
		 String sql = "SELECT i.id_inventario, i.existencia_inicial, i.existencia_final, i.precio_compra, i.fecha_entrada, i.id_material, i.id_proveedor, i.IdEmpleado, m.nombre AS nombreMaterial, p.nombre AS nombreProveedor, u.Nombres AS nombreEmpleado "
				    + "FROM inventario_material i "
				    + "JOIN materiales m ON i.id_material = m.id_material "
				    + "JOIN proveedores p ON i.id_proveedor = p.id_proveedor "
				    + "JOIN empleado u ON i.IdEmpleado = u.IdEmpleado "
				    + "ORDER BY i.id_inventario";


	        try {
	            con = cn.getConnection();
	            ps = con.prepareStatement(sql);
	            rs = ps.executeQuery();
	            while (rs.next()) {
	                Existencia inventario = new Existencia();
	                inventario.setIdInventario(rs.getInt("id_inventario"));
	                inventario.setExistenciaInicial(rs.getInt("existencia_inicial"));
	                inventario.setExistenciaFinal(rs.getInt("existencia_final"));
	                inventario.setPrecioCompra(rs.getBigDecimal("precio_compra"));
	                inventario.setFechaEntrada(rs.getDate("fecha_entrada"));
	                inventario.setIdMaterial(rs.getInt("id_material"));
	                inventario.setIdProveedor(rs.getInt("id_proveedor"));
	                inventario.setIdUsuario(rs.getInt("IdEmpleado"));
	                inventario.setNombreMaterial(rs.getString("nombreMaterial"));
	                inventario.setNombreProveedor(rs.getString("nombreProveedor"));
	                inventario.setIngresadoPor(rs.getString("nombreEmpleado"));
	                list.add(inventario);
	            }
	        } catch (Exception e) {
	            System.out.println("Error al recuperar datos de la base de datos: " + e.getMessage());
	            e.printStackTrace();
	        } finally {
	        cn.cerrarConexion();
	        }
	        return list;
	}

	@Override
	public boolean add(Existencia existencia) {
		 String sql = "INSERT INTO inventario_material (existencia_inicial, existencia_final, precio_compra, fecha_entrada, id_material, id_proveedor, IdEmpleado) "
	                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

	        try {
	            con = cn.getConnection();
	            ps = con.prepareStatement(sql);

	            ps.setInt(1, existencia.getExistenciaInicial());
	            ps.setInt(2, existencia.getExistenciaFinal());
	            ps.setBigDecimal(3, existencia.getPrecioCompra());
	            ps.setDate(4, new java.sql.Date(existencia.getFechaEntrada().getTime()));
	            ps.setInt(5, existencia.getIdMaterial()); 
	            ps.setInt(6, existencia.getIdProveedor()); 
	            ps.setInt(7, existencia.getIdUsuario());
	            int resultado = ps.executeUpdate();

	            return resultado == 1;
	        } catch (Exception e) {
	            
	            e.printStackTrace();
	            return false;
	        }
	        
	}

	@Override
	public boolean edit(Existencia ex) {
		String sql = "update inventario_material "
                + "SET existencia_inicial = ?, existencia_final = ?, precio_compra = ?, fecha_entrada = ?, "
                + "id_material = ?, id_proveedor = ?, IdEmpleado = ? "
                + 
                "WHERE id_inventario = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);


            ps.setInt(1, ex.getExistenciaInicial());
            ps.setInt(2, ex.getExistenciaFinal());
            ps.setBigDecimal(3, ex.getPrecioCompra());
            ps.setDate(4, new java.sql.Date(ex.getFechaEntrada().getTime()));
            ps.setInt(5, ex.getIdMaterial()); 
            ps.setInt(6, ex.getIdProveedor()); 
            ps.setInt(7, ex.getIdUsuario());
            ps.setInt(8, ex.getIdInventario()); 
            int resultado = ps.executeUpdate();

            return resultado == 1;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public List buscarE(String texto) {
		List<Existencia> resultados = new ArrayList<>();
        String sql = "SELECT i.id_inventario, i.existencia_inicial, i.existencia_final, i.precio_compra, i.fecha_entrada, m.nombre AS nombreMaterial, p.nombre AS nombreProveedor, u.Nombres AS nombreEmpleado "
                + "FROM inventario_material i "
                + "JOIN materiales m ON i.id_material = m.id_material "
                + "JOIN proveedores p ON i.id_proveedor = p.id_proveedor "
                + "JOIN empleado u ON i.IdEmpleado = u.IdEmpleado "
                + "WHERE m.nombre LIKE ? OR p.nombre LIKE ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);


            ps.setString(1, "%" + texto + "%"); 
            ps.setString(2, "%" + texto + "%"); 

            rs = ps.executeQuery();

            while (rs.next()) {
                Existencia existencia = new Existencia();
                existencia.setIdInventario(rs.getInt("id_inventario"));
                existencia.setExistenciaInicial(rs.getInt("existencia_inicial"));
                existencia.setExistenciaFinal(rs.getInt("existencia_final"));
                existencia.setPrecioCompra(rs.getBigDecimal("precio_compra"));
                existencia.setFechaEntrada(rs.getDate("fecha_entrada"));
                existencia.setIngresadoPor(rs.getString("nombreEmpleado"));
                existencia.setNombreMaterial(rs.getString("nombreMaterial"));
                existencia.setNombreProveedor(rs.getString("nombreProveedor"));
                resultados.add(existencia);
            }
        } catch (Exception e) {
            System.out.println("Error al realizar la búsqueda: " + e.getMessage());
            e.printStackTrace();
        }

        return resultados;
	}

	@Override
	public boolean addx(Existencia ex) {
        String sql = "INSERT INTO inventario_productos (existencia, precio_servicio, fecha_entrada, estado, id_producto, id_cliente, IdEmpleado) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            
            ps.setInt(1, ex.getExistenciaInicial());
            ps.setBigDecimal(2, ex.getPrecioCompra());
            ps.setDate(3, new java.sql.Date(ex.getFechaEntrada().getTime()));
            ps.setString(4, ex.getEstado());
            ps.setInt(5, ex.getIdProducto()); 
            ps.setInt(6, ex.getIdCliente()); 
            ps.setInt(7, ex.getIdUsuario());
            int resultado = ps.executeUpdate();
            
            // Verificar si se insertó correctamente (resultado igual a 1)
            return resultado == 1;
        } catch (Exception e) {
            // Manejo de excepciones
            e.printStackTrace();
            return false;
        }
	}

	@Override
	public List existenciam() {
		ArrayList<Existencia> list = new ArrayList<>();
		String sql = "SELECT i.id_inventario, i.existencia, i.precio_servicio, i.fecha_entrada, i.fecha_salida, i.estado, i.id_producto, i.id_cliente, i.IdEmpleado, p.nombre AS nombreProducto, c.nombre AS nombreCliente, u.Nombres AS nombreEmpleado "
			    + "FROM inventario_productos i "
			    + "JOIN productos p ON i.id_producto = p.id_producto "
			    + "JOIN cliente c ON i.id_cliente = c.id_cliente "
			    + "JOIN empleado u ON i.IdEmpleado = u.IdEmpleado "
			    + "ORDER BY i.id_inventario";


        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Existencia inventario = new Existencia();
                inventario.setIdInventario(rs.getInt("id_inventario"));
                inventario.setExistenciaInicial(rs.getInt("existencia"));
                inventario.setPrecioCompra(rs.getBigDecimal("precio_servicio"));
                inventario.setFechaEntrada(rs.getDate("fecha_entrada"));
                inventario.setFechaSalida(rs.getDate("fecha_salida"));
                inventario.setEstado(rs.getString("estado"));
                inventario.setIdProducto(rs.getInt("id_producto"));
                inventario.setIdCliente(rs.getInt("id_cliente"));
                inventario.setIdUsuario(rs.getInt("IdEmpleado"));
                inventario.setNombreProducto(rs.getString("nombreProducto"));
                inventario.setNombreCliente(rs.getString("nombreCliente"));
                inventario.setIngresadoPor(rs.getString("nombreEmpleado"));
                list.add(inventario);
            }
        } catch (Exception e) {
            System.out.println("Error al recuperar datos de la base de datos: " + e.getMessage());
            e.printStackTrace();
        } finally {
        cn.cerrarConexion();
        }
        return list;
	}

	@Override
	public List buscarM(String texto) {
		List<Existencia> resultados = new ArrayList<>();
        String sql = "select i.id_inventario, i.existencia, i.precio_servicio, i.fecha_entrada, i.fecha_salida, i.estado, p.nombre AS nombreProducto, c.nombre AS nombreCliente, u.Nombres AS nombreEmpleado "
                + "from inventario_productos i "
                + "JOIN productos p ON i.id_producto = p.id_producto "
                + "JOIN cliente c ON i.id_cliente = c.id_cliente "
                + "JOIN empleado u ON i.IdEmpleado = u.IdEmpleado "
                + "WHERE p.nombre LIKE ? OR c.nombre LIKE ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + texto + "%"); 
            ps.setString(2, "%" + texto + "%"); 

            rs = ps.executeQuery();

            while (rs.next()) {
                Existencia inventario = new Existencia();
                inventario.setIdInventario(rs.getInt("id_inventario"));
                inventario.setExistenciaInicial(rs.getInt("existencia"));
                inventario.setPrecioCompra(rs.getBigDecimal("precio_servicio"));
                inventario.setFechaEntrada(rs.getDate("fecha_entrada"));
                inventario.setFechaSalida(rs.getDate("fecha_salida"));
                inventario.setEstado(rs.getString("estado"));
                inventario.setNombreProducto(rs.getString("nombreProducto"));
                inventario.setNombreCliente(rs.getString("nombreCliente"));
                inventario.setIngresadoPor(rs.getString("nombreEmpleado"));
                resultados.add(inventario);
            }
        } catch (Exception e) {
            System.out.println("Error al realizar la búsqueda: " + e.getMessage());
            e.printStackTrace();
        }

        return resultados;
	}

	@Override
	public boolean editP(Existencia ex) {
		String sql;
        if ("Entregado".equals(ex.getEstado())) {

            sql = "update inventario_productos "
                    + "SET existencia = ?, precio_servicio = ?, fecha_entrada = ?, estado= ?, "
                    + "id_producto = ?, id_cliente = ?, IdEmpleado = ?, fecha_salida = CURRENT_TIMESTAMP() "
                    + "where id_inventario = ?";
        } else {

            sql = "update inventario_productos "
                    + "SET existencia = ?, precio_servicio = ?, fecha_entrada = ?, estado= ?, "
                    + "id_producto = ?, id_cliente = ?, IdEmpleado = ? "
                    + "where id_inventario = ?";
        }
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.setInt(1, ex.getExistenciaInicial());
            ps.setBigDecimal(2, ex.getPrecioCompra());
            ps.setDate(3, new java.sql.Date(ex.getFechaEntrada().getTime()));
            ps.setString(4, ex.getEstado());
            ps.setInt(5, ex.getIdProducto()); 
            ps.setInt(6, ex.getIdCliente()); 
            ps.setInt(7, ex.getIdUsuario());
            ps.setInt(8, ex.getIdInventario()); 

            int resultado = ps.executeUpdate();

            return resultado == 1;
        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
	}
	/*public Date obtenerFechaUltimaTransaccionProveedor(int proveedorId) {
		
	    Date fechaUltimaTransaccion = null;
	    String sql = "SELECT MAX(fecha_entrada) FROM inventario_material WHERE id_proveedor = ?";
	    
	    try {
	    	con = cn.getConnection();
            ps = con.prepareStatement(sql);
	        ps.setInt(1, proveedorId);
	        ResultSet rs = ps.executeQuery();

	        if (rs.next()) {
	            fechaUltimaTransaccion = rs.getDate(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Manejar la excepción
	    }
	    
	    return fechaUltimaTransaccion;
	}
	public long obtenerDiferenciaDiasUltimaTransaccionProveedor(int proveedorId) {
	    LocalDate fechaActual = LocalDate.now();
	    
	    Date fechaUltimaTransaccion = obtenerFechaUltimaTransaccionProveedor(proveedorId);
	    
	    if (fechaUltimaTransaccion == null) {
	        // Manejar el caso en que no haya fecha de última transacción
	        return -1; // Puedes devolver un valor especial o manejarlo según tus necesidades.
	    }
	    
	    // Convertir la fecha de última transacción a LocalDate
	    LocalDate fechaUltimaTransaccionLocalDate = fechaUltimaTransaccion.toLocalDate();
	    
	    // Calcular la diferencia de días
	    return ChronoUnit.DAYS.between(fechaUltimaTransaccionLocalDate, fechaActual);
	}*/
	public Date obtenerFechaUltimaTransaccionProveedor() {
	    Date fechaUltimaTransaccion = null;
	    String sql = "SELECT MAX(fecha_entrada) FROM inventario_material GROUP BY id_proveedor";
	    
	    try {
	        con = cn.getConnection();
	        ps = con.prepareStatement(sql);
	        rs = ps.executeQuery();

	        if (rs.next()) {
	            fechaUltimaTransaccion = rs.getDate(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Manejar la excepción
	    }
	    
	    return fechaUltimaTransaccion;
	}

	public Map<Integer, ProveedorInfo> obtenerDiferenciaDiasYNombreProveedores() {
	    LocalDate fechaActual = LocalDate.now();
	    Map<Integer, ProveedorInfo> diferenciaDiasYNombreProveedores = new HashMap<>();
	    
	    String sql = "SELECT p.id_proveedor AS id_proveedor, p.nombre AS nombre_proveedor, MAX(im.fecha_entrada) AS fecha_ultima_transaccion " +
	                 "FROM inventario_material im " +
	                 "INNER JOIN proveedores p ON im.id_proveedor = p.id_proveedor " +
	                 "GROUP BY im.id_proveedor";
	    
	    try {
	        con = cn.getConnection();
	        ps = con.prepareStatement(sql);
	        rs = ps.executeQuery();

	        while (rs.next()) {
	            int idProveedor = rs.getInt("id_proveedor");
	            String nombreProveedor = rs.getString("nombre_proveedor");
	            Date fechaUltimaTransaccion = rs.getDate("fecha_ultima_transaccion");
	            
	            if (fechaUltimaTransaccion != null) {
	                LocalDate fechaUltimaTransaccionLocalDate = fechaUltimaTransaccion.toLocalDate();
	                long diferenciaDias = ChronoUnit.DAYS.between(fechaUltimaTransaccionLocalDate, fechaActual);
	                ProveedorInfo proveedorInfo = new ProveedorInfo(idProveedor, nombreProveedor, diferenciaDias,fechaUltimaTransaccion);
	                diferenciaDiasYNombreProveedores.put(idProveedor, proveedorInfo);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	       
	    }
	    
	    return diferenciaDiasYNombreProveedores;
	}





}

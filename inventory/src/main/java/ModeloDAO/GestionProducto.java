package ModeloDAO;

import java.util.ArrayList;
import java.util.List;
import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Interface.CRUDProducto;
import Modelo.Cliente;
import Modelo.Producto;

public class GestionProducto implements CRUDProducto{
	Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

	public List listarP() {
		ArrayList<Producto> list = new ArrayList<>();
        String sql = "select*from productos";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto pro = new Producto();
                pro.setId(rs.getInt("id_producto"));
                pro.setTipo(rs.getString("tipo_acabado"));
                pro.setNombre(rs.getString("nombre"));
                pro.setModelo(rs.getString("modelo"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setTalla(rs.getString("talla"));
                pro.setGenero(rs.getString("Genero"));
                list.add(pro);
                
                
               // System.out.println("BIEN AGREGADO");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}

	public boolean add(Producto p) {
		String sql = "insert into productos(nombre, tipo_acabado, descripcion, talla, modelo, Genero) values ('" + p.getNombre() + "'"
                + ",'" + p.getTipo() + "','" + p.getDescripcion() + "','" + p.getTalla() + "','" + p.getModelo() +"','" + p.getGenero() +"')";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
           // System.out.println("BIEN AGREGADO");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}

	public boolean edit(Producto pro) {
		String sql = "update productos set nombre='" + pro.getNombre()+ "',tipo_acabado='" + pro.getTipo() + "',descripcion='" +
                pro.getDescripcion() + "',talla='" + pro.getTalla() + "', modelo='" + pro.getModelo() + "', Genero='" + pro.getGenero() +  "' where id_producto=" + pro.getId();
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
	}

	
	public List buscarP(String texto) {
		ArrayList<Producto> list = new ArrayList<>();

        String sql = "SELECT * FROM productos WHERE id_producto LIKE '%" + texto + "%' OR nombre LIKE '%" + 
                texto + "%' OR tipo_acabado LIKE '%" + texto + "%' OR talla LIKE '%" + texto + "%' OR modelo LIKE '%"+texto+"%' OR Genero LIKE '%"+texto+"%'";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Producto pro = new Producto();
                pro.setId(rs.getInt("id_producto"));
                pro.setNombre(rs.getString("nombre"));
                pro.setTipo(rs.getString("tipo_acabado"));
                pro.setDescripcion(rs.getString("descripcion"));
                pro.setTalla(rs.getString("talla"));
                pro.setModelo(rs.getString("modelo"));
                pro.setGenero(rs.getString("Genero"));
                list.add(pro);
                System.out.println(pro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}
	//CONSULTAS PARA EL CLIENTE
	@Override
	public List listarC() {
		ArrayList<Cliente> list = new ArrayList<>();
        String sql = "select*from cliente";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setId(rs.getInt("id_cliente"));
                cli.setNombre(rs.getString("nombre"));
                cli.setCorreo(rs.getString("correo_electronico"));
                cli.setTelefono(rs.getString("telefono"));
                cli.setDireccion(rs.getString("direccion"));
                list.add(cli);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}

	@Override
	public boolean add(Cliente c) {
		String sql = "insert into cliente(nombre, correo_electronico, telefono, direccion) values('" + c.getNombre()
				+ "','" + c.getCorreo() + "','" + c.getTelefono() + "','" + c.getDireccion() + "')";
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
	public boolean edit(Cliente cli) {
		String sql = "update cliente set nombre='" + cli.getNombre() + "',correo_electronico='" + cli.getCorreo() + "', telefono='" + cli.getTelefono() + "', "
                + "direccion='" + cli.getDireccion() + "' where id_cliente=" + cli.getId();
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
	public List buscarC(String texto) {
		ArrayList<Cliente> list = new ArrayList<>();

        String sql = "SELECT * FROM cliente WHERE id_cliente LIKE '%" + texto + "%' OR nombre LIKE '%" + texto + "%' OR correo_electronico LIKE '%" + texto + "%' OR "
                + "direccion LIKE '%" + texto + "%'";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Cliente cli = new Cliente();
                cli.setId(rs.getInt("id_cliente"));
                cli.setNombre(rs.getString("nombre"));
                cli.setCorreo(rs.getString("correo_electronico"));
                cli.setTelefono(rs.getString("telefono"));
                cli.setDireccion(rs.getString("direccion"));
                list.add(cli);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}

}

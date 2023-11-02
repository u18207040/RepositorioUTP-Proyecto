package ModeloDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import Conexion.Conexion;
import Interface.CRUDMaterial;
import Modelo.Material;
import Modelo.Proveedor;

public class GestionMaterial implements CRUDMaterial{
	Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

	@Override
	public List listarm() {
		ArrayList<Material> list = new ArrayList<>();
        String sql = "select*from materiales";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Material mer = new Material();
                mer.setId(rs.getInt("id_material"));
                mer.setCategoria(rs.getString("categoria"));
                mer.setNombre(rs.getString("nombre"));
                mer.setDetalle(rs.getString("detalles"));
                list.add(mer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}

	@Override
	public boolean add(Material m) {
		String sql = "insert into materiales(categoria, nombre, detalles) values ('" + m.getCategoria() + "','" + m.getNombre() + "','" + m.getDetalle() + "')";
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
	public boolean edit(Material mat) {
		String sql = "update materiales set categoria='" + mat.getCategoria() + "',nombre='" + mat.getNombre() + "', detalles='" + mat.getDetalle() + "' where id_material=" + mat.getId();
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
	public List buscarM(String texto) {
		ArrayList<Material> list = new ArrayList<>();

        String sql = "SELECT * FROM materiales WHERE id_material LIKE '%" + texto + "%' OR categoria LIKE '%" + texto + "%' OR nombre LIKE '%" + texto + "%' OR detalles LIKE '%" + texto + "%'";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Material mat = new Material();
                mat.setId(rs.getInt("id_material"));
                mat.setCategoria(rs.getString("categoria"));
                mat.setNombre(rs.getString("nombre"));
                mat.setDetalle(rs.getString("detalles"));
                list.add(mat);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}
	//GESTION DE PROVEEDOR
	@Override
	public List listarp() {
		ArrayList<Proveedor> list = new ArrayList<>();
        String sql = "select*from proveedores";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Proveedor pro = new Proveedor();
                pro.setId(rs.getInt("id_proveedor"));
                pro.setNombre(rs.getString("nombre"));
                pro.setCorreo(rs.getString("correo"));
                pro.setTelefono(rs.getString("telefono"));
                pro.setDireccion(rs.getString("direccion"));
                list.add(pro);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}

	@Override
	public boolean add(Proveedor p) {
		String sql = "insert into proveedores(nombre, correo, telefono, direccion) values('" + p.getNombre() + "','"
				+ p.getCorreo() + "','" + p.getTelefono() + "','" + p.getDireccion() + "')";
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
	public boolean edit(Proveedor pro) {
		String sql = "update proveedores set nombre='" + pro.getNombre() + "',correo='" + pro.getCorreo() + "', telefono='" + pro.getTelefono() + "', "
                + "direccion='" + pro.getDireccion() + "' where id_proveedor=" + pro.getId();
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
	public List buscarP(String texto) {
		ArrayList<Proveedor> list = new ArrayList<>();

        String sql = "SELECT * FROM proveedores WHERE id_proveedor LIKE '%" + texto + "%' OR nombre LIKE '%" + texto + "%' OR correo LIKE '%" + texto + "%' OR "
                + "direccion LIKE '%" + texto + "%'";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()) {
                Proveedor pro = new Proveedor();
                pro.setId(rs.getInt("id_proveedor"));
                pro.setNombre(rs.getString("nombre"));
                pro.setCorreo(rs.getString("correo"));
                pro.setTelefono(rs.getString("telefono"));
                pro.setDireccion(rs.getString("direccion"));
                list.add(pro);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
	}

}

package Controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Modelo.Conexion;
import Modelo.roles;
import Modelo.usuario;
import Vista.usuarios;

public class UsuariosDAO{
	Connection con;
	Conexion cn = new Conexion();
	PreparedStatement ps, ps2;
	ResultSet rs, rs2;
	
	public boolean AgregarUsuario(usuario usu) {
		String consulta = "insert into usuario (nombre, apellido, telefono, direccion, correo_electronico, fecha_nacimiento, id_rol, activo, contrasenia)"
				+ " values(?,?,?,?,?,?,?,?,?)";
		try {
			con=cn.getConexion();
			ps=con.prepareStatement(consulta);
			ps.setString(1, usu.getNombre());
			ps.setString(2, usu.getApellido());
			ps.setInt(3, usu.getTelefono());
			ps.setString(4, usu.getDireccion());
			ps.setString(5, usu.getCorreo_electronico());
			ps.setString(6, usu.getFecha_nacimiento());
			ps.setInt(7, usu.getId_rol());
			ps.setInt(8, usu.getActivo());
			ps.setString(9,usu.getContrasenia());
			ps.execute();
			return true;
		} catch (SQLException e) {
			System.out.println(e.toString());
			return false;
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
	}
	
	public List ListarUsuario() {
		List<usuario> ListarU = new ArrayList();
		String consulta = "select u.usuario_id, u.nombre, u.apellido, r.nombre, u.correo_electronico, u.telefono from usuarios u, roles r where u.id_rol = r.id_rol";
		try {
			con = cn.getConexion();
			ps= con.prepareStatement(consulta);
			rs=ps.executeQuery();		
			while(rs.next()) {
				usuario us = new usuario();
				us.setUsuario_id(rs.getInt("u.usuario_id"));//1
				us.setNombre(rs.getString("u.nombre"));//Abramo
				us.setApellido(rs.getString("u.apellido"));//Perez
				us.setNombre_rol(rs.getString("r.nombre"));//Adminstrador
				us.setCorreo_electronico(rs.getString("u.correo_electronico"));//Correo
				us.setTelefono(rs.getInt("u.telefono"));//telefono
				ListarU.add(us);
			}
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return ListarU;
		
		
	}
	
	public boolean GuardarUsuarios(usuario usu) {
		String consulta = "Insert into usuarios (nombre, apellido, telefono, direccion, correo_electronico, fecha_nacimiento, id_rol, activo, contrasenia) values (?,?,?,?,?,?,?,?,?)";
		try {
			con=cn.getConexion();
			ps = con.prepareStatement(consulta);
			ps.setString(1,usu.getNombre());
			ps.setString(2,usu.getApellido());
			ps.setInt(3, usu.getTelefono());
			ps.setString(4,usu.getDireccion());
			ps.setString(5, usu.getCorreo_electronico());
			ps.setString(6, usu.getFecha_nacimiento());
			ps.setInt(7, usu.getId_rol());
			ps.setInt(8, usu.getActivo());
			ps.setString(9, usu.getContrasenia());
			ps.execute();
			
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}finally {
			try {
				con.close();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
			
		}
		
	}
	
	public boolean ModificarUsuarios(usuario usu) {
		String consulta = "Update usuarios set nombre =?, apellido=?, telefono=?, direccion=?, correo_electronico=?, fecha_nacimiento=?, contrasenia=? where correo_electronico = ?";
		try {
			con=cn.getConexion();
			ps = con.prepareStatement(consulta);
			ps.setString(1, usu.getNombre());
			ps.setString(2, usu.getApellido());
			ps.setInt(3, usu.getTelefono());
			ps.setString(4, usu.getDireccion());
			ps.setString(5, usu.getCorreo_electronico());
			ps.setString(6, usu.getFecha_nacimiento());
			ps.setString(7, usu.getContrasenia());
			ps.setString(8, usu.getCorreo_electronico());
			ps.execute();
			return true;
			
		} catch (SQLException e) {
			System.out.println(e.toString());
			return false;
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println(e.toString());
			}
		}
		
	}

	public boolean EliminarUsuarios(String correo) {
		String consulta = "Delete from usuarios where correo_electronico = ?";
			try {
				con = cn.getConexion();
				ps=con.prepareStatement(consulta);
				ps.setString(1, correo);
				ps.execute();
				
				return true;
			} catch (SQLException e) {
				System.out.println(e.toString());
				return false;
			}finally {
				try {
					con.close();
				} catch (SQLException e) {
					System.out.println(e.toString());
				}
			}
	}
	
}

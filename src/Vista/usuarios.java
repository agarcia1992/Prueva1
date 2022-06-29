package Vista;

import java.awt.Color;
import java.awt.ScrollPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import Controlador.UsuariosDAO;
import Modelo.Conexion;
import Modelo.roles;
import Modelo.usuario;

public class usuarios{
	JFrame jf_usuarios = new JFrame();
	JPanel jp_menu = new JPanel();
	JPanel jp_tabla = new JPanel();
	JLabel lblUsuario = new JLabel();
	JTable tblUsuarios = new JTable();
	JButton btnModificar = new JButton();
	JButton btnEliminar = new JButton();
	JButton btnAgregar = new JButton();
	JButton btnSalir = new JButton();
	usuario usu = new usuario();
	UsuariosDAO usuDao = new UsuariosDAO();
	Object[] ob = new Object [6];
	DefaultTableModel modelo = new DefaultTableModel();
	JTable TablaUsuarios = new JTable();
	JScrollPane js_tabla = new JScrollPane(TablaUsuarios);
	String nombre;
	String apellido;
	
	private void ventanaUsuarios() {
		jf_usuarios.setTitle("Hoteles GT - Usuarios");
		jf_usuarios.setLocationRelativeTo(null);
		jf_usuarios.setBounds(125,250,900,500);
		jf_usuarios.setVisible(true);
		jp_tabla.setLayout(null);
		jp_menu.setLayout(null);
		jp_menu.setBounds(0,0,900,60);
		jp_menu.setBackground(Color.LIGHT_GRAY);
		jp_menu.setBorder(new BevelBorder(BevelBorder.RAISED));
		jp_tabla.setBounds(0, 60, 900, 400);
		jp_tabla.setBackground(Color.WHITE);
		jf_usuarios.add(jp_menu);
		jf_usuarios.add(jp_tabla);
		
	}
	
	private void botones_labels() {
		btnAgregar.setText("Agregar Usuario");
		btnAgregar.setBounds(10,10,150,40);
		jp_menu.add(btnAgregar);
		btnSalir.setText("Salir");
		btnSalir.setBounds(170, 10, 90, 40);
		jp_menu.add(btnSalir);
		lblUsuario.setText("Usuario: "+nombre+" "+apellido);
		lblUsuario.setBounds(675, 70, 150, 40);
		jp_tabla.add(lblUsuario);
		btnModificar.setText("Modificar Usuario");
		btnModificar.setBounds(225,390, 150, 40);
		jp_tabla.add(btnModificar);
		btnEliminar.setText("Eliminar Usuario");
		btnEliminar.setBounds(500, 390, 150, 40);
		jp_tabla.add(btnEliminar);
		
	}
	
	private void tabla() {
		modelo.addColumn("No.");
		modelo.addColumn("Nombre");
		modelo.addColumn("Apellido");
		modelo.addColumn("Rol");
		modelo.addColumn("Modelo");
		modelo.addColumn("Teléfono");
		TablaUsuarios.setModel(modelo);
		js_tabla.setBounds(10, 140, 870, 200);
		jp_tabla.add(js_tabla);
		
	}
	
	private void ListarUsuarios() {
		List<usuario> ListarUsu = usuDao.ListarUsuario();
		for(int i = 0; i< ListarUsu.size();i++) {
			ob[0] = ListarUsu.get(i).getUsuario_id();
			ob[1] = ListarUsu.get(i).getNombre();
			ob[2] = ListarUsu.get(i).getApellido();
			ob[3] = ListarUsu.get(i).getNombre_rol();
			ob[4] = ListarUsu.get(i).getCorreo_electronico();
			ob[5] = ListarUsu.get(i).getTelefono();
			modelo.addRow(ob);
		}
	}

	
	public void ejecutar() {
		
		ventanaUsuarios();
		tabla();
		ListarUsuarios();
		botones_labels();
		
		
	}
	
	public static void main(String[] args) {
		usuarios usu = new usuarios();
		usu.ejecutar();
	}

}



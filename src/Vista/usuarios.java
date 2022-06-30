package Vista;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
	JFrame jf_addUsuario = new JFrame();
	JPanel jp_addUsuario = new JPanel();
	JLabel lblTitulo = new JLabel();
	JLabel lblNombre = new JLabel();
	JLabel lblApellido = new JLabel();
	JLabel lblTelefono = new JLabel();
	JLabel lblDireccion = new JLabel();
	JLabel lblCorreo = new JLabel();
	JLabel lblFecha_nacimiento = new JLabel();
	JLabel lblContraseña = new JLabel();
	JButton btnGuargar = new JButton();
	JButton btnCancelar = new JButton();
	
	JTextField txtNombre = new JTextField();
	JTextField txtApellido = new JTextField();
	JTextField txtTelefono = new JTextField();
	JTextField txtDireccion = new JTextField();
	JTextField txtCorreo = new JTextField();
	JTextField txtContraseña = new JTextField();
	JTextField txtFecha_Nacimiento = new JTextField();
	ButtonGroup bg_Activo = new ButtonGroup();
	UsuariosDAO usuDAO = new UsuariosDAO();
	
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
	Object[] ob = new Object[6];
	DefaultTableModel modelo = new DefaultTableModel();
	JTable TablaUsuarios = new JTable();
	JScrollPane js_tabla = new JScrollPane(TablaUsuarios);
	String nombre;
	String apellido;
	int rol;
	int id_usuario;
	String nombre_bd;
	String apellido_bd;
	int telefono_bd;
	String direccion_bd;
	String correo_bd;
	String fecha_nacimiento_bd;
	String contraseña_bd;
	
	Connection con;
	Conexion cn = new Conexion();
	PreparedStatement ps, ps2;
	ResultSet rs, rs2;

	public void ventanaUsuarios() {
		jf_usuarios.setTitle("Hoteles GT - Usuarios");
		jf_usuarios.setLocationRelativeTo(null);
		jf_usuarios.setBounds(125, 250, 900, 500);
		jf_usuarios.setVisible(true);
		jp_tabla.setLayout(null);
		jp_menu.setLayout(null);
		jp_menu.setBounds(0, 0, 900, 60);
		jp_menu.setBackground(Color.LIGHT_GRAY);
		jp_menu.setBorder(new BevelBorder(BevelBorder.RAISED));
		jp_tabla.setBounds(0, 60, 900, 400);
		jp_tabla.setBackground(Color.WHITE);
		jf_usuarios.add(jp_menu);
		jf_usuarios.add(jp_tabla);

	}

	public void botones_labels() {
		// inicializar botones y labels
		btnAgregar.setText("Agregar Usuario");
		btnAgregar.setBounds(10, 10, 150, 40);
		jp_menu.add(btnAgregar);
		btnSalir.setText("Salir");
		btnSalir.setBounds(170, 10, 90, 40);
		jp_menu.add(btnSalir);
		lblUsuario.setText("Usuario: " + nombre + " " + apellido + " ");
		lblUsuario.setBounds(675, 70, 150, 40);
		jp_tabla.add(lblUsuario);
		btnModificar.setText("Modificar Usuario");
		btnModificar.setBounds(225, 390, 150, 40);
		jp_tabla.add(btnModificar);
		btnEliminar.setText("Eliminar Usuario");
		btnEliminar.setBounds(500, 390, 150, 40);
		jp_tabla.add(btnEliminar);

		// funcionalidades

		ActionListener funcion_Agregar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Agregar();
			}

		};
		btnAgregar.addActionListener(funcion_Agregar);
		
		ActionListener funcion_salir = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jf_usuarios.setVisible(false);
				jf_usuarios.dispose();
			}
			
		};
		btnSalir.addActionListener(funcion_salir);
		
		ActionListener funcion_Modificar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(TablaUsuarios.getSelectedColumn() == -1) {
					JOptionPane.showMessageDialog(null, "Seleccione un elemmento de la tabla");
				}
				else {
					SeleccionTabla();
					SeleccionarCampos();
					Modificar();
					TablaUsuarios.clearSelection();
					
				}
				
			}
		};
		btnModificar.addActionListener(funcion_Modificar);
		
		ActionListener funcion_Eliminar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(TablaUsuarios.getSelectedColumn() == -1) {
					JOptionPane.showMessageDialog(null, "Seleccione un elemmento de la tabla");
				}
				else {
					SeleccionTabla();
					SeleccionarCampos();
					Eliminar();
					TablaUsuarios.clearSelection();
				
			}
		}
	};
	btnEliminar.addActionListener(funcion_Eliminar);
		
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
		List<usuario> ListarUsu = usuDAO.ListarUsuario();
		for (int i = 0; i < ListarUsu.size(); i++) {
			ob[0] = ListarUsu.get(i).getUsuario_id();
			ob[1] = ListarUsu.get(i).getNombre();
			ob[2] = ListarUsu.get(i).getApellido();
			ob[3] = ListarUsu.get(i).getNombre_rol();
			ob[4] = ListarUsu.get(i).getCorreo_electronico();
			ob[5] = ListarUsu.get(i).getTelefono();
			modelo.addRow(ob);
		}
		TablaUsuarios.setModel(modelo);
	}

	public void LimpiarTabla() {
		for (int i = 0; i < modelo.getRowCount(); i++) {
			modelo.removeRow(i);
			i = i - 1;
		}
	}

	private void Agregar() {
		txtNombre.setText("");
		txtApellido.setText("");
		txtTelefono.setText("");
		txtDireccion.setText("");
		txtCorreo.setText("");
		txtFecha_Nacimiento.setText("");
		txtContraseña.setText("");
		
		JRadioButton rbActivo = new JRadioButton("Activo", false);
		JRadioButton rbInactivo = new JRadioButton("Inactivo", false);
		
		//Agregando Atributos
		jf_addUsuario.setTitle("Hoteles GT - Administrador");
		jf_addUsuario.setLocationRelativeTo(null);
		jf_addUsuario.setBounds(125, 250, 500, 550);
		jf_addUsuario.setVisible(true);
		jp_addUsuario.setLayout(null);
		jp_addUsuario.setLayout(null);
		jp_addUsuario.setBounds(0, 0, 500, 550);
		jp_addUsuario.setBackground(Color.LIGHT_GRAY);
		jf_addUsuario.add(jp_addUsuario);

		lblTitulo.setText("Agregar Usuario");
		lblTitulo.setFont(new Font("Serig", Font.PLAIN, 25));
		lblTitulo.setBounds(170, 50, 200, 40);
		jp_addUsuario.add(lblTitulo);

		lblNombre.setText("Nombre:");
		lblNombre.setBounds(50, 100, 50, 25);
		jp_addUsuario.add(lblNombre);

		txtNombre.setBounds(110, 100, 330, 25);
		jp_addUsuario.add(txtNombre);

		lblApellido.setText("Apellido:");
		lblApellido.setBounds(50, 140, 50, 25);
		jp_addUsuario.add(lblApellido);

		txtApellido.setBounds(110, 140, 330, 25);
		jp_addUsuario.add(txtApellido);

		lblTelefono.setText("Teléfono:");
		lblTelefono.setBounds(40, 180, 60, 25);
		jp_addUsuario.add(lblTelefono);

		txtTelefono.setBounds(110, 180, 330, 25);
		jp_addUsuario.add(txtTelefono);

		lblDireccion.setText("Direccion:");
		lblDireccion.setBounds(38, 220, 60, 25);
		jp_addUsuario.add(lblDireccion);

		txtDireccion.setBounds(110, 220, 330, 25);
		jp_addUsuario.add(txtDireccion);

		lblCorreo.setText("Correo:");
		lblCorreo.setBounds(55, 260, 50, 25);
		jp_addUsuario.add(lblCorreo);

		txtCorreo.setBounds(110, 260, 330, 25);
		jp_addUsuario.add(txtCorreo);

		lblFecha_nacimiento.setText("Fecha Nacimiento:");
		lblFecha_nacimiento.setBounds(40, 300, 125, 25);
		jp_addUsuario.add(lblFecha_nacimiento);

		txtFecha_Nacimiento.setText("[dd/mm/yy]");
		txtFecha_Nacimiento.setBounds(160, 300, 280, 25);
		jp_addUsuario.add(txtFecha_Nacimiento);

		lblContraseña.setText("Contraseña");
		lblContraseña.setBounds(33, 340, 70, 25);
		jp_addUsuario.add(lblContraseña);

		txtContraseña.setBounds(110, 340, 330, 25);
		jp_addUsuario.add(txtContraseña);

		rbActivo.setBounds(175, 380, 80, 25);
		rbActivo.setBackground(Color.LIGHT_GRAY);
		bg_Activo.add(rbActivo);

		rbInactivo.setBounds(260, 380, 80, 25);
		rbInactivo.setBackground(Color.LIGHT_GRAY);
		bg_Activo.add(rbInactivo);

		jp_addUsuario.add(rbActivo);
		jp_addUsuario.add(rbInactivo);

		btnGuargar.setText("Guardar");
		btnGuargar.setBounds(90, 450, 120, 30);
		jp_addUsuario.add(btnGuargar);

		btnCancelar.setText("Cancelar");
		btnCancelar.setBounds(260, 450, 120, 30);
		jp_addUsuario.add(btnCancelar);

		// Funcionalidad
		ActionListener GuardarU = new ActionListener () {

			@Override
			public void actionPerformed(ActionEvent e) {
				String Nombre = txtNombre.getText();
				String Apellido = txtApellido.getText();
				String Telefono = txtTelefono.getText();
				String Direccion = txtDireccion.getText();
				String Correo = txtCorreo.getText();
				String Fecha_Nacimiento = txtFecha_Nacimiento.getText();
				String Contraseña = txtContraseña.getText();

				if (isBlank(Nombre) || isBlank(Apellido) || isBlank(Telefono) || isBlank(Direccion) || isBlank(Correo)
						|| isBlank(Fecha_Nacimiento) || isBlank(Contraseña)) {
					JOptionPane.showMessageDialog(null, "Llenar los campos faltantes");

				} else {
					usu.setNombre(Nombre);
					usu.setApellido(Apellido);
					usu.setTelefono(Integer.parseInt(Telefono));
					usu.setDireccion(Direccion);
					usu.setCorreo_electronico(Correo);
					usu.setFecha_nacimiento(Fecha_Nacimiento);
					usu.setContrasenia(Contraseña);
					usu.setId_rol(rol);
					if (rbActivo.isSelected() == true && rbInactivo.isSelected() == false) {
						int Activo = 1;
						usu.setActivo(Activo);
					} else if (rbActivo.isSelected() == false && rbInactivo.isSelected() == true) {
						int Inactivo = 0;
						usu.setActivo(Inactivo);
					}
					usuDAO.GuardarUsuarios(usu);
					JOptionPane.showMessageDialog(null, "Datos ingresados Correctamente");
					jf_addUsuario.setVisible(false);
					LimpiarTabla();
					ListarUsuarios();
					
				}
				
			}
			
		};
		btnGuargar.addActionListener(GuardarU);
		
		ActionListener cancelar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jf_addUsuario.setVisible(false);
				jf_addUsuario.dispose();
				
			}
			
		};
		btnCancelar.addActionListener(cancelar);

	}
	
	private void Modificar() {
		//Agregando Atributos
		JFrame jf_addModificar = new JFrame();
		JPanel jp_addModificar = new JPanel();
		jf_addModificar.setTitle("Hoteles GT - Administrador");
		jf_addModificar.setLocationRelativeTo(null);
		jf_addModificar.setBounds(125, 250, 500, 550);
		jf_addModificar.setVisible(true);
		jp_addModificar.setLayout(null);
		jp_addModificar.setLayout(null);
		jp_addModificar.setBounds(0, 0, 500, 550);
		jp_addModificar.setBackground(Color.LIGHT_GRAY);
		jf_addModificar.add(jp_addModificar);
		JButton btnMod = new JButton();

		lblTitulo.setText("Modificar Usuario");
		lblTitulo.setFont(new Font("Serig", Font.PLAIN, 25));
		lblTitulo.setBounds(170, 50, 200, 40);
		jp_addModificar.add(lblTitulo);

		lblNombre.setText("Nombre:");
		lblNombre.setBounds(50, 100, 50, 25);
		jp_addModificar.add(lblNombre);

		txtNombre.setBounds(110, 100, 330, 25);
		jp_addModificar.add(txtNombre);

		lblApellido.setText("Apellido:");
		lblApellido.setBounds(50, 140, 50, 25);
		jp_addModificar.add(lblApellido);

		txtApellido.setBounds(110, 140, 330, 25);
		jp_addModificar.add(txtApellido);

		lblTelefono.setText("Teléfono:");
		lblTelefono.setBounds(40, 180, 60, 25);
		jp_addModificar.add(lblTelefono);

		txtTelefono.setBounds(110, 180, 330, 25);
		jp_addModificar.add(txtTelefono);

		lblDireccion.setText("Direccion:");
		lblDireccion.setBounds(38, 220, 60, 25);
		jp_addModificar.add(lblDireccion);

		txtDireccion.setBounds(110, 220, 330, 25);
		jp_addModificar.add(txtDireccion);

		lblCorreo.setText("Correo:");
		lblCorreo.setBounds(55, 260, 50, 25);
		jp_addModificar.add(lblCorreo);

		txtCorreo.setBounds(110, 260, 330, 25);
		jp_addModificar.add(txtCorreo);

		lblFecha_nacimiento.setText("Fecha Nacimiento:");
		lblFecha_nacimiento.setBounds(40, 300, 125, 25);
		jp_addModificar.add(lblFecha_nacimiento);

		
		txtFecha_Nacimiento.setBounds(160, 300, 280, 25);
		jp_addModificar.add(txtFecha_Nacimiento);

		lblContraseña.setText("Contraseña");
		lblContraseña.setBounds(33, 340, 70, 25);
		jp_addModificar.add(lblContraseña);

		txtContraseña.setBounds(110, 340, 330, 25);
		jp_addModificar.add(txtContraseña);

		btnMod.setText("Guardar");
		btnMod.setBounds(90, 450, 120, 30);
		jp_addModificar.add(btnMod);

		btnCancelar.setText("Cancelar");
		btnCancelar.setBounds(260, 450, 120, 30);
		jp_addModificar.add(btnCancelar);
		
		//Funcionalidad
		ActionListener modificar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombre = txtNombre.getText();
				String apellido = txtApellido.getText();
				String telefono = txtTelefono.getText();
				String direccion = txtDireccion.getText();
				String correo = txtCorreo.getText();
				String Fecha_Nacimiento = txtFecha_Nacimiento.getText();
				String Contraseña = txtContraseña.getText();
			
				
				if(isBlank(nombre)||isBlank(apellido)||isBlank(telefono)||isBlank(direccion)||isBlank(correo)|| isBlank(Fecha_Nacimiento)|| isBlank(Contraseña)) {
					JOptionPane.showMessageDialog(null,"Llene el campo faltante");
				}else {
					usu.setNombre(nombre);
					usu.setApellido(apellido);
					usu.setTelefono(Integer.parseInt(telefono));
					usu.setDireccion(direccion);
					usu.setCorreo_electronico(correo);
					usu.setFecha_nacimiento(Fecha_Nacimiento);
					usu.setContrasenia(Contraseña);
					usuDAO.ModificarUsuarios(usu);
					
					JOptionPane.showMessageDialog(null, "Datos actualizados correctamente");
					jf_addModificar.setVisible(false);
					LimpiarTabla();
					ListarUsuarios();
				}
				
			}
		};
		btnMod.addActionListener(modificar);
		
		ActionListener cancelar = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				jf_addModificar.setVisible(false);
				jf_addModificar.dispose();
			}
			
		};
		btnCancelar.addActionListener(cancelar);
	}
	
	private void SeleccionTabla() {
		MouseListener seleccionar = new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila = TablaUsuarios.rowAtPoint(e.getPoint());
				txtCorreo.setText(TablaUsuarios.getValueAt(fila, 4).toString());
			}
			

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

		
		};
		TablaUsuarios.addMouseListener(seleccionar);
		
	}

	public boolean SeleccionarCampos() {
			
			String consulta = "select nombre, apellido, telefono, direccion, fecha_nacimiento, contrasenia from usuarios where correo_electronico = '"+txtCorreo.getText()+"'";
			try {
				con = cn.getConexion();
				ps=con.prepareStatement(consulta);
				rs=ps.executeQuery();
				while(rs.next()) {
					
					nombre_bd= rs.getString("nombre");//Abramo
					apellido_bd=rs.getString("apellido");//Perez
					direccion_bd = rs.getString("direccion");
					telefono_bd = rs.getInt("telefono");//telefono
					fecha_nacimiento_bd = rs.getString("fecha_nacimiento");
					contraseña_bd = rs.getString("contrasenia");	
				}
				txtNombre.setText(nombre_bd);
				txtApellido.setText(apellido_bd);
				txtDireccion.setText(direccion_bd);
				txtTelefono.setText(String.valueOf(telefono_bd));
				txtFecha_Nacimiento.setText(fecha_nacimiento_bd);
				txtContraseña.setText(contraseña_bd);
				
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
	
	public void Eliminar() {
		
		//Agregando Atributos
				JFrame jf_addEliminar = new JFrame();
				JPanel jp_addEliminar = new JPanel();
				jf_addEliminar.setTitle("Hoteles GT - Administrador");
				jf_addEliminar.setLocationRelativeTo(null);
				jf_addEliminar.setBounds(125, 250, 500, 550);
				jf_addEliminar.setVisible(true);
				jp_addEliminar.setLayout(null);
				jp_addEliminar.setLayout(null);
				jp_addEliminar.setBounds(0, 0, 500, 550);
				jp_addEliminar.setBackground(Color.LIGHT_GRAY);
				jf_addEliminar.add(jp_addEliminar);
				JButton btnElim = new JButton();

				lblTitulo.setText("Eliminar Usuario");
				lblTitulo.setFont(new Font("Serig", Font.PLAIN, 25));
				lblTitulo.setBounds(170, 50, 200, 40);
				jp_addEliminar.add(lblTitulo);

				lblNombre.setText("Nombre:");
				lblNombre.setBounds(50, 100, 50, 25);
				jp_addEliminar.add(lblNombre);

				txtNombre.setBounds(110, 100, 330, 25);
				jp_addEliminar.add(txtNombre);

				lblApellido.setText("Apellido:");
				lblApellido.setBounds(50, 140, 50, 25);
				jp_addEliminar.add(lblApellido);

				txtApellido.setBounds(110, 140, 330, 25);
				jp_addEliminar.add(txtApellido);

				lblTelefono.setText("Teléfono:");
				lblTelefono.setBounds(40, 180, 60, 25);
				jp_addEliminar.add(lblTelefono);

				txtTelefono.setBounds(110, 180, 330, 25);
				jp_addEliminar.add(txtTelefono);

				lblDireccion.setText("Direccion:");
				lblDireccion.setBounds(38, 220, 60, 25);
				jp_addEliminar.add(lblDireccion);

				txtDireccion.setBounds(110, 220, 330, 25);
				jp_addEliminar.add(txtDireccion);

				lblCorreo.setText("Correo:");
				lblCorreo.setBounds(55, 260, 50, 25);
				jp_addEliminar.add(lblCorreo);

				txtCorreo.setBounds(110, 260, 330, 25);
				jp_addEliminar.add(txtCorreo);

				lblFecha_nacimiento.setText("Fecha Nacimiento:");
				lblFecha_nacimiento.setBounds(40, 300, 125, 25);
				jp_addEliminar.add(lblFecha_nacimiento);

				
				txtFecha_Nacimiento.setBounds(160, 300, 280, 25);
				jp_addEliminar.add(txtFecha_Nacimiento);

				lblContraseña.setText("Contraseña");
				lblContraseña.setBounds(33, 340, 70, 25);
				jp_addEliminar.add(lblContraseña);

				txtContraseña.setBounds(110, 340, 330, 25);
				jp_addEliminar.add(txtContraseña);

				btnElim.setText("Eliminar");
				btnElim.setBounds(90, 450, 120, 30);
				jp_addEliminar.add(btnElim);

				btnCancelar.setText("Cancelar");
				btnCancelar.setBounds(260, 450, 120, 30);
				jp_addEliminar.add(btnCancelar);

		ActionListener eliminar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String correo;
				int confirmacion = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar?");
				if(confirmacion ==0) {
					correo = txtCorreo.getText();
					usuDAO.EliminarUsuarios(correo);
					JOptionPane.showMessageDialog(null, "Datos eliminados correctamente");
					jf_addEliminar.setVisible(false);
					LimpiarTabla();
					ListarUsuarios();
					
				}
				
			}
		};
		btnElim.addActionListener(eliminar);
		
		ActionListener cancelar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				jf_addEliminar.setVisible(false);
				jf_addEliminar.dispose();
				
			}
		};
	}

public static boolean isBlank(String str) {
		return str.trim().isEmpty();
	}

	public void ejecutar() {
		ventanaUsuarios();
		botones_labels();
		tabla();
		ListarUsuarios();
	}

}

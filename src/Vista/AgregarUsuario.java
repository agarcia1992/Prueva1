package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import Controlador.UsuariosDAO;
import Modelo.usuario;

import java.util.*;

public class AgregarUsuario {
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
	JRadioButton rbActivo = new JRadioButton();
	JRadioButton rbInactivo = new JRadioButton();
	JButton btnGuargar = new JButton();
	JButton btnCancelar = new JButton();
	JTextField txtNombre = new JTextField();
	JTextField txtApellido = new JTextField();
	JTextField txtTelefono = new JTextField();
	JTextField txtDireccion = new JTextField();
	JTextField txtCorreo= new JTextField();
	JTextField txtContraseña = new JTextField();
	JTextField txtFecha_Nacimiento = new JTextField();
	usuario usu = new usuario();
	UsuariosDAO usuDAO = new UsuariosDAO();
	
	
	
	private void VenAgregarUsuarios() {

		jf_addUsuario.setTitle("Hoteles GT - Administrador");
		jf_addUsuario.setLocationRelativeTo(null);
		jf_addUsuario.setBounds(125,250,500,550);
		jf_addUsuario.setVisible(true);
		jp_addUsuario.setLayout(null);
		jp_addUsuario.setLayout(null);
		jp_addUsuario.setBounds(0,0,500,550);
		jp_addUsuario.setBackground(Color.LIGHT_GRAY);
		jf_addUsuario.add(jp_addUsuario);
		
		lblTitulo.setText("Agregar Usuario");
		lblTitulo.setFont(new Font("Serig", Font.PLAIN, 25));
		lblTitulo.setBounds(170, 50, 200, 40);
		jp_addUsuario.add(lblTitulo);
		
		lblNombre.setText("Nombre:");
		lblNombre.setBounds(50, 100, 50, 25);
		jp_addUsuario.add(lblNombre);
		
		txtNombre.setBounds(110,100,330,25);
		jp_addUsuario.add(txtNombre);
		
		lblApellido.setText("Apellido:");
		lblApellido.setBounds(50, 140, 50, 25);
		jp_addUsuario.add(lblApellido);
		
		txtApellido.setBounds(110,140,330,25);
		jp_addUsuario.add(txtApellido);
		
		lblTelefono.setText("Teléfono:");
		lblTelefono.setBounds(40,180, 60, 25);
		jp_addUsuario.add(lblTelefono);
		
		txtTelefono.setBounds(110, 180, 330, 25);
		jp_addUsuario.add(txtTelefono);
		
		lblDireccion.setText("Direccion:");
		lblDireccion.setBounds(38,220 , 60, 25);
		jp_addUsuario.add(lblDireccion);
		
		txtDireccion.setBounds(110, 220, 330, 25);
		jp_addUsuario.add(txtDireccion);
		
		lblCorreo.setText("Correo:");
		lblCorreo.setBounds(55,260, 50, 25);
		jp_addUsuario.add(lblCorreo);
		
		txtCorreo.setBounds(110,260,330,25);
		jp_addUsuario.add(txtCorreo);
		
		lblFecha_nacimiento.setText("Fecha Nacimiento:");
		lblFecha_nacimiento.setBounds(40,300,125,25 );
		jp_addUsuario.add(lblFecha_nacimiento);
		
		txtFecha_Nacimiento.setText("[dd/mm/yy]");
		txtFecha_Nacimiento.setBounds(160, 300,280 ,25);
		jp_addUsuario.add(txtFecha_Nacimiento);
		
		lblContraseña.setText("Contraseña");
		lblContraseña.setBounds(33,340,70,25);
		jp_addUsuario.add(lblContraseña);
		
		txtContraseña.setBounds(110, 340, 330, 25);
		jp_addUsuario.add(txtContraseña);
		
		rbActivo.setText("Activo");
		rbActivo.setBounds(175, 380, 80, 25);
		rbActivo.setBackground(Color.LIGHT_GRAY);
		jp_addUsuario.add(rbActivo);
		
		rbInactivo.setText("Inactivo");
		rbInactivo.setBounds(260, 380, 80, 25);
		rbInactivo.setBackground(Color.LIGHT_GRAY);
		jp_addUsuario.add(rbInactivo);
		
		btnGuargar.setText("Guardar");
		btnGuargar.setBounds(90, 450, 120, 30);
		jp_addUsuario.add(btnGuargar);
		
		btnCancelar.setText("Cancelar");
		btnCancelar.setBounds(260,450,120,30);
		jp_addUsuario.add(btnCancelar);
		
		
	
	}
	
	public static boolean isBlank(String str)
    {
        return str.trim().isEmpty();
    }
	
	public void ejecutar() {
		VenAgregarUsuarios();
	}
	
	private void Funcionalidades() {
		ActionListener guardar = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
		
			String Nombre = txtNombre.getText();
			String Apellido = txtApellido.getText();
			String Telefono = txtTelefono.getText();
			String Direccion = txtDireccion.getText();
			String Correo = txtCorreo.getText();
			String Fecha_Nacimiento = txtFecha_Nacimiento.getText();
			String Contraseña = txtContraseña.getText();
			
			if(isBlank(Nombre) || isBlank(Apellido) || isBlank(Telefono) || isBlank(Direccion) || isBlank(Correo) || 
					isBlank(Fecha_Nacimiento) || isBlank(Contraseña)) {
				JOptionPane.showMessageDialog(null, "Llenar los campos faltantes");
				
			}else {
				usu.setNombre(Nombre);
				usu.setApellido(Apellido);
				usu.setTelefono(Integer.parseInt(Telefono));
				usu.setDireccion(Direccion);
				usu.setCorreo_electronico(Correo);
				usu.setFecha_nacimiento(Fecha_Nacimiento);
				usu.setContrasenia(Contraseña);
				usuDAO.AgregarUsuario(usu);
				JOptionPane.showMessageDialog(null, "Datos ingresados Correctamente");
				LimpiarTabla();
				usuarios usu2 = new usuarios();
				usu2.
				
				}
			}
				
			
		};
			
	}
	
	public void LimpiarTabla()
    {
       for (int i = 0; i <usu2.modelo.getRowCount(); i++) {
          usu2.modelo.removeRow(i);
          i=i-1;
       }
    }
	
	public static void main(String[] args) {
		AgregarUsuario au = new AgregarUsuario();
		au.ejecutar();
	}
}

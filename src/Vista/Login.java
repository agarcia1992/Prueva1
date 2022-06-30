package Vista;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import javax.imageio.ImageIO;
import javax.swing.*;


import Modelo.Conexion;

public class Login {
	//Variables conexion bd
	Conexion con = new Conexion();
	Connection reg = con.getConexion();
	Statement stmt = null;
	ResultSet rs = null;
	//Atributos de la clase
	JFrame jf_principal = new JFrame();
	JPanel jp_principal = new JPanel ();
	JLabel lblUsuario = new JLabel();
	JLabel lblContraseña = new JLabel();
	JLabel imgHotel = new JLabel();
	JTextField txtUsuario = new JTextField();
	JPasswordField txtContraseña = new JPasswordField();
	JButton btnIncioSession = new JButton();
	String Qnombre;
	String Qapellido;
	int QRol = 0;
	int contador=1;
	
	
private void inicio_session() throws IOException{
	//Metodos de la clase Login
	
	File img = new File("E:\\Carlos Meza\\Documentos\\Eclipse\\Hoteles GT\\src\\Img\\hotel.png");
	BufferedImage bufferImg = ImageIO.read(img);
	ImageIcon icono = new ImageIcon(bufferImg);	
	imgHotel.setIcon(icono);
	imgHotel.setBounds(125, 40, 250, 100);
	jp_principal.add(imgHotel);
	
	jf_principal.setTitle("Inicio de Sesion");
	jf_principal.setLocationRelativeTo(null);
	jf_principal.setBounds(500, 250, 500, 500);
	jf_principal.setVisible(true);
	jp_principal.setBounds(500,250,500,500);
	jp_principal.setLayout(null);
	jf_principal.add(jp_principal);
	
	lblUsuario.setText("Usuario");
	lblUsuario.setBounds(50, 150, 50, 50);
	jp_principal.add(lblUsuario);
	txtUsuario.setBounds(50, 200, 380, 40);
	jp_principal.add(txtUsuario);
	lblContraseña.setText("Contraseña");
	lblContraseña.setBounds(50, 240, 80, 50);
	jp_principal.add(lblContraseña);
	txtContraseña.setBounds(50, 290, 380, 40);
	jp_principal.add(txtContraseña);
}

@SuppressWarnings("deprecation")
public void login() {
	String Qusuario = null;
	String Qcontrasena = null;
	String consulta = "Select nombre, apellido, correo_electronico, id_rol, contrasenia from usuarios where correo_electronico = '"+txtUsuario.getText()+"'";
	try {
		stmt = reg.createStatement();
		rs = stmt.executeQuery(consulta);
			while(rs.next()){
				Qusuario = rs.getString("correo_electronico");
				Qcontrasena = rs.getString("contrasenia");
				Qnombre = rs.getString("nombre");
				Qapellido = rs.getString("apellido");
				QRol = rs.getInt("id_rol");
			}
	} catch (Exception e) {
		
	}	
	String usuario = txtUsuario.getText();
	String contrasena = txtContraseña.getText();
	System.out.println(Qusuario+" "+Qcontrasena+" "+QRol);
	
	if(usuario.isBlank() || contrasena.isBlank()) {
		JOptionPane.showMessageDialog(null, "Ingresa un usuario y contraseña.");
	}
	else {
		if(usuario.equals(Qusuario) && contrasena.equals(Qcontrasena)) {
			JOptionPane.showMessageDialog(null, "Bienvenido");
			jf_principal.setVisible(false);
			usuarios usu = new usuarios();
			usu.rol = QRol;
			usu.nombre = Qnombre;
			usu.apellido = Qapellido;
			usu.ejecutar();
			
		}
		else {
			JOptionPane.showMessageDialog(null,"Usuario y Contraseña Incorrectos.");
			if(contador == 3) {
				jf_principal.setVisible(false);
			}
			contador++;
		}
	}
}

private void btnIniciarSesion() {
	btnIncioSession.setText("Iniciar Sesión");
	btnIncioSession.setBounds(125, 350, 250, 50);
	jp_principal.add(btnIncioSession);
	
	ActionListener Ingresar = new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			login();
			
		}
	};
	btnIncioSession.addActionListener(Ingresar);
}


	public static void main(String[] args) throws IOException {
		Login L1 = new Login();
		L1.inicio_session();
		L1.btnIniciarSesion();
	}
}

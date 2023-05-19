package app;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;

@SuppressWarnings("serial")
public class Login extends JFrame {

	private JPanel contentPane;
	private Modelo modelo;
	private Controlador controlador;
	private JTextField txtUsr;
	private JPasswordField txtPass;
	private JLabel lblCorreccion;
	
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		contentPane.setLayout(null);
		
		JLabel lblUsr = new JLabel("Nombre");
		lblUsr.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsr.setBounds(56, 80, 85, 13);
		contentPane.add(lblUsr);
		
		JLabel lblPass = new JLabel("Contraseña");
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPass.setBounds(56, 121, 85, 13);
		contentPane.add(lblPass);
		
		txtUsr = new JTextField();
		txtUsr.setBounds(262, 77, 96, 19);
		contentPane.add(txtUsr);
		txtUsr.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(262, 115, 96, 19);
		contentPane.add(txtPass);
		
		lblCorreccion = new JLabel("");
		lblCorreccion.setBounds(87, 148, 265, 39);
		contentPane.add(lblCorreccion);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.setBackground(new Color(89, 116, 190));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.login();
			}
		});
		btnLogin.setBounds(127, 197, 180, 21);
		contentPane.add(btnLogin);
		

		
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public String getUsr() {
		return txtUsr.getText();
	}

	public String getPass() {
		
		return String.valueOf(txtPass.getPassword());
	}

	public void actualizar() {
		String resultado = modelo.getResultado();
		
		if(resultado.equals("correcto")) {
			controlador.bienvinida();
		}else if(resultado.equals("ERROR")) {
			lblCorreccion.setText("Usuario o contraseña incorrecto");
			lblCorreccion.setBackground(Color.RED);
		}else {
			System.exit(0);
		}
	}

}

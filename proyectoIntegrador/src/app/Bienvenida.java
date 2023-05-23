package app;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
@SuppressWarnings("serial")

public class Bienvenida extends JFrame {

	private JPanel contentPane;
	private Controlador controlador;
	private JTextField txtUsuario;
	private JTextField txtSaldo; 
	private JTextField textCant;
	private Modelo modelo;
	
	
	public Bienvenida() {	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,500,420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCasino = new JButton("ACCEDER AL CASINO");
		btnCasino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallaMenuRuleta();
			}
		});
		btnCasino.setForeground(new Color(255, 255, 255));
		btnCasino.setBackground(new Color(89, 116, 190));
		btnCasino.setBounds(153, 86, 180, 21);
		contentPane.add(btnCasino);
		
		JButton btnApuestas = new JButton("ACCEDER A APUESTAS");
		btnApuestas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.pantallaApuestas();
			}
		});
		btnApuestas.setForeground(new Color(255, 255, 255));
		btnApuestas.setBackground(new Color(89, 116, 190));
		btnApuestas.setBounds(153, 155, 180, 21);
		contentPane.add(btnApuestas);
		
		JButton btnEstadisticas = new JButton("ESTADISTICAS");
		btnEstadisticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//accedera a las estadisticas de los equipos de la BBDD que será en la 
				//proxima entrega 
			}
		});
		btnEstadisticas.setForeground(new Color(255, 255, 255));
		btnEstadisticas.setBackground(new Color(89, 116, 190));
		btnEstadisticas.setBounds(153, 288, 180, 21);
		contentPane.add(btnEstadisticas);
		
		txtUsuario = new JTextField();
		txtUsuario.setBackground(new Color(89, 116, 190));
		txtUsuario.setForeground(new Color(255, 255, 255));
		txtUsuario.setEditable(false);
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtUsuario.setBounds(10, 24, 200, 19);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JButton btnIngresarDinero = new JButton("INGRESAR");
		btnIngresarDinero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textCant.setVisible(true);
				if(!textCant.getText().equals("")) {
					controlador.modificarSaldo(Double.parseDouble(textCant.getText()));
				}
			}
		});
		btnIngresarDinero.setFont(new Font("Tahoma", Font.PLAIN, 8));
		btnIngresarDinero.setBackground(new Color(89, 116, 190));
		btnIngresarDinero.setForeground(new Color(255, 255, 255));
		btnIngresarDinero.setBounds(343, 25, 85, 21);
		contentPane.add(btnIngresarDinero);
		
		JButton btnAjustes = new JButton("AJUSTES");
		
		btnAjustes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.ajustes();
			}
		});
		btnAjustes.setForeground(new Color(255, 255, 255));
		btnAjustes.setBackground(new Color(86, 116, 190));
		btnAjustes.setBounds(153, 224, 180, 21);
		contentPane.add(btnAjustes);
		
		txtSaldo = new JTextField();
		txtSaldo.setForeground(new Color(255, 255, 255));
		txtSaldo.setBackground(new Color(86, 116, 190));
		txtSaldo.setText("Saldo: 0€");
		txtSaldo.setEditable(false);
		txtSaldo.setBounds(10, 339, 96, 19);
		contentPane.add(txtSaldo);
		txtSaldo.setColumns(10);
		
		textCant = new JTextField();
		textCant.addKeyListener(new KeyAdapter() {
			//evento para que solo se pueda escribir numeros
			@Override
			public void keyReleased(KeyEvent e) {
				if((e.getKeyChar()>57 || e.getKeyChar()<48)&& e.getKeyChar() !=8) {
		            String mod= textCant.getText();
		            mod= mod.substring(0,mod.length()-1);
		            textCant.setText(mod);
		        }
			}
		});
		textCant.setBounds(438, 25, 38, 19);
		textCant.setVisible(false);
		contentPane.add(textCant);
		textCant.setColumns(10);
		
		
	}


	public void setTxtUsuario(String txtUsuario) {
		this.txtUsuario.setText("Usuario: "+txtUsuario);
	}
	
	public void setTxtSaldo(String txtSaldo) {
		this.txtSaldo.setText(txtSaldo); 
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}


	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	

 }



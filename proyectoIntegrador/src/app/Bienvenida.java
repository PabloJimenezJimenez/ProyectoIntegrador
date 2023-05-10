package app;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.*;
import java.awt.Font;
public class Bienvenida extends JFrame {

	private JPanel contentPane;
	private Controlador controlador;
	private Modelo modelo;
	private JTextField txtUsuario;	
	public Bienvenida() {	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,500,420);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCasino = new JButton("ACCEDER AL CASINO");
		btnCasino.setForeground(new Color(0, 0, 0));
		btnCasino.setBackground(new Color(89, 116, 190));
		btnCasino.setBounds(147, 67, 180, 21);
		contentPane.add(btnCasino);
		
		JButton btnApuestas = new JButton("ACCEDER A APUESTAS");
		btnApuestas.setForeground(new Color(0, 0, 0));
		btnApuestas.setBackground(new Color(89, 116, 190));
		btnApuestas.setBounds(147, 125, 180, 21);
		contentPane.add(btnApuestas);
		
		JButton btnEstadisticas = new JButton("ESTADISTICAS");
		btnEstadisticas.setForeground(new Color(0, 0, 0));
		btnEstadisticas.setBackground(new Color(89, 116, 190));
		btnEstadisticas.setBounds(147, 191, 180, 21);
		contentPane.add(btnEstadisticas);
		
		txtUsuario = new JTextField();
		txtUsuario.setBackground(new Color(89, 116, 190));
		txtUsuario.setForeground(new Color(255, 255, 255));
		txtUsuario.setEditable(false);
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtUsuario.setBounds(10, 24, 200, 19);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		
	}


	public void setTxtUsuario(String txtUsuario) {
		this.txtUsuario.setText(" Usuario: "+txtUsuario);
	}
	
	
 }



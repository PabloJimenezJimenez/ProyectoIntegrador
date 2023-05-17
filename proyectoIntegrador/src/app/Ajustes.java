package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Ajustes extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuar;
	private JTextField txtContra;
	private Controlador miContralador;


	
	public Ajustes() {
		
		miContralador = new Controlador();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsr = new JLabel("Usuario:");
		lblUsr.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsr.setBounds(63, 29, 68, 21);
		contentPane.add(lblUsr);
		
		JLabel lblContraseña = new JLabel("Contraseña: ");
		lblContraseña.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblContraseña.setBounds(63, 84, 68, 13);
		contentPane.add(lblContraseña);
		
		txtUsuar = new JTextField();
		txtUsuar.setBounds(264, 31, 96, 19);
		contentPane.add(txtUsuar);
		txtUsuar.setColumns(10);
		
		txtContra = new JTextField();
		txtContra.setBounds(264, 82, 96, 19);
		contentPane.add(txtContra);
		txtContra.setColumns(10);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miContralador.cambiarUsr();
			}
		});
		btnGuardar.setBounds(175, 213, 85, 21);
		contentPane.add(btnGuardar);
	
	}


	public String getTxtUsuario() {
		return txtUsuar.getText();
	}


	public JTextField getTxtContraseña() {
		return txtContra;
	}
	
	
}

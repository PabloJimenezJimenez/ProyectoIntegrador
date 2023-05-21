package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
public class Ajustes extends JFrame {

	private JPanel contentPane;
	private JTextField txtUsuar;
	private JTextField txtContra;
	private Controlador miControlador;


	
	public Ajustes() {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
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
		btnGuardar.setBackground(new Color(86, 116, 190));
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//guardaremos los datos en la BBDD 
				//de momento actualizaremos el usuario pero no se guardara cuando cierre
				miControlador.cambiarDatos();
				miControlador.guardarUsr();
			}
		});
		btnGuardar.setBounds(175, 213, 85, 21);
		contentPane.add(btnGuardar);
	
	}

	//generamos los getters y los setters
	
	public String getTxtUsuario() {
		return txtUsuar.getText();
	}


	public String getnewPasswd() {
		return txtContra.getText();
	}


	public void setMiContralador(Controlador miContralador) {
		this.miControlador = miContralador;
	}
	
	
}

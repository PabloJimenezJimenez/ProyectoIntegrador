package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MenuCasino extends JFrame {

	private JPanel contentPane;
	private Controlador controlador;
	public MenuCasino() {
		setTitle("Menu Casino");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 755, 433);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		//boton que te envia a la parte de la ruleta de la suerte
		JButton btnRuleta = new JButton("Ruleta de la suerte");
		btnRuleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.cambioPantallaRuleta();
			}
		});
		btnRuleta.setBackground(new Color(89, 116, 190));
		btnRuleta.setBounds(242, 186, 252, 51);
		contentPane.add(btnRuleta);
		//boton que te hace retroceder al menu anterior
		JButton btnVolver = new JButton("<---");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.menuCasinoBienvenida();
			}
		});
		btnVolver.setBackground(new Color(89, 116, 190));
		btnVolver.setBounds(54, 285, 103, 66);
		contentPane.add(btnVolver);
		
		JLabel lblNewLabel = new JLabel("Menu Casino");
		lblNewLabel.setBounds(328, 36, 145, 45);
		contentPane.add(lblNewLabel);
	}
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
}

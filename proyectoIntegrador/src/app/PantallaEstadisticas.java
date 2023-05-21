package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class PantallaEstadisticas extends JFrame {

	private JPanel contentPane;
	private Controlador micontrolador;
	public PantallaEstadisticas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEstadisticas = new JLabel("PROXIMAMENTE!!!!");
		lblEstadisticas.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblEstadisticas.setBounds(157, 38, 142, 13);
		contentPane.add(lblEstadisticas);
		
		JButton btnMenu = new JButton("Atrás");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				micontrolador.volverMenuPrincipal();
			}
		});
		btnMenu.setBounds(28, 232, 85, 21);
		contentPane.add(btnMenu);
	}
	public void setMicontrolador(Controlador micontrolador) {
		this.micontrolador = micontrolador;
	}

		
}

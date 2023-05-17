package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ApuestasDep extends JFrame {

	private JPanel contentPane;
	private MainPablo principal;
	public ApuestasDep() {
		principal = new MainPablo(); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PARTIDO");
		lblNewLabel.setBounds(179, 43, 67, 23);
		contentPane.add(lblNewLabel);
		
		JButton btnAnt = new JButton("Anterior");
		btnAnt.setBackground(new Color(89, 116, 190));
		btnAnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				principal.pantallaAnterior();
			}
		});
		btnAnt.setBounds(10, 11, 91, 23);
		contentPane.add(btnAnt);
		
		JLabel lblPartido = new JLabel("");
		lblPartido.setBounds(135, 80, 144, 14);
		contentPane.add(lblPartido);
		
		
	}

}

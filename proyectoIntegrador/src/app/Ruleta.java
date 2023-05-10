package app;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JSpinner;

public class Ruleta extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ruleta frame = new Ruleta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ruleta() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 841, 528);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JLabel lblNewLabel = new JLabel("Numero:");
		lblNewLabel.setBounds(253, 373, 96, 69);
		contentPane.add(lblNewLabel);
		
		JLabel txtNumero = new JLabel("");
		txtNumero.setBounds(318, 385, 79, 45);
		contentPane.add(txtNumero);
		
		JLabel lblColor = new JLabel("Color:");
		lblColor.setBounds(431, 373, 96, 69);
		contentPane.add(lblColor);
		
		JLabel txtColor = new JLabel("");
		txtColor.setBounds(479, 385, 79, 45);
		contentPane.add(txtColor);
		
		JPanel panel = new JPanel();
		panel.setBounds(83, 31, 695, 227);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(3, 12, 0, 0));
		
		JButton uno = new JButton("1");
		uno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("1");
				txtColor.setText("Negro");
			}
		});
		uno.setForeground(new Color(255, 255, 255));
		uno.setBackground(Color.BLACK);
		panel.add(uno);
		
		JButton cuatro = new JButton("4");
		cuatro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("4");
				txtColor.setText("Rojo");
			}
		});
		cuatro.setForeground(new Color(255, 255, 255));
		cuatro.setBackground(new Color(255, 0, 0));
		panel.add(cuatro);
		
		JButton siete = new JButton("7");
		siete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("7");
				txtColor.setText("Negro");
			}
		});
		siete.setForeground(Color.WHITE);
		siete.setBackground(Color.BLACK);
		panel.add(siete);
		
		JButton diez = new JButton("10");
		diez.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("10");
				txtColor.setText("Negro");
			}
		});
		diez.setForeground(Color.WHITE);
		diez.setBackground(Color.BLACK);
		panel.add(diez);
		
		JButton trece = new JButton("13");
		trece.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("13");
				txtColor.setText("Negro");
			}
		});
		trece.setForeground(Color.WHITE);
		trece.setBackground(Color.BLACK);
		panel.add(trece);
		
		JButton dieciseis = new JButton("16");
		dieciseis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("16");
				txtColor.setText("Rojo");
			}
		});
		dieciseis.setForeground(Color.WHITE);
		dieciseis.setBackground(Color.RED);
		panel.add(dieciseis);
		
		JButton btnNewButton_18 = new JButton("19");
		btnNewButton_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("19");
				txtColor.setText("Rojo");
			}
		});
		btnNewButton_18.setForeground(Color.WHITE);
		btnNewButton_18.setBackground(Color.RED);
		panel.add(btnNewButton_18);
		
		JButton veintidos = new JButton("22");
		veintidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("22");
				txtColor.setText("Negro");
			}
		});
		veintidos.setBackground(Color.BLACK);
		veintidos.setForeground(Color.WHITE);
		panel.add(veintidos);
		
		JButton veinticinco = new JButton("25");
		veinticinco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("25");
				txtColor.setText("Rojo");
			}
		});
		veinticinco.setForeground(Color.WHITE);
		veinticinco.setBackground(Color.RED);
		panel.add(veinticinco);
		
		JButton veintiocho = new JButton("28");
		veintiocho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("28");
				txtColor.setText("Negro");
			}
		});
		veintiocho.setForeground(Color.WHITE);
		veintiocho.setBackground(Color.BLACK);
		panel.add(veintiocho);
		
		JButton treintYUno = new JButton("31");
		treintYUno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("31");
				txtColor.setText("Negro");
			}
		});
		treintYUno.setForeground(Color.WHITE);
		treintYUno.setBackground(Color.BLACK);
		panel.add(treintYUno);
		
		JButton treintaYCuatro = new JButton("34");
		treintaYCuatro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("34");
				txtColor.setText("Rojo");
			}
		});
		treintaYCuatro.setForeground(Color.WHITE);
		treintaYCuatro.setBackground(Color.RED);
		panel.add(treintaYCuatro);
		
		JButton btnNewButton_8 = new JButton("2");
		btnNewButton_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("2");
				txtColor.setText("Rojo");
			}
		});
		btnNewButton_8.setForeground(new Color(255, 255, 255));
		btnNewButton_8.setBackground(new Color(255, 0, 0));
		panel.add(btnNewButton_8);
		
		JButton cinco = new JButton("5");
		cinco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("5");
				txtColor.setText("Negro");
			}
		});
		cinco.setForeground(Color.WHITE);
		cinco.setBackground(Color.BLACK);
		panel.add(cinco);
		
		JButton ocho = new JButton("8");
		ocho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("8");
				txtColor.setText("Rojo");
			}
		});
		ocho.setForeground(Color.WHITE);
		ocho.setBackground(Color.RED);
		panel.add(ocho);
		
		JButton once = new JButton("11");
		once.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("11");
				txtColor.setText("Negro");
			}
		});
		once.setForeground(Color.WHITE);
		once.setBackground(Color.BLACK);
		panel.add(once);
		
		JButton catorce = new JButton("14");
		catorce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("14");
				txtColor.setText("Rojo");
			}
		});
		catorce.setForeground(Color.WHITE);
		catorce.setBackground(Color.RED);
		panel.add(catorce);
		
		JButton diecisiete = new JButton("17");
		diecisiete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("17");
				txtColor.setText("Negro");
			}
		});
		diecisiete.setForeground(Color.WHITE);
		diecisiete.setBackground(Color.BLACK);
		panel.add(diecisiete);
		
		JButton veinte = new JButton("20");
		veinte.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("20");
				txtColor.setText("Negro");
			}
		});
		veinte.setBackground(Color.BLACK);
		veinte.setForeground(Color.WHITE);
		panel.add(veinte);
		
		JButton veintitres = new JButton("23");
		veintitres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("23");
				txtColor.setText("Rojo");
			}
		});
		veintitres.setForeground(Color.WHITE);
		veintitres.setBackground(Color.RED);
		panel.add(veintitres);
		
		JButton veintiseis = new JButton("26");
		veintiseis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("26");
				txtColor.setText("Negro");
			}
		});
		veintiseis.setForeground(Color.WHITE);
		veintiseis.setBackground(Color.BLACK);
		panel.add(veintiseis);
		
		JButton veintinueve = new JButton("29");
		veintinueve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("29");
				txtColor.setText("Negro");
			}
		});
		veintinueve.setForeground(Color.WHITE);
		veintinueve.setBackground(Color.BLACK);
		panel.add(veintinueve);
		
		JButton treintaYDos = new JButton("32");
		treintaYDos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("32");
				txtColor.setText("Rojo");
			}
		});
		treintaYDos.setForeground(Color.WHITE);
		treintaYDos.setBackground(Color.RED);
		panel.add(treintaYDos);
		
		JButton treintaYCinco = new JButton("35");
		treintaYCinco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("35");
				txtColor.setText("Negro");
			}
		});
		treintaYCinco.setForeground(Color.WHITE);
		treintaYCinco.setBackground(Color.BLACK);
		panel.add(treintaYCinco);
		
		JButton tres = new JButton("3");
		tres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("3");
				txtColor.setText("Negro");
			}
		});
		tres.setForeground(new Color(255, 255, 255));
		tres.setBackground(new Color(0, 0, 0));
		panel.add(tres);
		
		JButton seis = new JButton("6");
		seis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("6");
				txtColor.setText("Rojo");
			}
		});
		seis.setBackground(Color.RED);
		seis.setForeground(new Color(255, 255, 255));
		panel.add(seis);
		
		JButton nueve = new JButton("9");
		nueve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("9");
				txtColor.setText("Negro");
			}
		});
		nueve.setForeground(Color.WHITE);
		nueve.setBackground(Color.BLACK);
		panel.add(nueve);
		
		JButton doce = new JButton("12");
		doce.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("12");
				txtColor.setText("Rojo");
			}
		});
		doce.setForeground(Color.WHITE);
		doce.setBackground(Color.RED);
		panel.add(doce);
		
		JButton quince = new JButton("15");
		quince.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("15");
				txtColor.setText("Negro");
			}
		});
		quince.setForeground(Color.WHITE);
		quince.setBackground(Color.BLACK);
		panel.add(quince);
		
		JButton dieciocho = new JButton("18");
		dieciocho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("18");
				txtColor.setText("Rojo");
			}
		});
		dieciocho.setForeground(Color.WHITE);
		dieciocho.setBackground(Color.RED);
		panel.add(dieciocho);
		
		JButton veintiuno = new JButton("21");
		veintiuno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("21");
				txtColor.setText("Rojo");
			}
		});
		veintiuno.setForeground(Color.WHITE);
		veintiuno.setBackground(Color.RED);
		panel.add(veintiuno);
		
		JButton veinticuatro = new JButton("24");
		veinticuatro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("24");
				txtColor.setText("Negro");
			}
		});
		veinticuatro.setBackground(Color.BLACK);
		veinticuatro.setForeground(Color.WHITE);
		panel.add(veinticuatro);
		
		JButton veintisiete = new JButton("27");
		veintisiete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("27");
				txtColor.setText("Rojo");
			}
		});
		veintisiete.setForeground(Color.WHITE);
		veintisiete.setBackground(Color.RED);
		panel.add(veintisiete);
		
		JButton treinta = new JButton("30");
		treinta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("30");
				txtColor.setText("Rojo");
			}
		});
		treinta.setForeground(Color.WHITE);
		treinta.setBackground(Color.RED);
		panel.add(treinta);
		
		JButton treintaYTres = new JButton("33");
		treintaYTres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("33");
				txtColor.setText("Negro");
			}
		});
		treintaYTres.setForeground(Color.WHITE);
		treintaYTres.setBackground(Color.BLACK);
		panel.add(treintaYTres);
		
		JButton treintaYSeis = new JButton("36");
		treintaYSeis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("36");
				txtColor.setText("Rojo");
			}
		});
		treintaYSeis.setForeground(Color.WHITE);
		treintaYSeis.setBackground(Color.RED);
		panel.add(treintaYSeis);
		
		JButton cero = new JButton("0");
		cero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("0");
				txtColor.setText("Verde");
			}
		});
		cero.setForeground(new Color(0, 0, 0));
		cero.setBackground(new Color(0, 255, 0));
		cero.setBounds(10, 31, 63, 227);
		contentPane.add(cero);
		
		JButton btnRojo = new JButton("Rojo");
		btnRojo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("");
				txtColor.setText("Rojo");
			}
		});
		btnRojo.setBackground(Color.RED);
		btnRojo.setForeground(Color.WHITE);
		btnRojo.setBounds(83, 268, 350, 56);
		contentPane.add(btnRojo);
		
		JButton btnNegro = new JButton("Negro");
		btnNegro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("");
				txtColor.setText("Negro");
			}
		});
		btnNegro.setForeground(Color.WHITE);
		btnNegro.setBackground(Color.BLACK);
		btnNegro.setBounds(443, 268, 335, 56);
		contentPane.add(btnNegro);
		
		
		
		JSpinner spinner = new JSpinner();
		spinner.setBounds(658, 392, 63, 32);
		contentPane.add(spinner);
		
		JButton btnApostar = new JButton("apostar");
		btnApostar.setBackground(new Color(89, 116, 190));
		btnApostar.setBounds(731, 388, 85, 39);
		contentPane.add(btnApostar);
		
		JButton btnReiniciar = new JButton("reiniciar");
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNumero.setText("");
				txtColor.setText("");
			}
		});
		btnReiniciar.setForeground(Color.BLACK);
		btnReiniciar.setBackground(new Color(89, 116, 190));
		btnReiniciar.setBounds(82, 391, 100, 32);
		contentPane.add(btnReiniciar);
	}
}

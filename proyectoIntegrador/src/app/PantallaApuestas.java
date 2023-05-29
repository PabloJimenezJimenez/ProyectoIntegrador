package app;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class PantallaApuestas extends JFrame {

	private JPanel contentPane;
	private Controlador controladoor;
	private JButton btnApuesta1;
	private JButton btnApuesta2;
	private JButton btnApuesta3;
	private JButton btnApuesta4;
	private JButton btnApuesta5;
	private JButton btnApuesta6;
	private JButton btnApuesta7;
	private JLabel lblTxtSaldo;

	public PantallaApuestas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnApuesta1 = new JButton("ANDALUS EFES – MACABI TEL AVIV ");
		btnApuesta1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladoor.cambiarPantalla();//cambio la pantalla
				controladoor.MoverDatos(getBtnApuesta1());//Paso la información que tiene el jbutton
			}
		});
		btnApuesta1.setBackground(new Color(89, 116, 190));
		btnApuesta1.setBounds(78, 47, 281, 23);
		contentPane.add(btnApuesta1);
		
		btnApuesta2 = new JButton("ANDALUS EFES – MACABI TEL AVIV ");
		btnApuesta2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladoor.cambiarPantalla();
				controladoor.MoverDatos(getBtnApuesta2());
			}
		});
		btnApuesta2.setBackground(new Color(89, 116, 190));
		btnApuesta2.setBounds(78, 71, 281, 23);
		contentPane.add(btnApuesta2);
		
		btnApuesta3 = new JButton("ANDALUS EFES – MACABI TEL AVIV ");
		btnApuesta3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladoor.cambiarPantalla();
				controladoor.MoverDatos(getBtnApuesta3());
			}
		});
		btnApuesta3.setBackground(new Color(89, 116, 190));
		btnApuesta3.setBounds(78, 95, 281, 23);
		contentPane.add(btnApuesta3);
		
		btnApuesta4 = new JButton("ANDALUS EFES – MACABI TEL AVIV ");
		btnApuesta4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladoor.cambiarPantalla();//cambio de pantalla
				controladoor.MoverDatos(getBtnApuesta4());//Paso la información que tiene el jbutton
			}
			
		});
		btnApuesta4.setBackground(new Color(89, 116, 190));
		btnApuesta4.setBounds(78, 119, 281, 23);
		contentPane.add(btnApuesta4);
		
		btnApuesta5 = new JButton("ANDALUS EFES – MACABI TEL AVIV ");
		btnApuesta5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladoor.cambiarPantalla();//cambio de pantalla
				controladoor.MoverDatos(getBtnApuesta5());//Paso la información que tiene el jbutton
			}
		});
		btnApuesta5.setBackground(new Color(89, 116, 190));
		btnApuesta5.setBounds(78, 143, 281, 23);
		contentPane.add(btnApuesta5);
		
		btnApuesta6 = new JButton("ANDALUS EFES – MACABI TEL AVIV ");
		btnApuesta6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladoor.cambiarPantalla();
				controladoor.MoverDatos(getBtnApuesta6());//Paso la informacion que tiene el jbutton
			}
		});
		btnApuesta6.setBackground(new Color(89, 116, 190));
		btnApuesta6.setBounds(78, 167, 281, 23);
		contentPane.add(btnApuesta6);
		
		btnApuesta7 = new JButton("ANDALUS EFES – MACABI TEL AVIV ");
		btnApuesta7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladoor.cambiarPantalla();
				controladoor.MoverDatos(getBtnApuesta7());//Paso la informacion que tiene el jbutton
			}
		});
		btnApuesta7.setBackground(new Color(89, 116, 190));
		btnApuesta7.setBounds(78, 191, 281, 23);
		contentPane.add(btnApuesta7);
		
		JButton btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controladoor.volverMenu();
			}
		});
		btnAtras.setBackground(new Color(86, 116, 190));
		btnAtras.setBounds(10, 10, 85, 21);
		contentPane.add(btnAtras);
		
		lblTxtSaldo = new JLabel("New label",SwingConstants.CENTER);
		lblTxtSaldo.setBounds(295, 13, 133, 14);
		contentPane.add(lblTxtSaldo);
	}

	//genero los getter y setter 
	public void setBtnApuesta1(String btnApuesta1) {
		this.btnApuesta1.setText(btnApuesta1);
	}

	public void setBtnApuesta2(String btnApuesta2) {
		this.btnApuesta2.setText(btnApuesta2);
	}

	public void setBtnApuesta3(String btnApuesta3) {
		this.btnApuesta3.setText(btnApuesta3);
	}

	public void setBtnApuesta4(String btnApuesta4) {
		this.btnApuesta4.setText(btnApuesta4);
	}

	public void setBtnApuesta5(String btnApuesta5) {
		this.btnApuesta5.setText(btnApuesta5);
	}

	public void setBtnApuesta6(String btnApuesta6) {
		this.btnApuesta6.setText(btnApuesta6);
	}

	public void setBtnApuesta7(String btnApuesta7) {
		this.btnApuesta7.setText(btnApuesta7);
	}

	public void setControladoor(Controlador controladoor) {
		this.controladoor = controladoor;
	}
	public String getBtnApuesta1() {
		return btnApuesta1.getText();
	}

	public String getBtnApuesta2() {
		return btnApuesta2.getText();
	}

	public String getBtnApuesta3() {
		return btnApuesta3.getText();
	}

	public String getBtnApuesta4() {
		return btnApuesta4.getText();
	}

	public String getBtnApuesta5() {
		return btnApuesta5.getText();
	}

	public String getBtnApuesta6() {
		return btnApuesta6.getText();
	}

	public String getBtnApuesta7() {
		return btnApuesta7.getText();
	}
	
	
	public void setLblTxtSaldo(String lblTxtSaldo) {
		this.lblTxtSaldo.setText(lblTxtSaldo); 
	}

	public void generarJornada(HashMap<Integer, String> partidos) {
		//Pongo el texto de cada jornada en el btn corespondiente
		btnApuesta1.setText(partidos.get(1));
		btnApuesta2.setText(partidos.get(2));
		btnApuesta3.setText(partidos.get(3));
		btnApuesta4.setText(partidos.get(4));
		btnApuesta5.setText(partidos.get(5));
		btnApuesta6.setText(partidos.get(6));
		btnApuesta7.setText(partidos.get(7));
		
	}

		
}
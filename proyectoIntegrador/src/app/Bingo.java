package app;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.util.Iterator;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class Bingo extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bingo frame = new Bingo();
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
	//contadores
	int contBola;
	int contFila;
	int contColumna;
	public Bingo() {
		setTitle("Bingo");
		//array de numeros generados aleatoria mente que tendra un numero para cada boton del carton.
		int numeros[][]= new int [5][5];
		//array de botones para meter todos los botones de todo el carton
		JButton botones[][]=new JButton[5][5];
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 704, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		//llamo a la funcion casillaAletorio
		casillaAleatorio(numeros);
		
		JPanel panel = new JPanel();
		panel.setBounds(166, 46, 362, 253);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 5, 5, 0));
		//inicilizo los contadores
		contBola = 0;
		contFila = 0;
		contColumna=0;
		//label que simula ser una bola de bingo que pone numeros aleatorios cada vez que das al boton siguiente bola.
		JLabel lblBola = new JLabel("");
		lblBola.setHorizontalAlignment(SwingConstants.CENTER);
		lblBola.setBackground(new Color(255, 255, 128));
		lblBola.setBounds(289, 332, 125, 53);
		lblBola.setOpaque(true);
		contentPane.add(lblBola);
		/*Botones que imitan el carton de un bingo que cuando das al boton mirara si el numero que estas pulsado
		 * es igual a la bola se marcara en rojo.
		 * el boton se mete en un array de botones y el nombre del boton que aprece en pantalla
		 * es un numero aleatorio que esta guardado en un array de numeros aleatorios
		 */
		JButton btnF1C1 = new JButton(""+numeros[0][0]);
		botones[0][0]= btnF1C1;
		btnF1C1.setBackground(new Color(255, 255, 255));
		btnF1C1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF1C1.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF1C1.setBackground(Color.RED);
				}
			}
		});
		
		panel.add(btnF1C1);
		
		JButton btnF1C2 = new JButton(""+numeros[0][1]);
		botones[0][1]= btnF1C2;
		btnF1C2.setBackground(new Color(255, 255, 255));
		btnF1C2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF1C2.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF1C2.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF1C2);
		
		JButton btnF1C3 = new JButton(""+numeros[0][2]);
		botones[0][2]= btnF1C3;
		btnF1C3.setBackground(new Color(255, 255, 255));
		btnF1C3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF1C3.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF1C3.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF1C3);
		
		JButton btnF1C4 = new JButton(""+numeros[0][3]);
		botones[0][3]= btnF1C4;
		btnF1C4.setBackground(new Color(255, 255, 255));
		btnF1C4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF1C4.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF1C4.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF1C4);
		
		JButton btnF1C5 = new JButton(""+numeros[0][4]);
		botones[0][4]= btnF1C5;
		btnF1C5.setBackground(new Color(255, 255, 255));
		btnF1C5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF1C5.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF1C5.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF1C5);
		
		JButton btnF2C1 = new JButton(""+numeros[1][0]);
		botones[1][0]= btnF2C1;
		btnF2C1.setBackground(new Color(255, 255, 255));
		btnF2C1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF2C1.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF2C1.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF2C1);
		
		JButton btnF2C2 = new JButton(""+numeros[1][1]);
		botones[1][1]= btnF2C2;
		btnF2C2.setBackground(new Color(255, 255, 255));
		btnF2C2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF2C2.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF2C2.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF2C2);
		
		JButton btnF2C3 = new JButton(""+numeros[1][2]);
		botones[1][2]= btnF2C3;
		btnF2C3.setBackground(new Color(255, 255, 255));
		btnF2C3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF2C3.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF2C3.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF2C3);
		
		JButton btnF2C4 = new JButton(""+numeros[1][3]);
		botones[1][3]= btnF2C4;
		btnF2C4.setBackground(new Color(255, 255, 255));
		btnF2C4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF2C4.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF2C4.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF2C4);
		
		JButton btnF2C5 = new JButton(""+numeros[1][4]);
		botones[1][4]= btnF2C5;
		btnF2C5.setBackground(new Color(255, 255, 255));
		btnF2C5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF2C5.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF2C5.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF2C5);
		
		JButton btnF3C1 = new JButton(""+numeros[2][0]);
		botones[2][0]= btnF3C1;
		btnF3C1.setBackground(new Color(255, 255, 255));
		btnF3C1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF3C1.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF3C1.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF3C1);
		
		JButton btnF3C2 = new JButton(""+numeros[2][1]);
		botones[2][1]= btnF3C2;
		btnF3C2.setBackground(new Color(255, 255, 255));
		btnF3C2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF3C2.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF3C2.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF3C2);
		
		JButton btnF3C3 = new JButton(""+numeros[2][2]);
		botones[2][2]= btnF3C3;
		btnF3C3.setBackground(new Color(255, 255, 255));
		btnF3C3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF3C3.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF3C3.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF3C3);
		
		JButton btnF3C4 = new JButton(""+numeros[2][3]);
		botones[2][3]= btnF3C4;
		btnF3C4.setBackground(new Color(255, 255, 255));
		btnF3C4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF3C4.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF3C4.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF3C4);
		
		JButton btnF3C5 = new JButton(""+numeros[2][4]);
		botones[2][4]= btnF3C5;
		btnF3C5.setBackground(new Color(255, 255, 255));
		btnF3C5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF3C5.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF3C5.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF3C5);
		
		JButton btnF4C1 = new JButton(""+numeros[3][0]);
		botones[3][0]= btnF4C1;
		btnF4C1.setBackground(new Color(255, 255, 255));
		btnF4C1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF4C1.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF4C1.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF4C1);
		
		JButton btnF4C2 = new JButton(""+numeros[3][1]);
		botones[3][1]= btnF4C2;
		btnF4C2.setBackground(new Color(255, 255, 255));
		btnF4C2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF4C2.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF4C2.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF4C2);
		
		JButton btnF4C3 = new JButton(""+numeros[3][2]);
		botones[3][2]= btnF4C3;
		btnF4C3.setBackground(new Color(255, 255, 255));
		btnF4C3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF4C3.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF4C3.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF4C3);
		
		
		
		JButton btnF4C4 = new JButton(""+numeros[3][3]);
		botones[3][3]= btnF4C4;
		btnF4C4.setBackground(new Color(255, 255, 255));
		btnF4C4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF4C4.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF4C4.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF4C4);
		
		JButton btnF4C5 = new JButton(""+numeros[3][4]);
		botones[3][4]= btnF4C5;
		btnF4C5.setBackground(new Color(255, 255, 255));
		btnF4C5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF4C5.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF4C5.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF4C5);
		
		JButton btnF5C1 = new JButton(""+numeros[4][0]);
		botones[4][0]= btnF5C1;
		btnF5C1.setBackground(new Color(255, 255, 255));
		btnF5C1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF5C1.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF5C1.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF5C1);
		
		JButton btnF5C2 = new JButton(""+numeros[4][1]);
		botones[4][1]= btnF5C2;
		btnF5C2.setBackground(new Color(255, 255, 255));
		btnF5C2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF5C2.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF5C2.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF5C2);
		
		JButton btnF5C3 = new JButton(""+numeros[4][2]);
		botones[4][2]= btnF5C3;
		btnF5C3.setBackground(new Color(255, 255, 255));
		btnF5C3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF5C3.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF5C3.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF5C3);
		
		JButton btnF5C4 = new JButton(""+numeros[4][3]);
		botones[4][3]= btnF5C4;
		btnF5C4.setBackground(new Color(255, 255, 255));
		btnF5C4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF5C4.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF5C4.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF5C4);
		
		JButton btnF5C5 = new JButton(""+numeros[4][4]);
		botones[4][4]= btnF5C5;
		btnF5C5.setBackground(new Color(255, 255, 255));
		btnF5C5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnF5C5.getText().equalsIgnoreCase(lblBola.getText())) {
					btnF5C5.setBackground(Color.RED);
				}
			}
		});
		panel.add(btnF5C5);
		//label que te dice si has ganado o has perdido.
		
		JLabel lblGanador = new JLabel("");
		lblGanador.setBounds(574, 131, 88, 68);
		contentPane.add(lblGanador);
		/*boton que genera un nuemro aleatorio y lo pone en lblBola que si das cien veces al boton pierdes y te genera un carton nuevo 
		 * y limpia el numero aleatorio lblBola y el boton se cambiara el nombre a empezar */ 
		JButton btnSiguienteBola = new JButton("Empezar");
		btnSiguienteBola.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (contBola==0) {
					btnSiguienteBola.setText("siguiente bola");
					lblBola.setText(""+bolaAleatorio());
					contBola++;
					lblGanador.setText("");
				}else if(contBola>=100){
					casillaAleatorio(numeros);
					lblGanador.setText("");
					
					for (int i = 0; i < botones.length; i++) {
						for (int j = 0; j < botones[i].length; j++) {
							botones[i][j].setText(""+numeros[i][j]);
							botones[i][j].setBackground(new Color(255, 255, 255));
						}
					}
					lblBola.setText("");
					contBola = 0;
					contFila = 0;
					contColumna = 0;
					lblGanador.setText("Perdedor");
					
				}else {
					lblBola.setText(""+bolaAleatorio());
					contBola++;
				}
				
			}
		});
		btnSiguienteBola.setBackground(new Color(89, 116, 190));
		btnSiguienteBola.setBounds(536, 358, 131, 39);
		contentPane.add(btnSiguienteBola);
		
		
		/* boton que analiza si hay alguna linea completa y si esta completa cambiara el color de esa linea  aun color mas oscuros
		 * si detecta que el boton es de color rojo añadira un punto al contador contColumnas.*/
		JButton btnLinea = new JButton("Linea");
		btnLinea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numerosFila;
					//for que analiza las filas
				for (int i = 0; i < numeros.length; i++) {
					
					numerosFila = i;
					for (int j = 0; j < numeros[i].length; j++) {
						if(botones[i][j].getBackground().equals(Color.RED)) {
							contColumna++;
						}
					}
					if(contColumna==5|| contColumna==10 || contColumna==15 || contColumna==20 || contColumna==25) {
						contFila++;
						for (int j = 0; j < numeros.length; j++) {
							botones[numerosFila][j].setBackground(new Color(249,13,14));
						}
					}
				}
				
				//for que analiza las columnas
				for (int i = 0; i < numeros.length; i++) {
					for (int j = 0; j < numeros[i].length; j++) {
						numerosFila=j;
						for (int k = 0; k < numeros.length; k++) {
							if(botones[k][j].getBackground().equals(Color.RED)) {
								
								contColumna++;
							}
						}
						if(contColumna==5|| contColumna==10 || contColumna==15 || contColumna==20 || contColumna==25) {
							contFila++;
							for (int k = 0; k < numeros.length; k++) {
								botones[numerosFila][j].setBackground(new Color(249,13,14));
							}
						}
						
					}
				}
			}
		});
		btnLinea.setBackground(new Color(89, 116, 190));
		btnLinea.setBounds(24, 309, 125, 39);
		contentPane.add(btnLinea);
		/*boton que reinicia el programa generando un nuevo carton poniendo lblBola y lblGanador en vacio*/
		JButton reiniciar = new JButton("reiniciar");
		reiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				casillaAleatorio(numeros);
				
				for (int i = 0; i < botones.length; i++) {
					for (int j = 0; j < botones[i].length; j++) {
						botones[i][j].setText(""+numeros[i][j]);
						botones[i][j].setBackground(new Color(255, 255, 255));
					}
				}
				lblBola.setText("");
				lblGanador.setText("");
				contBola = 0;
				contFila = 0;
				contColumna = 0;
			}
		});
		reiniciar.setBackground(new Color(89, 116, 190));
		reiniciar.setBounds(536, 309, 131, 39);
		contentPane.add(reiniciar);
		//detecta que si todos los botones estan en  rojo
		JButton btnBingo = new JButton("Bingo");
		btnBingo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(contColumna==25) {
					lblGanador.setText("Ganador");
				}
			}
		});
		btnBingo.setBackground(new Color(89, 116, 190));
		btnBingo.setBounds(24, 358, 125, 39);
		contentPane.add(btnBingo);
		// labe que indica que estas jugando al bingo
		JLabel lblBingo = new JLabel("bingo");
		lblBingo.setHorizontalAlignment(SwingConstants.CENTER);
		lblBingo.setBackground(new Color(0, 255, 0));
		lblBingo.setBounds(166, 0, 362, 48);
		lblBingo.setOpaque(true);
		contentPane.add(lblBingo);
		
		JButton volver = new JButton("<---");
		volver.setBackground(new Color(89, 116, 190));
		volver.setBounds(10, 14, 72, 39);
		contentPane.add(volver);
		
		
	}
	//funcion que genera un numero aleatorio para cada casilla que se va añadiendo al array numeros
	public static void casillaAleatorio(int numeros[][]) {
		for (int i = 0; i < numeros.length; i++) {
			for (int j = 0; j < numeros[i].length; j++) {
				numeros[i][j]= (int) Math.round(Math.random() * 100);
				
			}
		}
		
	}
	//funcion que genera un numero aletorio para la bola
	public static int bolaAleatorio() {
		int numero = 0;
		numero = (int) Math.round(Math.random() * 100);
		return numero;
				
		
	}
}

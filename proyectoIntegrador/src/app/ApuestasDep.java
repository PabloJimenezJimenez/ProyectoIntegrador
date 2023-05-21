package app;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

@SuppressWarnings("serial")
public class ApuestasDep extends JFrame {

	private JPanel contentPane;
	private Controlador controlador;
	private JTextField apuestaLocal;
	private JTextField apuestaVisitante;
	private JTextField prorroga;
	private JLabel cuotaLocal;
	private JLabel cotaVisitante;
	private JLabel cuotaProrroga;
	private double cantApuesta;
	public ApuestasDep() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 128, 128));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PARTIDO", SwingConstants.CENTER);
		lblNewLabel.setBounds(187, 41, 67, 23);
		contentPane.add(lblNewLabel);
		
		JButton btnAnt = new JButton("Anterior");
		btnAnt.setBackground(new Color(89, 116, 190));
		btnAnt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controlador.pantallaAnterior();
			}
		});
		btnAnt.setBounds(10, 11, 91, 23);
		contentPane.add(btnAnt);
		
		JLabel lblPartido = new JLabel("REAL MADRID - VALENCIA", SwingConstants.CENTER);
		lblPartido.setBounds(10, 77, 428, 14);
		contentPane.add(lblPartido);
		
		JButton btnEquipoLocal = new JButton("Local");
		btnEquipoLocal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*Pongo visible el JTextField para apuesta local
				 * y escondo los otros dos
				 */
				apuestaLocal.setVisible(true);
				prorroga.setVisible(false);
				prorroga.setText("");
				apuestaVisitante.setVisible(false);
				apuestaVisitante.setText("");
			}
		});
		btnEquipoLocal.setBackground(new Color(89, 116, 190));
		btnEquipoLocal.setBounds(64, 105, 91, 23);
		contentPane.add(btnEquipoLocal);
		
		JButton btnEquipoVisitante = new JButton("Visitante");
		btnEquipoVisitante.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*Pongo visible el JTextField para apuesta visitante
				 * y escondo los otros dos
				 */
				apuestaLocal.setVisible(false);
				apuestaLocal.setText("");
				prorroga.setVisible(false);
				prorroga.setText("");
				apuestaVisitante.setVisible(true);
			}
		});
		btnEquipoVisitante.setBackground(new Color(89, 116, 190));
		btnEquipoVisitante.setBounds(270, 105, 91, 23);
		contentPane.add(btnEquipoVisitante);
		
		JButton btnProrroga = new JButton("Prorroga");
		btnProrroga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*Pongo visible el JTextField para prorroga
				 * y escondo los otros dos
				 */
				apuestaLocal.setVisible(false);
				apuestaLocal.setText("");
				prorroga.setVisible(true);
				apuestaVisitante.setVisible(false);
				apuestaVisitante.setText("");
			}
		});
		btnProrroga.setBackground(new Color(89, 116, 190));
		btnProrroga.setBounds(10, 193, 418, 40);
		contentPane.add(btnProrroga);
		
		apuestaLocal = new JTextField();
		apuestaLocal.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if((e.getKeyChar()>57 || e.getKeyChar()<48)&& e.getKeyChar() !=8) {
					String mod= apuestaLocal.getText();
					mod= mod.substring(0,mod.length()-1);
					apuestaLocal.setText(mod);
				}
			}
		});
		apuestaLocal.setBounds(64, 159, 91, 23);
		apuestaLocal.setVisible(false);
		contentPane.add(apuestaLocal);
		apuestaLocal.setColumns(10);
		
		apuestaVisitante = new JTextField();
		apuestaVisitante.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//Condicional para que solo se metan numeros
				if((e.getKeyChar()>57 || e.getKeyChar()<48)&& e.getKeyChar() !=8) {
					String mod= apuestaVisitante.getText();
					mod= mod.substring(0,mod.length()-1);
					apuestaVisitante.setText(mod);
				}
			}
		});
		apuestaVisitante.setColumns(10);
		apuestaVisitante.setBounds(270, 159, 91, 23);
		apuestaVisitante.setVisible(false);
		contentPane.add(apuestaVisitante);
		
		prorroga = new JTextField();
		prorroga.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				//Condicional para que solo se metan numeros
				if((e.getKeyChar()>57 || e.getKeyChar()<48)&& e.getKeyChar() !=8) {
					String mod= prorroga.getText();
					mod= mod.substring(0,mod.length()-1);
					prorroga.setText(mod);
				}
			}
		});
		prorroga.setBounds(173, 234, 106, 20);
		prorroga.setVisible(false);
		contentPane.add(prorroga);
		prorroga.setColumns(10);
		
		JButton btnGuardarApuesta = new JButton("Guardar");
		btnGuardarApuesta.setBackground(new Color(89, 116, 190));
		btnGuardarApuesta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Condicional para saber en que JTextField está introducida la cantidad 
				if(!apuestaLocal.getText().equals("")) {
					//Cantidad apostada
					double cantidad= Double.parseDouble(apuestaLocal.getText());
					//Cuota
					double cuota= Double.parseDouble(cuotaLocal.getText());
					cantApuesta= cantidad*cuota;
					double salida= Math.round(cantApuesta*100);
					cantApuesta=salida/100;
					System.out.println(cantApuesta);
					/*Posteriormente introducire la variable cantApuesta en la base de datos*/
					apuestaLocal.setText("");
					//Vuelvo a la pantalla anterior
					controlador.pantallaAnterior();
					
				}else if(!apuestaVisitante.getText().equals("")) {
					double cantidad= Double.parseDouble(apuestaVisitante.getText());
					double cuota= Double.parseDouble(cotaVisitante.getText());
					cantApuesta= cantidad*cuota;
					double salida= Math.round(cantApuesta*100);
					cantApuesta=salida/100;
					System.out.println(cantApuesta);
					apuestaVisitante.setText("");
					controlador.pantallaAnterior();
					
				}else if(!prorroga.getText().equals("")) {
					double cantidad= Double.parseDouble(prorroga.getText());
					double cuota= Double.parseDouble(cuotaProrroga.getText());
					cantApuesta= cantidad*cuota;
					double salida= Math.round(cantApuesta*100);
					cantApuesta=salida/100;
					System.out.println(cantApuesta);
					prorroga.setText("");
					controlador.pantallaAnterior();
					
				}
			}
		});
		btnGuardarApuesta.setBounds(321, 11, 91, 23);
		contentPane.add(btnGuardarApuesta);
		
		cuotaLocal = new JLabel("2.10", SwingConstants.CENTER);
		cuotaLocal.setForeground(new Color(255, 255, 255));
		cuotaLocal.setBounds(84, 139, 48, 14);
		contentPane.add(cuotaLocal);
		
		cotaVisitante = new JLabel("2.30", SwingConstants.CENTER);
		cotaVisitante.setForeground(new Color(255, 255, 255));
		cotaVisitante.setBounds(294, 139, 48, 14);
		contentPane.add(cotaVisitante);
		
		cuotaProrroga = new JLabel("3.20");
		cuotaProrroga.setForeground(new Color(255, 255, 255));
		cuotaProrroga.setBounds(114, 237, 48, 14);
		contentPane.add(cuotaProrroga);
		
		
	}
	
	//Setter del controlador
	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}
	
	
}

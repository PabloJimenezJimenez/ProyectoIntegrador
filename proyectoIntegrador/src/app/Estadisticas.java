package app;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import app.PantallaEstadisticas.ResultSetModeloTabla;

public class Estadisticas extends JFrame{
	
	private JComboBox nombreTablas;
	private JTextField busqueda;
	private JButton ejecutar;
	private Connection miConexion;
	private ResultSet rs;
	private DatabaseMetaData datosBBDD;
	private ResultSetModeloTabla modelo;
	private Statement st;
	private Controlador miControlador;
	private JButton btnAtras;

	public void setMiControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public Estadisticas() {
		
		setTitle("DAtos tablas BBDD");
		setBounds(300,300,800,400);
		BorderLayout milayout = new BorderLayout();
		getContentPane().setLayout(milayout);
		
		busqueda = new JTextField(20);
		JPanel pnlNorte = new JPanel();
		ejecutar = new JButton("Buscar");
		
		nombreTablas = new JComboBox();
				
		//conexion
		
		try {
			miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/nba","root","");
			
			datosBBDD = miConexion.getMetaData();
			rs = datosBBDD.getTables("nba", null, null, null);
			
			
			
			while(rs.next()) {
				nombreTablas.addItem(rs.getString("TABLE_NAME"));
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
				
		nombreTablas.addActionListener(new ActionListener() {

			JTable tabla = new JTable(modelo);
			JScrollPane scroll = new JScrollPane(tabla);
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String tablaSeleccionada = (String)nombreTablas.getSelectedItem();
				String sql="SELECT * FROM " + tablaSeleccionada;
				
				try {
					st = miConexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					rs = st.executeQuery(sql);
					
					
					//modelo = new ResultSetModeloTabla(rs);
					//modelo = new ResultSetModeloTabla(rs);
					tabla.setModel(modelo);
					
					
					
					getContentPane().add(scroll, BorderLayout.CENTER);
					validate(); //para que pinte la tabla
					
					
					
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		ejecutar.addActionListener(new ActionListener() {

			JTable tabla = new JTable(modelo);
			JScrollPane scroll = new JScrollPane(tabla);
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String tablaSeleccionada = (String)nombreTablas.getSelectedItem();
				String sql="SELECT * FROM " + tablaSeleccionada;
				
				String filtro = busqueda.getText();
				System.out.println(filtro);
				
				if(tablaSeleccionada.equals("estadisticas")) {
					sql = "SELECT * FROM estadisticas where temporada= '" + filtro +"'";
				}else if(tablaSeleccionada.equals("equipos")){
					sql="SELECT * FROM equipos group by nombre";
				}else if(tablaSeleccionada.equals("jugadores")){
					if(busqueda.getText().equals("")) {
						sql= "SELECT * FROM " + tablaSeleccionada;
					}else {
						
						sql = "SELECT * FROM  jugadores where Nombre_equipo= '" + filtro +"'";
					}
				}else if(tablaSeleccionada.equals("partidos")) {
					if(filtro.equals("99/00")) {
						sql= "SELECT * FROM " + tablaSeleccionada;
					}else
						sql="SELECT * FROM estadisticas where temporada= '" + filtro +"'";
				}
				else 
					sql= "SELECT * FROM " + tablaSeleccionada;
				
				
				
				
				try {
					st = miConexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					rs = st.executeQuery(sql);
					
					//modelo = new ResultSetModeloTabla(rs);
					tabla.setModel(modelo);
					
					
					
					getContentPane().add(scroll, BorderLayout.CENTER);
					validate(); //para que pinte la tabla
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				
			}
			
		});
		
		btnAtras = new JButton("Atrás");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.volverEstadisticas();
			}
		});
		pnlNorte.add(btnAtras);
		
		
		pnlNorte.add(nombreTablas);
		pnlNorte.add(busqueda);
		pnlNorte.add(ejecutar);
		getContentPane().add(pnlNorte, BorderLayout.NORTH);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
	


package app;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import java.awt.Color;

public class Estadisticas extends JFrame{

	private JComboBox nombreTablas;
	private JTextField busqueda;
	private JButton ejecutar;
	private Connection miConexion;
	private ResultSet rs;
	private DatabaseMetaData datosBBDD;
	private ResultSetModeloTabla modelo;
	private Statement stBusqueda;
	private Controlador miControlador;
	private JButton btnAtras;
	
	public void setMiControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public Estadisticas() {
		getContentPane().setBackground(new Color(128, 128, 128));
		

		setTitle("ESTADISTICAS");
		setBounds(300,300,800,400);
		BorderLayout milayout = new BorderLayout();
		getContentPane().setLayout(milayout);
		
		busqueda = new JTextField(20);
		JPanel pnlNorte = new JPanel();
		pnlNorte.setBackground(new Color(128, 128, 128));
		ejecutar = new JButton("Buscar");
		ejecutar.setBackground(new Color(89, 116, 190));
		
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
				
		
		ejecutar.addActionListener(new ActionListener() {

			JTable tabla = new JTable(modelo);
			JScrollPane scroll = new JScrollPane(tabla);
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String tablaSeleccionada = (String)nombreTablas.getSelectedItem();
				String sql="";	
				String filtro = busqueda.getText();
			
				
				if(tablaSeleccionada.equals("estadisticas")) {						
					if(filtro.equals("99/00") || filtro.isEmpty()) {
						sql= "SELECT * FROM " + tablaSeleccionada;
					}else
						sql="SELECT * FROM estadisticas where temporada = '" +  filtro + "'";
					
				}else if(tablaSeleccionada.equals("equipos")){
					if(!filtro.isEmpty())
						sql="SELECT * FROM equipos where nombre = '" + filtro+"'";
					else
						sql= "SELECT * FROM " + tablaSeleccionada;
				}else if(tablaSeleccionada.equals("jugadores")){
					if(busqueda.getText().equals("")) {
						sql= "SELECT * FROM " + tablaSeleccionada;
					}else {
						
						sql = "SELECT * FROM  jugadores where Nombre_equipo= '" + filtro +"'";
					}
				}else if(tablaSeleccionada.equals("partidos")) {
					if(!filtro.isEmpty())
						sql="SELECT * FROM partidos where temporada = '98/99' and equipo_local = '" + filtro +"'";
					if(filtro.equals("99/00") || filtro.isEmpty()) {
						sql= "SELECT * FROM " + tablaSeleccionada;
					}
				}
				else
					sql= "SELECT * FROM " + tablaSeleccionada;
				
				
				
				
				try {
					stBusqueda = miConexion.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
					rs = stBusqueda.executeQuery(sql);
					
					modelo = new ResultSetModeloTabla(rs);
					tabla.setModel(modelo);
					
				
					
					getContentPane().add(scroll, BorderLayout.CENTER);
					validate(); //para que pinte la tabla
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				busqueda.setText("");
				
			}
			
		});
		
		
		pnlNorte.add(nombreTablas);
		pnlNorte.add(busqueda);
		pnlNorte.add(ejecutar);
		getContentPane().add(pnlNorte, BorderLayout.NORTH);
		
		btnAtras = new JButton("Atrás");
		btnAtras.setBackground(new Color(89, 116, 190));
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				miControlador.volverEstadisticas();
			}
		});
		getContentPane().add(btnAtras, BorderLayout.SOUTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}
	
	
}

class ResultSetModeloTabla extends AbstractTableModel{

	private ResultSet rsRegistros;
	private ResultSetMetaData rsMeta;
	
	public ResultSetModeloTabla(ResultSet rs) {
		this.rsRegistros=rs;
		
		try {
			this.rsMeta = rsRegistros.getMetaData();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	
	@Override
	public int getRowCount() {

		try {
			rsRegistros.last();
			return rsRegistros.getRow();
			
			
		} catch (SQLException e) {
		
			e.printStackTrace();
			return 0;
		}
		
	}

	@Override
	public int getColumnCount() {
		
		try {
			return rsMeta.getColumnCount();
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		try {
			rsRegistros.absolute(rowIndex+1);
			return rsRegistros.getObject(columnIndex+1);
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		
	}

	@Override

	public String getColumnName(int column) {

	try {

	return rsMeta.getColumnName(column+1);

	} catch (SQLException e) {

		e.printStackTrace();

	return null;

		}
	
	}
	
}


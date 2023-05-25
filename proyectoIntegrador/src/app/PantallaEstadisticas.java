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
import java.awt.Font;

public  class PantallaEstadisticas {

	private Controlador miControlador;
	
	/*public static void main(String[] args) {
		
		MarcoDatosBBDD marco = new MarcoDatosBBDD();
		marco.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		
	}*/




class MarcoDatosBBDD extends JFrame{
	
	private JComboBox nombreTablas;
	private JTextField busqueda;
	private JButton ejecutar;
	private Connection miConexion;
	private ResultSet rs;
	private DatabaseMetaData datosBBDD;
	private ResultSetModeloTabla modelo;
	private Statement st;
	
	
	public MarcoDatosBBDD() {
		setTitle("DAtos tablas BBDD");
		setBounds(300,300,800,400);
		BorderLayout milayout = new BorderLayout();
		setLayout(milayout);
		
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
					
					
					modelo = new ResultSetModeloTabla(rs);
					tabla.setModel(modelo);
					
					
					
					add(scroll, BorderLayout.CENTER);
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
					
					modelo = new ResultSetModeloTabla(rs);
					tabla.setModel(modelo);
					
					
					
					add(scroll, BorderLayout.CENTER);
					validate(); //para que pinte la tabla
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
				
				
			}
			
		});
		
		
		pnlNorte.add(nombreTablas);
		pnlNorte.add(busqueda);
		pnlNorte.add(ejecutar);
		add(pnlNorte, BorderLayout.NORTH);
		setVisible(true);
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

}
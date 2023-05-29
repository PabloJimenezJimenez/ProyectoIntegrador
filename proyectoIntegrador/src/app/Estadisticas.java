
package app;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;
import java.awt.Color;

@SuppressWarnings("serial")
public class Estadisticas extends JFrame {

	@SuppressWarnings("rawtypes")
	private JComboBox nombreTablas;
	private JTextField busqueda;
	private JButton ejecutar;
	private ResultSetModeloTabla modeloTabla;
	private Controlador miControlador;
	private JButton btnAtras;
	private Modelo miModelo;
	private ArrayList<String>opcCombo;

	@SuppressWarnings("rawtypes")
	public Estadisticas() {
		getContentPane().setBackground(new Color(128, 128, 128));

		setTitle("ESTADISTICAS");
		setBounds(300, 300, 800, 400);
		BorderLayout milayout = new BorderLayout();
		getContentPane().setLayout(milayout);

		busqueda = new JTextField(20);
		JPanel pnlNorte = new JPanel();
		pnlNorte.setBackground(new Color(128, 128, 128));
		ejecutar = new JButton("Buscar");
		ejecutar.setBackground(new Color(89, 116, 190));

		nombreTablas = new JComboBox();
		ejecutar.addActionListener(new ActionListener() {

			JTable tabla = new JTable(modeloTabla);
			JScrollPane scroll = new JScrollPane(tabla);

			@Override
			public void actionPerformed(ActionEvent e) {

				String tablaSeleccionada = (String) nombreTablas.getSelectedItem();
				String filtro = busqueda.getText();
				// LLamo al metodo para hacer las busquedas
				miModelo.buscarTablas(tablaSeleccionada, filtro);

				modeloTabla = new ResultSetModeloTabla(miModelo.getTablas());
				tabla.setModel(modeloTabla);

				getContentPane().add(scroll, BorderLayout.CENTER);
				validate(); // para que pinte la tabla

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
	
	public void setMiControlador(Controlador miControlador) {
		this.miControlador = miControlador;
	}

	public void setMiModelo(Modelo miModelo) {
		this.miModelo = miModelo;
	}

	public void setNombreTablas(@SuppressWarnings("rawtypes") JComboBox nombreTablas) {
		this.nombreTablas = nombreTablas;
	}
	
	public void setOpcCombo(ArrayList<String> opcCombo) {
		this.opcCombo = opcCombo;
	}

	@SuppressWarnings("unchecked")
	public void rellenarCombo() {
		for(int i=0;i<opcCombo.size();i++) {
			nombreTablas.addItem(opcCombo.get(i));
		}
		
	}

}

@SuppressWarnings("serial")
class ResultSetModeloTabla extends AbstractTableModel {

	private ResultSet rsRegistros;
	private ResultSetMetaData rsMeta;

	public ResultSetModeloTabla(ResultSet rs) {
		this.rsRegistros = rs;

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
			rsRegistros.absolute(rowIndex + 1);
			return rsRegistros.getObject(columnIndex + 1);
		} catch (SQLException e) {

			e.printStackTrace();
			return null;
		}

	}

	@Override

	public String getColumnName(int column) {

		try {

			return rsMeta.getColumnName(column + 1);

		} catch (SQLException e) {

			e.printStackTrace();

			return null;

		}

	}

}

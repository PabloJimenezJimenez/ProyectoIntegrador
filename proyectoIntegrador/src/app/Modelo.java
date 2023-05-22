package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Modelo {

	@SuppressWarnings("unused")
	private Login miVista;
	private String usr;
	private String pswd;
	private String resultado;
	private int fallos;
	private Connection conexionBBDD;

	public Modelo() {
		this.usr="";
		this.pswd="";
		this.fallos = 0;
		try {
			this.conexionBBDD = DriverManager.getConnection("jdbc:mysql://localhost:3306/nba", "root", "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void setMiVista(Login miVista) {
		this.miVista = miVista;
	}

	public void login(String usr, String contraseña) {
			try {
				// Hago un prepared statement para saber si esta o no en la base de datos
				PreparedStatement ps = conexionBBDD
						.prepareStatement("select nombre,passwd from usuario where nombre=? AND passwd=?");
				ps.setString(1, usr);// Le paso el nombre del usuario que ha introducido
				ps.setString(2, contraseña);// Le paso la contraseña que ha introducido
				ResultSet rs = ps.executeQuery();
				//Guardo el nombre del usuario y su contraseña en dos variables. 
				while (rs.next()) {
					this.usr = rs.getString("nombre");
					this.pswd = rs.getString("passwd");
				}
				if(this.usr.equals("") && this.pswd.equals("")) {
					fallos++;
					if (fallos == 3) {
						resultado = "Cerrar";
					} else
						resultado = "ERROR";
				}else {
					resultado = "correcto";
				}
				miVista.actualizar();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		

	}

	public String getResultado() {
		return this.resultado;
	}

	public void setUsr(String usr) {
		try {
			PreparedStatement cambioUsr=conexionBBDD.prepareStatement("update usuarios set nombre=? where nombre=?");
			cambioUsr.setString(1, usr);//Nuevo nombre de usuario
			cambioUsr.setString(2, this.usr);//Antiguo nombre
			this.usr = usr;//Modifico el valor de usr.
			cambioUsr.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void setContraseña(String pswd) {
		try {
			PreparedStatement cambioPswd=conexionBBDD.prepareStatement("update usuarios set passwd=? where passwd=?");
			cambioPswd.setString(1, pswd);//Nueva contraseña de usuario
			cambioPswd.setString(2, this.pswd);//Antigua contraseña
			this.pswd = pswd;//Modifico el valor de usr.
			cambioPswd.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


public class Modelo {

	private Login miVista;
	private ApuestasDep apuestasDep;
	private PantallaApuestas pantallaApuestas;
	private String usr;
	private String pswd;
	private String resultado;
	private int fallos;
	private Connection conexionBBDD;

	public Modelo() {
		this.usr = "";
		this.pswd = "";
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
	
	public void setApuestasDep(ApuestasDep apuestasDep) {
		this.apuestasDep = apuestasDep;
	}

	public void setPantallaApuestas(PantallaApuestas pantallaApuestas) {
		this.pantallaApuestas = pantallaApuestas;
	}

	public void login(String usr, String contraseña) {
		try {
			// Hago un prepared statement para saber si esta o no en la base de datos
			PreparedStatement ps = conexionBBDD
					.prepareStatement("select nombre,passwd from usuario where nombre=? AND passwd=?");
			ps.setString(1, usr);// Le paso el nombre del usuario que ha introducido
			ps.setString(2, contraseña);// Le paso la contraseña que ha introducido
			ResultSet rs = ps.executeQuery();
			// Guardo el nombre del usuario y su contraseña en dos variables.
			while (rs.next()) {
				this.usr = rs.getString("nombre");
				this.pswd = rs.getString("passwd");
			}
			if (this.usr.equals("") && this.pswd.equals("")) {
				fallos++;
				if (fallos == 3) {
					resultado = "Cerrar";
				} else
					resultado = "ERROR";
			} else {
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
			PreparedStatement cambioUsr = conexionBBDD.prepareStatement("update usuarios set nombre=? where nombre=?");
			cambioUsr.setString(1, usr);// Nuevo nombre de usuario
			cambioUsr.setString(2, this.usr);// Antiguo nombre
			this.usr = usr;// Modifico el valor de usr.
			cambioUsr.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setContraseña(String pswd) {
		try {
			PreparedStatement cambioPswd = conexionBBDD.prepareStatement("update usuarios set passwd=? where passwd=?");
			cambioPswd.setString(1, pswd);// Nueva contraseña de usuario
			cambioPswd.setString(2, this.pswd);// Antigua contraseña
			this.pswd = pswd;// Modifico el valor de usr.
			cambioPswd.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void generarPartido() {
		HashMap<Integer, String> partidos= new HashMap<>();
		for (int i = 1; i <= 7; i++) {
			int local = (int) (Math.random() * 30 + 1);
			int partido = (local - 1) * 58 + (int) (Math.random() * 58 + 1);
			String partido_guardado="";
			try {
				String eqLocal="";
				String eqVisitante="";
				PreparedStatement equiposPartido = conexionBBDD
						.prepareStatement("Select equipo_local,equipo_visitante from partidos where codigo=?");
				equiposPartido.setInt(1, partido);
				ResultSet rs = equiposPartido.executeQuery();
				while (rs.next()) {
					eqLocal=rs.getString("equipo_local");
					eqVisitante=rs.getString("equipo_visitante");
				}
				partido_guardado=eqLocal+"-"+eqVisitante;
				partidos.put(i, partido_guardado);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		pantallaApuestas.generarJornada(partidos);
		
	}

	public void generarCuota(String eqLocal, String eqVisitante) {
		try {
			int mediaLocal=0;
			int mediaVisitante=0;
			int cuotaLocal=0;
			int cuotaVisitante=0;
			int cuotaProrroga=0;
			int cuotaProrogaVis=0;
			//Preparo dos sentencias para saber la media de puntos de ambos equipos
			PreparedStatement mediaEqLocal= conexionBBDD.prepareStatement("SELECT round(AVG(puntos_local),0) FROM partidos WHERE temporada='99/00' AND equipo_local=?;");
			PreparedStatement mediaEqVisitante=conexionBBDD.prepareStatement("SELECT round(AVG(puntos_visitante),0) FROM partidos WHERE temporada='99/00' AND equipo_visitante=?;");
			mediaEqLocal.setString(1, eqLocal);
			mediaEqVisitante.setString(1, eqVisitante);
			ResultSet local= mediaEqLocal.executeQuery();
			ResultSet vis= mediaEqVisitante.executeQuery();
			while(local.next()) {
				mediaLocal=local.getInt("round(AVG(puntos_local),0)");
			}
			while(vis.next()) {
				mediaVisitante=vis.getInt("round(AVG(puntos_visitante),0)");
			}
			if(mediaLocal>mediaVisitante || mediaLocal==mediaVisitante) {
				//Sentencia preparada para saber el numero de partidos que ha superado el valor objetivo el equipo local
				PreparedStatement supMedLocal=conexionBBDD.prepareStatement("SELECT COUNT(*) FROM partidos WHERE temporada='99/00' and puntos_local>? AND equipo_local=?;");
				//Sentencia preparada para saber el numero de partidos que ha superado el valor objetivo el equipo visitante
				PreparedStatement supMedVisitante= conexionBBDD.prepareStatement("SELECT COUNT(*) FROM partidos WHERE temporada='99/00' and puntos_visitante>? AND equipo_visitante=?;");
				//Sentencia preparada para saber el numero de partidos que ha igualado el valor objetivo el equipo local
				PreparedStatement igualMedLocal= conexionBBDD.prepareStatement("SELECT COUNT(*) FROM partidos WHERE temporada='99/00' and puntos_local=? AND equipo_local=?;");
				//Sentencia preparada para saber el numero de partidos que ha igualado el valor objetivo el equipo visitante
				PreparedStatement igualMedVis=conexionBBDD.prepareStatement("SELECT COUNT(*) FROM partidos WHERE temporada='99/00' and puntos_visitante=? AND equipo_visitante=?;");
				//Doy valor a las incognitas
				supMedLocal.setInt(1, mediaLocal);
				supMedLocal.setString(2, eqLocal);
				
				supMedVisitante.setInt(1, mediaLocal);
				supMedVisitante.setString(2, eqVisitante);
				
				igualMedLocal.setInt(1, mediaLocal);
				igualMedLocal.setString(2, eqLocal);
				
				igualMedVis.setInt(1,mediaLocal);
				igualMedVis.setString(2, eqVisitante);
				
				//ResultSet
				ResultSet supLocal=supMedLocal.executeQuery();
				ResultSet supVis=supMedVisitante.executeQuery();
				ResultSet igLocal=igualMedLocal.executeQuery();
				ResultSet igVis=igualMedVis.executeQuery();
				
				//Guardo el resultado de los resultset en variables
				while(supLocal.next()) {
					cuotaLocal=supLocal.getInt("COUNT(*)");
				}
				while(supVis.next()) {
					cuotaVisitante=supVis.getInt("COUNT(*)");
				}
				while(igLocal.next()) {
					cuotaProrroga=igLocal.getInt("COUNT(*)");
				}
				while(igVis.next()) {
					cuotaProrogaVis=igLocal.getInt("COUNT(*)");
				}
				cuotaProrroga+=cuotaProrogaVis;
				cuotaLocal=1/(cuotaLocal/58);
				cuotaVisitante=1/(cuotaVisitante/58);
				cuotaProrroga=1/(cuotaProrroga/58);
				apuestasDep.setCuotaLocal(Integer.toString(cuotaLocal));
				apuestasDep.setCuotaProrroga(Integer.toString(cuotaProrroga));
				apuestasDep.setCuotaVisitante(Integer.toString(cuotaVisitante));
			}else if(mediaVisitante > mediaLocal) {
				//Sentencia preparada para saber el numero de partidos que ha superado el valor objetivo el equipo local
				PreparedStatement supMedLocal=conexionBBDD.prepareStatement("SELECT COUNT(*) FROM partidos WHERE temporada='99/00' and puntos_local>? AND equipo_local=?;");
				//Sentencia preparada para saber el numero de partidos que ha superado el valor objetivo el equipo visitante
				PreparedStatement supMedVisitante= conexionBBDD.prepareStatement("SELECT COUNT(*) FROM partidos WHERE temporada='99/00' and puntos_visitante>? AND equipo_visitante=?;");
				//Sentencia preparada para saber el numero de partidos que ha igualado el valor objetivo el equipo local
				PreparedStatement igualMedLocal= conexionBBDD.prepareStatement("SELECT COUNT(*) FROM partidos WHERE temporada='99/00' and puntos_local=? AND equipo_local=?;");
				//Sentencia preparada para saber el numero de partidos que ha igualado el valor objetivo el equipo visitante
				PreparedStatement igualMedVis=conexionBBDD.prepareStatement("SELECT COUNT(*) FROM partidos WHERE temporada='99/00' and puntos_visitante=? AND equipo_visitante=?;");
				//Doy valor a las incognitas
				supMedLocal.setInt(1, mediaVisitante);
				supMedLocal.setString(2, eqLocal);
				
				supMedVisitante.setInt(1, mediaVisitante);
				supMedVisitante.setString(2, eqVisitante);
				
				igualMedLocal.setInt(1, mediaVisitante);
				igualMedLocal.setString(2, eqLocal);
				
				igualMedVis.setInt(1,mediaVisitante);
				igualMedVis.setString(2, eqVisitante);
				
				//ResultSet
				ResultSet supLocal=supMedLocal.executeQuery();
				ResultSet supVis=supMedVisitante.executeQuery();
				ResultSet igLocal=igualMedLocal.executeQuery();
				ResultSet igVis=igualMedVis.executeQuery();
				
				//Guardo el resultado de los resultset en variables
				while(supLocal.next()) {
					cuotaLocal=supLocal.getInt("COUNT(*)");
				}
				while(supVis.next()) {
					cuotaVisitante=supVis.getInt("COUNT(*)");
				}
				while(igLocal.next()) {
					cuotaProrroga+=igLocal.getInt("COUNT(*)");
				}
				while(igVis.next()) {
					cuotaProrroga+=igLocal.getInt("COUNT(*)");
				}
				
				cuotaLocal=1/(cuotaLocal/58);
				cuotaVisitante=1/(cuotaVisitante/58);
				cuotaProrroga=1/(cuotaProrroga/58);
				apuestasDep.setCuotaLocal(Integer.toString(cuotaLocal));
				apuestasDep.setCuotaProrroga(Integer.toString(cuotaProrroga));
				apuestasDep.setCuotaVisitante(Integer.toString(cuotaVisitante));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

}

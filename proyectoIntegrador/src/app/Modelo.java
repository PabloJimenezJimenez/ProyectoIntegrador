package app;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Modelo {

	private Login miVista;
	private ApuestasDep apuestasDep;
	private PantallaApuestas pantallaApuestas;
	private Bienvenida bienvenida;
	private Ruleta ruleta;
	private Estadisticas estadisticas;
	private String usr;
	private String pswd;
	private String resultado;
	private int fallos;
	private Connection conexionBBDD;
	private double saldo;
	private int id_usuario;
	private int id_partido;
	private int puntLocal;
	private int puntVis;
	private DatabaseMetaData datosBBDD;
	private ResultSet tablas;

	public Modelo() {
		this.usr = "";
		this.pswd = "";
		this.fallos = 0;
		try {
			this.conexionBBDD = DriverManager.getConnection("jdbc:mysql://localhost:3306/nba", "root", "");
			this.datosBBDD = conexionBBDD.getMetaData();
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

	public void setBienvenida(Bienvenida bienvenida) {
		this.bienvenida = bienvenida;
	}

	public void setRuleta(Ruleta ruleta) {
		this.ruleta = ruleta;
	}
	
	public void setEstadisticas(Estadisticas estadisticas) {
		this.estadisticas = estadisticas;
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
				// Llamo al procedimiento para obtener el saldo del cliente
				obtenerSaldo(usr);
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
			PreparedStatement cambioUsr = conexionBBDD.prepareStatement("update usuario set nombre=? where nombre=?");
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
			PreparedStatement cambioPswd = conexionBBDD.prepareStatement("update usuario set passwd=? where passwd=?");
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
		HashMap<Integer, String> partidos = new HashMap<>();
		for (int i = 1; i <= 7; i++) {
			int local = (int) (Math.random() * 30 + 1);
			int partido = (local - 1) * 58 + (int) (Math.random() * 58 + 1);
			String partido_guardado = "";
			try {
				String eqLocal = "";
				String eqVisitante = "";
				PreparedStatement equiposPartido = conexionBBDD
						.prepareStatement("Select equipo_local,equipo_visitante from partidos where codigo=?");
				equiposPartido.setInt(1, partido);
				ResultSet rs = equiposPartido.executeQuery();
				while (rs.next()) {
					eqLocal = rs.getString("equipo_local");
					eqVisitante = rs.getString("equipo_visitante");
				}
				partido_guardado = eqLocal + "-" + eqVisitante;
				partidos.put(i, partido_guardado);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		pantallaApuestas.generarJornada(partidos);

	}

	public void generarCuota(String eqLocal, String eqVisitante) {
		try {
			int mediaLocal = 0;
			int mediaVisitante = 0;
			double cuotaLocal = 0;
			double cuotaVisitante = 0;
			double cuotaProrroga = 0;
			double cuotaProrogaVis = 0;
			// Preparo dos sentencias para saber la media de puntos de ambos equipos
			PreparedStatement mediaEqLocal = conexionBBDD.prepareStatement(
					"SELECT round(AVG(puntos_local),0) FROM partidos WHERE temporada='99/00' AND equipo_local=?;");
			PreparedStatement mediaEqVisitante = conexionBBDD.prepareStatement(
					"SELECT round(AVG(puntos_visitante),0) FROM partidos WHERE temporada='99/00' AND equipo_visitante=?;");
			mediaEqLocal.setString(1, eqLocal);
			mediaEqVisitante.setString(1, eqVisitante);
			ResultSet local = mediaEqLocal.executeQuery();
			ResultSet vis = mediaEqVisitante.executeQuery();
			while (local.next()) {
				mediaLocal = local.getInt("round(AVG(puntos_local),0)");
			}
			while (vis.next()) {
				mediaVisitante = vis.getInt("round(AVG(puntos_visitante),0)");
			}
			if (mediaLocal > mediaVisitante || mediaLocal == mediaVisitante) {
				// Sentencia preparada para saber el numero de partidos que ha superado el valor
				// objetivo el equipo local
				PreparedStatement supMedLocal = conexionBBDD.prepareStatement(
						"SELECT COUNT(*) FROM partidos WHERE temporada='99/00' and puntos_local>? AND equipo_local=?;");
				// Sentencia preparada para saber el numero de partidos que ha superado el valor
				// objetivo el equipo visitante
				PreparedStatement supMedVisitante = conexionBBDD.prepareStatement(
						"SELECT COUNT(*) FROM partidos WHERE temporada='99/00' and puntos_visitante>? AND equipo_visitante=?;");
				// Sentencia preparada para saber el numero de partidos que ha igualado el valor
				// objetivo el equipo local
				PreparedStatement igualMedLocal = conexionBBDD.prepareStatement(
						"SELECT COUNT(*) FROM partidos WHERE temporada='99/00' and puntos_local=? AND equipo_local=?;");
				// Sentencia preparada para saber el numero de partidos que ha igualado el valor
				// objetivo el equipo visitante
				PreparedStatement igualMedVis = conexionBBDD.prepareStatement(
						"SELECT COUNT(*) FROM partidos WHERE temporada='99/00' and puntos_visitante=? AND equipo_visitante=?;");
				// Doy valor a las incognitas
				supMedLocal.setInt(1, mediaLocal);
				supMedLocal.setString(2, eqLocal);

				supMedVisitante.setInt(1, mediaLocal);
				supMedVisitante.setString(2, eqVisitante);

				igualMedLocal.setInt(1, mediaLocal);
				igualMedLocal.setString(2, eqLocal);

				igualMedVis.setInt(1, mediaLocal);
				igualMedVis.setString(2, eqVisitante);

				// ResultSet
				ResultSet supLocal = supMedLocal.executeQuery();
				ResultSet supVis = supMedVisitante.executeQuery();
				ResultSet igLocal = igualMedLocal.executeQuery();
				ResultSet igVis = igualMedVis.executeQuery();

				// Guardo el resultado de los resultset en variables
				while (supLocal.next()) {
					cuotaLocal = supLocal.getInt("COUNT(*)");
					System.out.println(cuotaLocal);
				}
				while (supVis.next()) {
					cuotaVisitante = supVis.getInt("COUNT(*)");
					System.out.println(cuotaVisitante);
				}
				while (igLocal.next()) {
					cuotaProrroga = igLocal.getInt("COUNT(*)");
					System.out.println(cuotaProrroga);
				}
				while (igVis.next()) {
					cuotaProrogaVis = igVis.getInt("COUNT(*)");
					System.out.println(cuotaProrogaVis);
				}
				cuotaProrroga += cuotaProrogaVis;
				// condicional para modificar el valor de la proroga si sale 0
				if (cuotaProrroga == 0) {
					cuotaProrroga = 0.5;
				}
				cuotaLocal = 1 / (cuotaLocal / 58);
				cuotaVisitante = 1 / (cuotaVisitante / 58);
				cuotaProrroga = 1 / (cuotaProrroga / 58);
				// Redondeo a dos decimeales
				cuotaLocal = (Math.round(cuotaLocal * 100));
				cuotaLocal /= 100;
				cuotaVisitante = (Math.round(cuotaVisitante * 100));
				cuotaVisitante /= 100;
				cuotaProrroga = Math.round(cuotaProrroga);
				// Introduzco cada cuota en su jlabel
				apuestasDep.setCuotaLocal(Double.toString(cuotaLocal));
				apuestasDep.setCuotaProrroga(Double.toString(cuotaProrroga));
				apuestasDep.setCuotaVisitante(Double.toString(cuotaVisitante));
			} else if (mediaVisitante > mediaLocal) {
				// Sentencia preparada para saber el numero de partidos que ha superado el valor
				// objetivo el equipo local
				PreparedStatement supMedLocal = conexionBBDD.prepareStatement(
						"SELECT COUNT(*) FROM partidos WHERE temporada='99/00' and puntos_local>? AND equipo_local=?;");
				// Sentencia preparada para saber el numero de partidos que ha superado el valor
				// objetivo el equipo visitante
				PreparedStatement supMedVisitante = conexionBBDD.prepareStatement(
						"SELECT COUNT(*) FROM partidos WHERE temporada='99/00' and puntos_visitante>? AND equipo_visitante=?;");
				// Sentencia preparada para saber el numero de partidos que ha igualado el valor
				// objetivo el equipo local
				PreparedStatement igualMedLocal = conexionBBDD.prepareStatement(
						"SELECT COUNT(*) FROM partidos WHERE temporada='99/00' and puntos_local=? AND equipo_local=?;");
				// Sentencia preparada para saber el numero de partidos que ha igualado el valor
				// objetivo el equipo visitante
				PreparedStatement igualMedVis = conexionBBDD.prepareStatement(
						"SELECT COUNT(*) FROM partidos WHERE temporada='99/00' and puntos_visitante=? AND equipo_visitante=?;");
				// Doy valor a las incognitas
				supMedLocal.setInt(1, mediaVisitante);
				supMedLocal.setString(2, eqLocal);

				supMedVisitante.setInt(1, mediaVisitante);
				supMedVisitante.setString(2, eqVisitante);

				igualMedLocal.setInt(1, mediaVisitante);
				igualMedLocal.setString(2, eqLocal);

				igualMedVis.setInt(1, mediaVisitante);
				igualMedVis.setString(2, eqVisitante);

				// ResultSet
				ResultSet supLocal = supMedLocal.executeQuery();
				ResultSet supVis = supMedVisitante.executeQuery();
				ResultSet igLocal = igualMedLocal.executeQuery();
				ResultSet igVis = igualMedVis.executeQuery();

				// Guardo el resultado de los resultset en variables
				while (supLocal.next()) {
					cuotaLocal = supLocal.getInt("COUNT(*)");
				}
				while (supVis.next()) {
					cuotaVisitante = supVis.getInt("COUNT(*)");
				}
				while (igLocal.next()) {
					cuotaProrroga = igLocal.getInt("COUNT(*)");
					System.out.println(cuotaProrroga);
				}
				while (igVis.next()) {
					cuotaProrogaVis = igVis.getInt("COUNT(*)");
					System.out.println(cuotaProrogaVis);
				}
				cuotaProrroga += cuotaProrogaVis;
				// condicional para modificar el valor de la proroga si sale 0
				if (cuotaProrroga == 0) {
					cuotaProrroga = 0.5;
				}
				cuotaLocal = 1 / (cuotaLocal / 58);
				cuotaVisitante = 1 / (cuotaVisitante / 58);
				cuotaProrroga = 1 / (cuotaProrroga / 58);
				// Redondeo a dos decimeales
				cuotaLocal = (Math.round(cuotaLocal * 100));
				cuotaLocal /= 100;
				cuotaVisitante = (Math.round(cuotaVisitante * 100));
				cuotaVisitante /= 100;
				cuotaProrroga = Math.round(cuotaProrroga);
				// Introduzco cada cuota en su jlabel
				apuestasDep.setCuotaLocal(Double.toString(cuotaLocal));
				apuestasDep.setCuotaProrroga(Double.toString(cuotaProrroga));
				apuestasDep.setCuotaVisitante(Double.toString(cuotaVisitante));

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void obtenerSaldo(String user) {
		try {
			PreparedStatement psSaldo = conexionBBDD.prepareStatement("SELECT saldo from usuario where nombre=?;");
			psSaldo.setString(1, user);
			ResultSet rsSaldo = psSaldo.executeQuery();
			while (rsSaldo.next()) {
				saldo = rsSaldo.getDouble("saldo");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Modifico el textField de saldo en la pantalla bienvenida
		bienvenida.setTxtSaldo("Saldo: " + saldo + " €");
		// Modifico el lbl de saldo en la pantalla de apuestasDep
		apuestasDep.setLblSaldoApuestas("Saldo: " + saldo + " €");
		// Modifico el lbl de saldo en la pantalla pantallaApuesta
		pantallaApuestas.setLblTxtSaldo("Saldo: " + saldo + " €");
		// Modifico el lbl de saldo de la ruleta
		ruleta.setLblTxtSaldo("Saldo: " + saldo + " €");
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
		// Hago una consulta para modificar el saldo en la bbdd
		try {
			PreparedStatement psModSaldo = conexionBBDD
					.prepareStatement("UPDATE usuario SET saldo = ? WHERE usuario.nombre = ?;");
			psModSaldo.setDouble(1, saldo);
			psModSaldo.setString(2, this.usr);
			psModSaldo.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public void setIdUsuario() {
		try {
			PreparedStatement psGetId = conexionBBDD.prepareStatement("SELECT id_usuario from usuario where nombre=?");
			psGetId.setString(1, usr);
			ResultSet rsGetId = psGetId.executeQuery();
			while (rsGetId.next()) {
				this.id_usuario = rsGetId.getInt("id_usuario");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setApuestaBBDD(int numUsuario, String colorUsuario, int numGanador, String colorGanador,
			double apuestaRelalizada) {
		try {
			PreparedStatement psAddApuestaCasino = conexionBBDD.prepareStatement(
					"INSERT into casino(numGanador,colorGanador,numUsuario,colorUsuario,apuestaRealizada,id_usuario) VALUES (?,?,?,?,?,?)");
			psAddApuestaCasino.setInt(1, numGanador);
			psAddApuestaCasino.setString(2, colorGanador);
			psAddApuestaCasino.setInt(3, numUsuario);
			psAddApuestaCasino.setString(4, colorUsuario);
			psAddApuestaCasino.setDouble(5, apuestaRelalizada);
			psAddApuestaCasino.setInt(6, id_usuario);
			psAddApuestaCasino.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setApuestaBBDD(String colorUsuario, int numGanador, String colorGanador, double apuestaRelalizada) {
		try {
			PreparedStatement psAddApuestaCasino = conexionBBDD.prepareStatement(
					"INSERT into casino(numGanador,colorGanador,colorUsuario,apuestaRealizada,id_usuario) VALUES (?,?,?,?,?)");
			psAddApuestaCasino.setInt(1, numGanador);
			psAddApuestaCasino.setString(2, colorGanador);
			psAddApuestaCasino.setString(3, colorUsuario);
			psAddApuestaCasino.setDouble(4, apuestaRelalizada);
			psAddApuestaCasino.setInt(5, id_usuario);
			psAddApuestaCasino.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void SetId_partido(String eqLocal, String eqVis) {
		try {
			PreparedStatement psGetId = conexionBBDD.prepareStatement(
					"SELECT codigo from partidos WHERE equipo_local=? AND equipo_visitante=? AND temporada='99/00'");
			psGetId.setString(1, eqLocal);
			psGetId.setString(2, eqVis);
			ResultSet rsGetid = psGetId.executeQuery();
			while (rsGetid.next()) {
				this.id_partido = rsGetid.getInt("codigo");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getId_partido() {
		return id_partido;
	}

	public int getPuntLocal() {
		return puntLocal;
	}

	public void setPuntLocal() {
		try {
			PreparedStatement psEqLocal = conexionBBDD
					.prepareStatement("SELECT puntos_local from partidos WHERE codigo=?;");
			psEqLocal.setInt(1, this.id_partido);
			ResultSet rs = psEqLocal.executeQuery();
			while (rs.next()) {
				this.puntLocal = rs.getInt("puntos_local");
			}
		} catch (SQLException e) {
			this.puntLocal = 0;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getPuntVis() {
		return puntVis;
	}

	public void setPuntVis() {
		try {
			PreparedStatement psEqVis = conexionBBDD
					.prepareStatement("SELECT puntos_visitante from partidos WHERE codigo=?;");
			psEqVis.setInt(1, this.id_partido);
			ResultSet rs = psEqVis.executeQuery();
			while (rs.next()) {
				this.puntVis = rs.getInt("puntos_visitante");
			}
		} catch (SQLException e) {
			this.puntVis = 0;
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateApuestasDep(double cantidad, String apuesta) {
		String equipo;
		try {

			if (apuesta.equals("apuestaLocal")) {
				PreparedStatement psEqLocal = conexionBBDD
						.prepareStatement("SELECT equipo_local from partidos WHERE codigo=?;");
				psEqLocal.setInt(1, id_partido);
				ResultSet rs = psEqLocal.executeQuery();
				while (rs.next()) {
					equipo = rs.getString("equipo_local");
				}
			} else if (apuesta.equals("apuestaVisitante")) {
				PreparedStatement psEqVis = conexionBBDD
						.prepareStatement("SELECT equipo_visitante from partidos WHERE codigo=?;");
				psEqVis.setInt(1, id_partido);
				ResultSet rs = psEqVis.executeQuery();
				while (rs.next()) {
					equipo = rs.getString("equipo_visitante");
				}
			} else if (apuesta.equals("apuestaProrroga")) {

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Metodo que me devuelve un ArrayList con el nombre de las tablas
	public ArrayList<String> nombreTablas() {
		ArrayList<String> nombreTablas = new ArrayList<>();
		try {
			ResultSet rs = datosBBDD.getTables("nba", null, null, null);
			while (rs.next()) {
				nombreTablas.add(rs.getString("TABLE_NAME"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(nombreTablas);
		return nombreTablas;
	}

	public void buscarTablas(String tablaSeleccionada, String filtro) {
		String sql="";
		if (tablaSeleccionada.equals("estadisticas")) {
			if (filtro.equals("99/00") || filtro.isEmpty()) {
				sql = "SELECT * FROM " + tablaSeleccionada;
			} else
				sql = "SELECT * FROM estadisticas where temporada = '" + filtro + "'";

		} else if (tablaSeleccionada.equals("equipos")) {
			if (!filtro.isEmpty())
				sql = "SELECT * FROM equipos where nombre = '" + filtro + "'";
			else
				sql = "SELECT * FROM " + tablaSeleccionada;
		} else if (tablaSeleccionada.equals("jugadores")) {
			if (filtro.equals("")) {
				sql = "SELECT * FROM " + tablaSeleccionada;
			} else {

				sql = "SELECT * FROM  jugadores where Nombre_equipo= '" + filtro + "'";
			}
		} else if (tablaSeleccionada.equals("partidos")) {
			if (!filtro.isEmpty())
				sql = "SELECT * FROM partidos where temporada = '98/99' and equipo_local = '" + filtro + "'";
			if (filtro.equals("99/00") || filtro.isEmpty()) {
				sql = "SELECT * FROM " + tablaSeleccionada;
			}
		} else
			sql = "SELECT * FROM " + tablaSeleccionada;
		try {
			Statement stBusqueda= conexionBBDD.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stBusqueda.executeQuery(sql);
			this.tablas=rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ResultSet getTablas() {
		return tablas;
	}
	
}

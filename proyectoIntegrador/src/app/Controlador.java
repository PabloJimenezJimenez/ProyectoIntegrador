package app;

import java.util.ArrayList;

import javax.swing.JComboBox;

public class Controlador {

	private Login vista;
	private Modelo modelo;
	private Bienvenida miBienvenida;
	private Ajustes config;
	private PantallaApuestas pantallaApuesta;
	private ApuestasDep apuestas;
	private MenuCasino menuCasino;
	private Ruleta ruleta;
	private Estadisticas estadisticas;

	public void setMenuCasino(MenuCasino menuCasino) {
		this.menuCasino = menuCasino;
	}

	public void setRuleta(Ruleta ruleta) {
		this.ruleta = ruleta;
	}

	public void setVista(Login vista) {
		this.vista = vista;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	
	public void setEstadisticas(Estadisticas estadisticas) {
		this.estadisticas = estadisticas;
	}

	public void login() {

		String user = vista.getUsr();
		String pass = vista.getPass();
		miBienvenida.setTxtUsuario(vista.getUsr());
		modelo.login(user, pass);
		modelo.generarPartido();

	}

	public void setMiBienvenida(Bienvenida miBienvenida) {
		this.miBienvenida = miBienvenida;
	}

	// mostraremos la bienvenida y ocultaremos el login
	public void bienvinida() {
		modelo.setIdUsuario();
		vista.setVisible(false);
		miBienvenida.setVisible(true);
	}

	// actualizaremos el usuario con el nombre que meta
	public void setConfig(Ajustes config) {
		this.config = config;
	}

	public void setPantallaApuesta(PantallaApuestas pantallaApuesta) {
		this.pantallaApuesta = pantallaApuesta;
	}

	public void setApuestas(ApuestasDep apuestas) {
		this.apuestas = apuestas;
	}

	public void pantallaApuestas() {
		miBienvenida.setVisible(false);
		pantallaApuesta.setVisible(true);
	}

	// entrará en el apartado de una apuesta y cerrara el apartado con todas las
	// apuestas
	public void cambiarPantalla() {
		pantallaApuesta.setVisible(false);
		apuestas.setVisible(true);

	}

	public void ajustes() {
		miBienvenida.setVisible(false);
		config.setVisible(true);

	}

	// volvera al menu de bienvenida
	public void volverMenu() {
		miBienvenida.setVisible(true);
		pantallaApuesta.setVisible(false);
	}

	// cuando le de al boton guardar en el apartado de ajustes volvera al menu
	// y cerrera los ajustes
	public void guardarUsr() {
		miBienvenida.setVisible(true);
		config.setVisible(false);
	}

	// cambiara los datos del usuario cuando vaya a ajustes
	public void cambiarDatos() {
		String nuevoNombre = config.getTxtUsuario();
		String newPasswd = config.getnewPasswd();
		miBienvenida.setTxtUsuario(nuevoNombre);
		modelo.setUsr(nuevoNombre);
		modelo.setContraseña(newPasswd);

	}

	public void pantallaAnterior() {
		apuestas.setVisible(false);
		pantallaApuesta.setVisible(true);

	}
	
	public void cambiarPantallaBienv() {
		apuestas.setVisible(false);
		miBienvenida.setVisible(true);
		apuestas.mostrarMsjError();
	}
	public void cambioPantallaMenuRuleta() {
		miBienvenida.setVisible(false);
		menuCasino.setVisible(true);

	}

	public void cambioPantallaRuleta() {
		menuCasino.setVisible(false);
		ruleta.setVisible(true);
	}

	public void menuCasinoBienvenida() {
		menuCasino.setVisible(false);
		miBienvenida.setVisible(true);

	}

	public void ruletaMenuCasino() {
		ruleta.setVisible(false);
		menuCasino.setVisible(true);

	}

	// Escribo el nombre de los equipos del partido en apuestas
	public void MoverDatos(String partido) {
		apuestas.setLblPartido(partido);
		String equipos[] = partido.split("-");
		// Saco los equipos para generar la cuota
		String eqLocal = equipos[0];
		String eqVis = equipos[1];
		System.out.println(eqLocal);
		System.out.println(eqVis);
		modelo.SetId_partido(eqLocal, eqVis);
		modelo.generarCuota(eqLocal, eqVis);
		modelo.setPuntLocal();
		modelo.setPuntVis();
	}

	public void modificarSaldo(double cant) {
		double saldo = (modelo.getSaldo() + cant);
		saldo=Math.round(saldo*100);
		saldo/=100;
		if (saldo < 0) {
			miBienvenida.setTxtSaldo("Saldo negativo");
		} else {
			// Modifico el textfield con el saldo de la pantalla Bienvenida
			miBienvenida.setTxtSaldo("Saldo: " + saldo + " €");
			// Modifico el lbl de saldo en la pantalla de apuestasDep
			apuestas.setLblSaldoApuestas("Saldo: " + saldo + " €");
			// Modifico el lbl de saldo en la pantalla pantallaApuesta
			pantallaApuesta.setLblTxtSaldo("Saldo: " + saldo + " €");
			// Modifico el lbSaldo de la ruleta
			ruleta.setLblTxtSaldo("Saldo: " + saldo + " €");
			// Modifico el valor del saldo
			modelo.setSaldo(saldo);
		}

	}

	public void apuestaRuleta(int numUsuario, String colorUsuario, int numGanador, String colorGanador,
			double apuestaRelalizada) {
		modelo.setApuestaBBDD(numUsuario, colorUsuario, numGanador, colorGanador, apuestaRelalizada);

	}

	public void apuestaRuleta(String colorUsuario, int numGanador, String colorGanador, double apuestaRelalizada) {
		modelo.setApuestaBBDD(colorUsuario, numGanador, colorGanador, apuestaRelalizada);
	}

	//Metodo para comprobar
	public void comprobarApuesta(double cuota, double cantidad, String apuesta) {
		int prorroga=(int)(Math.random()*10)+1;
		//Valores de los puntos del partido
		int puntLocal = modelo.getPuntLocal();
		int puntVis = modelo.getPuntVis();
		//condicional para saber si ha ganado o no la apuesta
		if (apuesta.equals("apuestaLocal") && (puntLocal > puntVis)) {
			double cantApuesta = cantidad * cuota;
			double salida = Math.round(cantApuesta * 100);
			cantApuesta = salida / 100;
			modificarSaldo(cantApuesta);
		} else if (apuesta.equals("apuestaVisitante") && (puntVis > puntLocal)) {
			double cantApuesta = cantidad * cuota;
			double salida = Math.round(cantApuesta * 100);
			cantApuesta = salida / 100;
			modificarSaldo(cantApuesta);

		}else if (apuesta.equals("apuestaProrroga") && (prorroga==1)) {
			double cantApuesta = cantidad * cuota;
			double salida = Math.round(cantApuesta * 100);
			cantApuesta = salida / 100;
			modificarSaldo(cantApuesta);
		}
		else {
			modificarSaldo(-cantidad);
		}
		//Lo añado a la bbdd
		modelo.updateApuestasDep(cantidad,apuesta);
	}

	//Vuelvo a bienvenida
	public void volverEstadisticas() {
		estadisticas.setVisible(false);
		miBienvenida.setVisible(true);
		
	}
	//Cambio a la pantalla estadisticas
	public void cambiarEstadisticas() {
		miBienvenida.setVisible(false);
		estadisticas.setOpcCombo(modelo.nombreTablas());
		estadisticas.rellenarCombo();
		estadisticas.setVisible(true);
		
	}
	

}

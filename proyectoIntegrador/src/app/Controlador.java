package app;

public class Controlador {

	private Login vista;
	private Modelo modelo;
	private Bienvenida miBienvenida;
	private Ajustes config;
	
	
	public void setVista(Login vista) {
		this.vista = vista;
	}
	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}
	public void login() {
		
		String user = vista.getUsr();
		String pass = vista.getPass();
		miBienvenida.setTxtUsuario(vista.getUsr());
		modelo.login(user,pass);
		
	}
	public void setMiBienvenida(Bienvenida miBienvenida) {
		this.miBienvenida = miBienvenida;
	}
	public void bienvinida() {
		
		vista.setVisible(false);
		miBienvenida.setVisible(true);
	}
	
	public void cambiarUsr() {
		//config = new Ajustes();
		String nombre = config.getTxtUsuario();
		miBienvenida.setTxtUsuario(nombre);
	}
	public void setConfig(Ajustes config) {
		this.config = config;
	}

}

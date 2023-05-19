package app;

public class Controlador {

	private Login vista;
	private Modelo modelo;
	private Bienvenida miBienvenida;
	private Ajustes config;
	private PantallaApuestas pantallaApuesta;
	private ApuestasDep apuestas;
	
	
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
	
	//mostraremos la bienvenida y ocultaremos el login
	public void bienvinida() {
		
		vista.setVisible(false);
		miBienvenida.setVisible(true);
	}
	
	//actualizaremos el usuario con el nombre que meta
	public void cambiarUsr() {
		//config = new Ajustes();
		String nombre = config.getTxtUsuario();
		miBienvenida.setTxtUsuario(nombre);
	}
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
	
	//entrará en el apartado de una apuesta y cerrara el apartado con todas las apuestas
	public void cambiarPantalla() {
		pantallaApuesta.setVisible(false);
		apuestas.setVisible(true);
		
	}
	public void ajustes() {
		miBienvenida.setVisible(false);
		config.setVisible(true);
		
	}
	
	//volvera al menu de bienvenida
	public void volverMenu() {
		miBienvenida.setVisible(true);
		pantallaApuesta.setVisible(false);
	}
	
	//cuando le de al boton guardar en el apartado de ajustes volvera al menu 
	//y cerrera los ajustes
	public void guardarUsr() {
		miBienvenida.setVisible(true);
		config.setVisible(false);
	}
	
	//cambiara los datos del usuario cuando vaya a ajustes
	public void cambiarDatos() {
		String nuevoNombre= config.getTxtUsuario();
		String newPasswd=config.getnewPasswd();
		miBienvenida.setTxtUsuario(nuevoNombre);
		modelo.setUsr(nuevoNombre);
		modelo.setContraseña(newPasswd);
		
	}
	
}

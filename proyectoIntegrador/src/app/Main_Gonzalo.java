package app;

public class Main_Gonzalo {
	static PantallaApuestas pantalla = new PantallaApuestas();
	static ApuestasDep apuestas = new ApuestasDep();
	public static void main(String[] args) {
		

		Controlador miControlador = new Controlador();
		Modelo miModelo = new Modelo();
		Login miVista = new Login();
		
		Bienvenida miBienvenida = new Bienvenida();
		Ajustes config = new Ajustes();
		
		miControlador.setModelo(miModelo);
		miControlador.setVista(miVista);
		miControlador.setMiBienvenida(miBienvenida);
		miControlador.setConfig(config);
		
		miModelo.setMiVista(miVista);
		
		miVista.setModelo(miModelo);
		miVista.setControlador(miControlador);
		
		miVista.setVisible(true);
		
		
		
			
			pantalla.setVisible(true);

	

		

	}
	public void cambiarPantalla() {
		pantalla.setVisible(false);
		apuestas.setVisible(true);
	}
	public void pantallaAnterior() {
		pantalla.setVisible(true);
		apuestas.setVisible(false);
	}

}

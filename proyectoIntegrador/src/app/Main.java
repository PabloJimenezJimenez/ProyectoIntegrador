package app;

public class Main {
	public static void main(String[] args) {
		

		Controlador miControlador = new Controlador();
		Modelo miModelo = new Modelo();
		Login miVista = new Login();
		PantallaApuestas pantalla = new PantallaApuestas();
		ApuestasDep apuestas = new ApuestasDep();
		Bienvenida miBienvenida = new Bienvenida();
		Ajustes config = new Ajustes();
		Estadisticas misEstadisticas = new Estadisticas();
		
		//presentamos los setters al controlador
		
		miControlador.setModelo(miModelo);
		miControlador.setVista(miVista);
		miControlador.setMiBienvenida(miBienvenida);
		miControlador.setConfig(config);
		miControlador.setApuestas(apuestas);
		miControlador.setPantallaApuesta(pantalla);
		miControlador.setConfig(config);
		miControlador.setEstadisticas(misEstadisticas);
		
		
		miModelo.setMiVista(miVista);
		
		miVista.setModelo(miModelo);
		miVista.setControlador(miControlador);
		
		miBienvenida.setControlador(miControlador);
		
		config.setMiContralador(miControlador);
		
		apuestas.setControlador(miControlador);
		
		pantalla.setControladoor(miControlador);
		
		misEstadisticas.setMiControlador(miControlador);
		miVista.setVisible(true);
		
		
	

		

	}

}

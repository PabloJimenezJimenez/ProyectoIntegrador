package app;

public class Main_Gonzalo {

	public static void main(String[] args) {
		

		Controlador miControlador = new Controlador();
		Modelo miModelo = new Modelo();
		Login miVista = new Login();
		Bienvenida miBienvenida = new Bienvenida();
		
		miControlador.setModelo(miModelo);
		miControlador.setVista(miVista);
		miControlador.setMiBienvenida(miBienvenida);
		
		miModelo.setMilista(miVista);
		
		miVista.setModelo(miModelo);
		miVista.setControlador(miControlador);
		
		miVista.setVisible(true);

	}

}
package app;

public class MainPablo {

	static PantallaApuestas pantalla = new PantallaApuestas();
	static ApuestasDep apuestas = new ApuestasDep();
	
	public static void main(String[] args) {
		
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
	
	public void partido() {
		
		
	}
}

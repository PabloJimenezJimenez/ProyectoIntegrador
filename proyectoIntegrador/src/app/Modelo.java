package app;

public class Modelo {

private Login miVista;
	
	private final String usr;
	private final String contraseña;
	private String resultado;
	private int fallos;
	
	public Modelo() {
		this.usr="Gonzalo";
		this.contraseña="Herrera";
		this.fallos=0;
	}

	public void setMilista(Login miLista) {
		this.miVista = miLista;
	}

	public void login(String usr,String contraseña) {
		
		if(this.usr.equals(usr) && this.contraseña.equals(contraseña)) {
			resultado = "correcto";
		}else {
			
			fallos++; 		
			
			if (fallos == 3) {
				resultado = "Cerrar";
			}else
				resultado = "ERROR";
			
		}
	
		miVista.actualizar();
	}

	public String getResultado() {
		return this.resultado;
	}
	
}

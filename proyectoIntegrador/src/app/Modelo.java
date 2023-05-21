package app;

public class Modelo {

private Login miVista;
	
	private String usr;
	private String contraseña;
	private String resultado;
	private int fallos;
	
	public Modelo() {
		this.usr="Gonzalo";
		this.contraseña="Herrera";
		this.fallos=0;
	}


	public void setMiVista(Login miVista) {
		this.miVista = miVista;
	}

	//comprobaremos el usuario y la contraseña
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
	
		//llamamos al metodo que esta en el login
		miVista.actualizar();
	}

	//generamos getters y setters
	public String getResultado() {
		return this.resultado;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}
	
}

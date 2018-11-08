package src;

public abstract class INivelVerificacion {

	protected Muestra muestra;
	
	public INivelVerificacion(Muestra muestra) {
		this.muestra = muestra;
	}

	public abstract void cambiarEstado(); 
}

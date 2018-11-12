package muestra;

public abstract class INivelVerificacion {

	protected Muestra muestra;
	
	public INivelVerificacion(Muestra muestra) {
		this.muestra = muestra;
	}

	public abstract void cambiarEstado(); 
}

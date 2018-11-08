package src;

public abstract class INivelVerificacion {

	Muestra muestra;
	
	public INivelVerificacion(Muestra muestra) {
		this.muestra = muestra;
	}

	public abstract void cambiarEstado(Muestra muestra); 
}

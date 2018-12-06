package muestra;

import exceptions.EstadoInvalidoException;

public abstract class INivelVerificacion {

	protected Muestra muestra;
	
	public INivelVerificacion(Muestra muestra) {
		this.muestra = muestra;
	}

	public abstract void cambiarEstado() throws EstadoInvalidoException; 
}

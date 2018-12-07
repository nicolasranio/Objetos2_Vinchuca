package muestra;

import exceptions.EstadoInvalidoException;

public class NivelVerificacionAlto extends INivelVerificacion {

	public NivelVerificacionAlto(Muestra muestra) {
		super(muestra);
	}

	@Override
	public void cambiarEstado() throws EstadoInvalidoException {
		throw new EstadoInvalidoException();
	}

}

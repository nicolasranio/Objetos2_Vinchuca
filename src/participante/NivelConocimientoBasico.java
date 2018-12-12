package participante;

import muestra.Muestra;

public class NivelConocimientoBasico extends INivelConocimiento {

	@Override
	public void verificarMuestra(Muestra muestra) {
		//no hace nada
	}

	@Override
	public boolean esDefinitorio() {
		return false;
	}

}

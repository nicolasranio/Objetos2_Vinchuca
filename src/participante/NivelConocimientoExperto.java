package participante;

import muestra.Muestra;
import muestra.NivelVerificacionAlto;

public class NivelConocimientoExperto extends INivelConocimiento {

	/**
	 * Al verificar una Muestra, un Nivel Experto, este cambia el nivelDeVerificacion de tal muestra.
	 */
	@Override
	public void verificarMuestra(Muestra muestra) {
		muestra.setNivelVerificacion(new NivelVerificacionAlto(muestra));
	}

	@Override
	public boolean esDefinitorio() {
		return true;
	}	

}

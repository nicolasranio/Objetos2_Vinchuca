package participante;

import muestra.Muestra;
import muestra.NivelVerificacionAlto;

public class NivelConocimientoExperto extends INivelConocimiento {

	@Override
	public void verificarMuestra(Muestra muestra) {
		muestra.setNivelVerificacion(new NivelVerificacionAlto(muestra));
	}	

}

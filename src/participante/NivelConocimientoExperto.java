package participante;

import muestra.Muestra;
import muestra.NivelVerificacionAlto;

public class NivelConocimientoExperto extends INivelConocimiento {

	
	public NivelConocimientoExperto(Participante participante) {
		super(participante);
	}

	
	/*public void cambiarEstado() {
		//por ahora no hace nada...
	}*/


	@Override
	public void verificarMuestra(Muestra muestra) {
		muestra.setNivelVerificacion(new NivelVerificacionAlto(muestra));
	}


	/*
	@Override
	public void enviarMuestra(Muestra muestra) {
		this.verificarMuestra(muestra);
	}
	*/
	

}

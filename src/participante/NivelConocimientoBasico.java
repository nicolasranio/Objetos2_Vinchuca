package participante;

import muestra.Muestra;

public class NivelConocimientoBasico extends INivelConocimiento {

	@Override
	public void verificarMuestra(Muestra muestra) {
		if ((this.participante.getMuestrasEnviadas().size()>10) && (this.participante.getMuestrasVerificadas().size()>20)){
			this.participante.setEstado(new NivelConocimientoExperto(this.participante));
		}
	}

}

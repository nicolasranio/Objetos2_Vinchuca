package participante;

import muestra.Muestra;

public class NivelConocimientoBasico extends INivelConocimiento {

	public NivelConocimientoBasico(Participante participante){
		super(participante);	
	}

/*	@Override
	public void cambiarEstado() {
		if ((this.participante.getMuestrasEnviadas().size()>10) && (this.participante.getMuestrasVerificadas().size()>20)){
			this.participante.setEstado(new NivelConocimientoExperto(this.participante));
		}
	}*/

	
	//este comportamiento deberia tenerlo el participante, que va a ser quien decida como cambiarse el estado.
	//tambien va a tener el comportamiento para mantener o volver a basico el estado experto
	@Override
	public void verificarMuestra(Muestra muestra) {
		if ((this.participante.getMuestrasEnviadas().size()>10) && (this.participante.getMuestrasVerificadas().size()>20)){
			this.participante.setEstado(new NivelConocimientoExperto(this.participante));
		}
	}

	/*
	@Override
	public void enviarMuestra(Muestra muestra) {
		this.cambiarEstado();
	}
	*/
}

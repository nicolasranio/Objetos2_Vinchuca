package src;

public class NivelConocimientoBasico extends INivelConocimiento {

	public NivelConocimientoBasico(Participante participante){
		super(participante);	
	}

	@Override
	public void cambiarEstado() {
		if ((this.participante.getMuestrasEnviadas().size()>10) && (this.participante.getMuestrasVerificadas().size()>20)){
			this.participante.setEstado(new NivelConocimientoExperto(this.participante));
		}
	}

	@Override
	public void verificarMuestra(Muestra muestra) {
		//no hace nada
	}

	
	@Override
	public void enviarMuestra(Muestra muestra) {
		//no hace nada
	}
}

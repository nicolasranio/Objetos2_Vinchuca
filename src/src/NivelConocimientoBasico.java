package src;

public class NivelConocimientoBasico implements INivelConocimiento {

	private Participante participante;

	public NivelConocimientoBasico(Participante participante){
		this.participante = participante;
		
	}

	@Override
	public void cambiarEstado() {
		// TODO Auto-generated method stub
		if ((participante.getMuestrasEnviadas().size()>10) && (participante.getMuestrasVerificadas().size()>20)){
			participante.setEstado(new NivelConocimientoExperto(participante));
		}
	}
}

package src;

public class NivelConocimientoBasico extends INivelConocimiento {

	private Participante participante;

	public NivelConocimientoBasico(Participante participante){
		super(participante);	
	}

	@Override
	public void cambiarEstado() {
		if ((participante.getMuestrasEnviadas().size()>10) && (participante.getMuestrasVerificadas().size()>20)){
			participante.setEstado(new NivelConocimientoExperto(participante));
		}
	}
}

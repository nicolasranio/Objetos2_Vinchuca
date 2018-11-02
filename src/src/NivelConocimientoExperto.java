package src;

public class NivelConocimientoExperto implements INivelConocimiento {

	private Participante participante;

	public NivelConocimientoExperto(Participante participante) {
		this.participante = participante;
	}

	@Override
	public void cambiarEstado() {
		//por ahora no hace nada...
	}

}

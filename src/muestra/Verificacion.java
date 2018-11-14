package muestra;

import participante.Participante;

public class Verificacion {

	private Participante participante;
	private TipoVinchuca tipoVinchuca;
	
	public Verificacion(Participante participante, TipoVinchuca validacion) {
		this.participante = participante;
		this.tipoVinchuca = validacion;
	}
	
	public Participante getParticipante() {
		return participante;
	}
	public TipoVinchuca getTipoVinchuca() {
		return tipoVinchuca;
	}
	
	
	
}

package muestra;

import java.util.Date;

import participante.Participante;

public class VerificacionMuestra {

	private Participante participante;
	private TipoVinchuca tipoVinchuca;
	private Date fechaVerificacion;
	
	public VerificacionMuestra(Participante participante, TipoVinchuca validacion) {
		this.participante = participante;
		this.tipoVinchuca = validacion;
		this.fechaVerificacion=new Date();
	}
	
	public Participante getParticipante() {
		return participante;
	}
	public TipoVinchuca getTipoVinchuca() {
		return tipoVinchuca;
	}
	
	public Date fechaVerificacion(){
		return fechaVerificacion;
	}
	
	
}

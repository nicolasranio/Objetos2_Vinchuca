package muestra;

import java.time.LocalDate;
import java.util.Date;

import participante.Participante;

public class VerificacionMuestra {

	private Participante participante;
	private TipoVinchuca tipoVinchuca;
	private LocalDate fechaVerificacion;
	
	public VerificacionMuestra(Participante participante, TipoVinchuca validacion) {
		this.participante = participante;
		this.tipoVinchuca = validacion;
		this.fechaVerificacion= LocalDate.now();
	}
	
	public Participante getParticipante() {
		return participante;
	}
	public TipoVinchuca getTipoVinchuca() {
		return tipoVinchuca;
	}
	
	public LocalDate fechaVerificacion(){
		return fechaVerificacion;
	}
	
	
}

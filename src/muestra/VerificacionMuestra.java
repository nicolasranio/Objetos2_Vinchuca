package muestra;

import java.time.LocalDate;
import java.util.Date;

import participante.Participante;

public class VerificacionMuestra {
	
	private Muestra muestra;
	private Participante participante;
	private TipoVinchuca tipoVinchuca;
	private LocalDate fechaVerificacion;
	
	public VerificacionMuestra(Muestra muestra, Participante participante, TipoVinchuca validacion) {
		this.muestra=muestra;
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
	
	public LocalDate getFechaVerificacion(){
		return fechaVerificacion;
	}
	
	public Muestra getMuestra(){
		return muestra;
	}
	
}
